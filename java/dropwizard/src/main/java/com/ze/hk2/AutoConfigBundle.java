//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ze.hk2;

import com.google.common.collect.Lists;
import io.dropwizard.Configuration;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.configuration.ConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.api.ServiceLocatorFactory;
import org.glassfish.hk2.utilities.Binder;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.jvnet.hk2.annotations.Service;
import org.reflections.Reflections;
import org.reflections.scanners.Scanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Path;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

public class AutoConfigBundle<T extends Configuration> implements ConfiguredBundle<T> {
    private static final Logger LOG = LoggerFactory.getLogger(AutoConfigBundle.class);
    private ServiceLocator locator;
    private Reflections reflections;
    private Set<Class<?>> services;
    private Bootstrap<?> bootstrap;

    AutoConfigBundle(String packageName) {
        this((List)Lists.newArrayList(new String[]{packageName}));
    }

    AutoConfigBundle(List<String> packageNames) {
        FilterBuilder filterBuilder = new FilterBuilder();
        packageNames.stream().forEach((packageName) -> {
            filterBuilder.include(FilterBuilder.prefix(packageName));
        });
        ConfigurationBuilder reflectionCfg = new ConfigurationBuilder();
        packageNames.stream().forEach((packageName) -> {
            reflectionCfg.addUrls(ClasspathHelper.forPackage(packageName, new ClassLoader[0]));
        });
        reflectionCfg.filterInputsBy(filterBuilder).setScanners(new Scanner[]{new SubTypesScanner(), new TypeAnnotationsScanner()});
        this.reflections = new Reflections(reflectionCfg);
        this.locator = ServiceLocatorFactory.getInstance().create("dw-hk2");
        ServiceLocatorHolder.setLocator(this.locator);
    }

    public static <T extends Configuration> AutoConfigBundleBuider<T> newBuilder() {
        return new AutoConfigBundleBuider();
    }

    public void initialize(Bootstrap<?> bootstrap) {
        this.bootstrap = bootstrap;
        this.registerPreLoadService();
    }

    private void registerPreLoadService() {
        this.registerService(PreLoad.class);
    }

    public void run(T configuration, Environment environment) throws Exception {
        ConfigurationSourceProvider configProvider = this.bootstrap.getConfigurationSourceProvider();
        if (configProvider instanceof ExtFileConfigurationSourceProvider) {
            LOG.info("yaml config:{}", ((ExtFileConfigurationSourceProvider)configProvider).transformConfigString);
        }

//        this.registerConfigurationProvider(configuration, environment);
//        this.registerEnvironment(environment);
//        this.registerObjectMapper(environment);
        environment.getApplicationContext().getServletContext().setAttribute("jersey.config.servlet.context.serviceLocator", this.locator);
        this.registerService(PreBaseService.class);
        this.registerService(BaseService.class);
        this.registerService(PostBaseService.class);
        this.registePreServices();
        this.registerServices();
        this.registerManaged(environment);
//        this.registerLifecycle(environment);
//        this.registerServerLifecycleListeners(environment);
//        this.registerJettyLifeCycleListener(environment);
//        this.registerTasks(environment);
//        this.registerHealthChecks(environment);
//        this.registerProviders(environment);
        this.registerResources(environment);
        environment.lifecycle().manage(new ServiceLocatorManaged(this.locator));
    }

    private void registePreServices() {
        this.registerService(PreServiceLoad.class);
    }

/*    private void registerProviders(Environment environment) {
        Stream var10000 = this.reflections.getTypesAnnotatedWith(Provider.class).stream();
        Set var10001 = this.services;
        this.services.getClass();
        var10000.filter(var10001::contains).forEach((providerKlass) -> {
            try {
                environment.jersey().register(this.instancer.getService((Type) providerKlass, new Annotation[0]));
            } catch (Exception var4) {
                LOG.warn("", var4);
            }

        });
    }*/

/*    private void registerTasks(Environment environment) {
        Stream var10000 = this.reflections.getSubTypesOf(Task.class).stream();
        Set var10001 = this.services;
        this.services.getClass();
        var10000.filter(var10001::contains).forEach((taskKlass) -> {
            try {
                environment.admin().addTask((Task)this.instancer.getService((Type) taskKlass, new Annotation[0]));
            } catch (Exception var4) {
                LOG.warn("", var4);
            }

        });
    }*/

  /*  private void registerJettyLifeCycleListener(Environment environment) {
        Stream var10000 = this.reflections.getSubTypesOf(Listener.class).stream();
        Set var10001 = this.services;
        this.services.getClass();
        var10000.filter(var10001::contains).forEach((lifecycleListenerKlass) -> {
            try {
                environment.lifecycle().addLifeCycleListener((Listener)this.instancer.getService((Type) lifecycleListenerKlass, new Annotation[0]));
            } catch (Exception var4) {
                LOG.warn("", var4);
            }

        });
    }*/

/*    private void registerServerLifecycleListeners(Environment environment) {
        Stream var10000 = this.reflections.getSubTypesOf(ServerLifecycleListener.class).stream();
        Set var10001 = this.services;
        this.services.getClass();
        var10000.filter(var10001::contains).forEach((serverLifecycleListenerKlass) -> {
            try {
                environment.lifecycle().addServerLifecycleListener((ServerLifecycleListener)this.instancer.getService((Type) serverLifecycleListenerKlass, new Annotation[0]));
            } catch (Exception var4) {
                LOG.warn("", var4);
            }

        });
    }*/

  /*  private void registerLifecycle(Environment environment) {
        Stream var10000 = this.reflections.getSubTypesOf(LifeCycle.class).stream();
        Set var10001 = this.services;
        this.services.getClass();
        var10000.filter(var10001::contains).forEach((lifeCycleKlass) -> {
            try {
                environment.lifecycle().manage((LifeCycle)this.instancer.getService((Type) lifeCycleKlass, new Annotation[0]));
            } catch (Exception var4) {
                LOG.warn("", var4);
            }

//            LOG.info("Registering Dropwizard LifeCycle, class name : {}", lifeCycleKlass.getName());
        });
    }*/

    private void registerManaged(Environment environment) {
    }
//
//    private void registerObjectMapper(Environment environment) {
//        final ObjectMapper objectMapper = environment.getObjectMapper();
//        ServiceLocatorUtilities.bind(this.instancer, new Binder[]{new AbstractBinder() {
//            protected void configure() {
//                this.bind(objectMapper).to(ObjectMapper.class);
//                AutoConfigBundle.LOG.info("Registering Dropwizard objectMapper, class name : {}", objectMapper.getClass().getName());
//            }
//        }});
//    }

//    private void registerEnvironment(final Environment environment) {
//        ServiceLocatorUtilities.bind(this.instancer, new Binder[]{new AbstractBinder() {
//            protected void configure() {
//                this.bind(environment).to(Environment.class);
//                AutoConfigBundle.LOG.info("Registering Dropwizard environment, class name : {}", environment.getClass().getName());
//            }
//        }});
//    }

//    private void registerConfigurationProvider(final T configuration, Environment environment) {
//        ServiceLocatorUtilities.bind(this.instancer, new Binder[]{new AbstractBinder() {
//            protected void configure() {
//                this.bind(configuration);
//                AutoConfigBundle.LOG.info("Registering Dropwizard Configuration class name:{}", configuration.getClass().getName());
//                if (configuration instanceof Configuration) {
//                    this.bind(configuration).to(Configuration.class);
//                    AutoConfigBundle.LOG.info("Registering Dropwizard Configuration class name:{}", Configuration.class.getName());
//                }
//
//            }
//        }});
//        this.registerSubConfigure(configuration, environment);
//    }
//
//    private void registerSubConfigure(final T configuration, Environment environment) {
//        List<Field> subDeclaredFields = Arrays.asList(configuration.getClass().getDeclaredFields());
//        List<Field> parentDeclaredFields = Arrays.asList(Configuration.class.getDeclaredFields());
//        final List<Field> filtersubDeclaredFields = (List)subDeclaredFields.stream().filter((subDeclaredField) -> {
//            return !subDeclaredField.getType().isPrimitive();
//        }).filter((subDeclaredField) -> {
//            return !subDeclaredField.getType().equals(String.class);
//        }).filter((subDeclaredField) -> {
//            return !parentDeclaredFields.contains(subDeclaredField);
//        }).collect(Collectors.toList());
//        ServiceLocatorUtilities.bind(this.instancer, new Binder[]{new AbstractBinder() {
//            protected void configure() {
//                filtersubDeclaredFields.forEach((subField) -> {
//                    subField.setAccessible(true);
//
//                    try {
//                        Object subConfig = subField.get(configuration);
//                        if (subConfig != null) {
//                            this.bind(subConfig);
//                            AutoConfigBundle.LOG.info("Registering Dropwizard Sub Configuration class name {}", subConfig.getClass().getName());
//                        }
//                    } catch (Exception var4) {
//                        AutoConfigBundle.LOG.error("bind sub config:{} fail", subField);
//                    }
//
//                });
//            }
//        }});
//    }

    private void registerServices() {
        this.services = this.reflections.getTypesAnnotatedWith(Service.class, true);
        if (!this.services.isEmpty()) {
            ServiceLocatorUtilities.bind(this.locator, new Binder[]{new ServiceBinder(this.services)});
            this.services.forEach((s) -> {
                LOG.info("Registering Dropwizard service, class name : {}", s.getName());
            });
            this.services.stream().filter((serviceClazz) -> {
                return serviceClazz.getAnnotation(Lazy.class) == null;
            }).peek((serviceClazz) -> {
                LOG.info("active service, class name : {}", serviceClazz.getName());
            }).forEach((serviceClazz) -> {
                try {
                    long startTime = System.currentTimeMillis();
                    this.locator.getService(serviceClazz, new Annotation[0]);
                    LOG.info("active service, class name : {},cost time:{}", serviceClazz.getName(), System.currentTimeMillis() - startTime);
                } catch (Exception var4) {
                    LOG.warn("", var4);
                }

            });
        } else {
            LOG.warn("Registering Dropwizard service is empty");
        }

    }

    private void registerResources(Environment environment) {
        this.reflections.getTypesAnnotatedWith(Path.class).stream().forEach((resourceClass) -> {
            LOG.info("begin Registering Dropwizard resource, class name : {}", resourceClass.getName());

            try {
                Object resourceObject = this.locator.getService(resourceClass, new Annotation[0]);
                if (resourceObject != null) {
                    environment.jersey().register(resourceObject);
                    LOG.info("Registering Dropwizard resource, class name : {}", resourceClass.getName());
                } else {
                    LOG.warn(resourceClass.getName() + " not use Service annotation");
                }
            } catch (Exception var4) {
                LOG.error("", var4);
            }

        });
    }

/*    private void registerHealthChecks(Environment env) {
        Stream var10000 = this.reflections.getSubTypesOf(HealthCheck.class).stream();
        Set var10001 = this.services;
        this.services.getClass();
        var10000.filter(var10001::contains).forEach((healthCheckKlass) -> {
            try {
                env.healthChecks().register("healthChecks", (HealthCheck)this.instancer.getService((Type) healthCheckKlass, new Annotation[0]));
            } catch (Exception var4) {
                LOG.warn("", var4);
            }

        });
    }*/

    private void registerService(Class<? extends Annotation> annotationClazz) {
        Set<Class<?>> services = this.reflections.getTypesAnnotatedWith(annotationClazz, true);
        if (!services.isEmpty()) {
            ServiceLocatorUtilities.bind(this.locator, new Binder[]{new ServiceBinder(services)});
            services.forEach((s) -> {
                LOG.info("{} Registering  service, class name : {}", annotationClazz.getName(), s.getName());
            });
            services.stream().filter((serviceClazz) -> {
                return serviceClazz.getAnnotation(Lazy.class) == null;
            }).peek((serviceClazz) -> {
                LOG.info("active service, class name : {}", serviceClazz.getName());
            }).forEach((serviceClazz) -> {
                try {
                    long startTime = System.currentTimeMillis();
                    this.locator.getService(serviceClazz, new Annotation[0]);
                    LOG.info("active service, class name : {},cost time:{}", serviceClazz.getName(), System.currentTimeMillis() - startTime);
                } catch (Exception var4) {
                    LOG.warn("", var4);
                }

            });
        } else {
            LOG.warn("Registering {} service is empty", annotationClazz.getName());
        }

    }
}
