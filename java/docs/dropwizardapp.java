package com.zte.iot.aep.customer;

import com.zte.ums.zenap.hk2.IOCApplication;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

/**
 * Created by 10225371 on 2017/8/22.
 */
public class Runner extends IOCApplication<Config> {
    public static void main(String[] args) throws Exception{
        new Runner().run(args);
    }

    @Override
    public String getName() {
        return " CUSTOMER-HANDLER APP ";
    }

    @Override
    public void initialize(final Bootstrap<Config> bootstrap) {
        super.initialize(bootstrap);
    }

    @Override
    public void run(final Config configuration, final Environment environment) throws Exception {
        super.run(configuration, environment);
        environment.jersey().register(MultiPartFeature.class);
        // 新增跨域支持
        environment.servlets()
                .addFilter("CrossOriginFilter", new CrossOriginFilter())
                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
    }

    @Override 
public void initialize(Bootstrap<HelloAppConfiguration> bootstrap) { 
bootstrap.addBundle(new AutoConfigBundleBuider().addPackageName("ioc").build()); 
} 
}
}
