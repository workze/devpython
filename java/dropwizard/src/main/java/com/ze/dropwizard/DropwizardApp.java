package com.ze.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.glassfish.hk2.api.DynamicConfiguration;
import org.glassfish.hk2.api.DynamicConfigurationService;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.api.ServiceLocatorFactory;
import org.glassfish.hk2.utilities.BuilderHelper;
import org.reflections.Reflections;

import javax.inject.Singleton;
import java.util.Set;

/**
 * Created by ZE-C on 2017/11/19.
 */
public class DropwizardApp extends Application<AppConfig> {

    public static void main(String[] args) throws Exception {
        System.out.println();
        new DropwizardApp().run("server", "/Users/wangguize/Documents/oschina/java/dropwizard/src/main/resources/config.yml");

    }

    @Override
    public void run(AppConfig configuration, Environment environment) throws Exception {
//        final CommonResource commonResource = new CommonResource();
//        environment.jersey().register(commonResource);
//
//        final HtmlResource htmlResource = new HtmlResource();
//        environment.jersey().register(htmlResource);
//
//        final ValidateResource validateResource = new ValidateResource();
//        environment.jersey().register(validateResource);
        //
//        ServiceLocator instancer = ServiceLocatorUtilities.createAndPopulateServiceLocator();
//        environment.getApplicationContext().setAttribute(ServletProperties.SERVICE_LOCATOR, instancer);
//        ServiceLocator instancer = ServiceLocatorFactory.getInstance().find("dw-hk2");
//        environment.getApplicationContext().setAttribute(ServletProperties.SERVICE_LOCATOR, instancer);
    }

    @Override
    public void initialize(Bootstrap<AppConfig> bootstrap) {
        // nothing to do yet
        bootstrap.addBundle(new SwaggerBundle<AppConfig>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(AppConfig configuration) {
                return configuration.swaggerBundleConfiguration;
            }
        });

        hk2Init();

    }

    private void hk2Init() {
        Reflections reflections = new Reflections("com.ze");

        Set<Class<?>> serviceClasses = reflections.getTypesAnnotatedWith(org.jvnet.hk2.annotations.Service.class);
        System.out.println(serviceClasses);

        Set<Class<?>> contractClasses = reflections.getTypesAnnotatedWith(org.jvnet.hk2.annotations.Contract.class);
        System.out.println(contractClasses);

        ServiceLocator locator = ServiceLocatorFactory.getInstance().create("dw-hk2");
        DynamicConfigurationService dcs = locator.getService(DynamicConfigurationService.class);
        DynamicConfiguration config = dcs.createDynamicConfiguration();

        for (Class<?> serviceClass : serviceClasses) {
            Class<?> linkTo = serviceClass;
            for (Class<?> contractClass : contractClasses) {
                if (contractClass.isAssignableFrom(serviceClass) && contractClass.isInterface()) {
                    linkTo = contractClass;
                    break;
                }
            }
            config.bind(BuilderHelper.link(serviceClass).to(linkTo).in(Singleton.class).build());
        }

        config.commit();
    }
}
