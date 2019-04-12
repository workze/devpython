package com.ze.dropwizard.filter;
import javax.ws.rs.Path;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;

@Provider
public class Dyfilter implements DynamicFeature {
    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        Class clazz = resourceInfo.getResourceClass();
        Method method = resourceInfo.getResourceMethod();
        if(method.getDeclaredAnnotation(Path.class)!=null) {
            String path = method.getDeclaredAnnotation(Path.class).value();
            System.out.println("zzz: "+path);
        }

        context.register(DateNotSpecifiedFilter.class);

    }
}