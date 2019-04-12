//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ze.hk2;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Set;

public class ServiceBinder extends AbstractBinder {
    private static final Logger LOG = LoggerFactory.getLogger(ServiceBinder.class);
    final Set<Class<?>> klasses;

    public ServiceBinder(Set<Class<?>> services) {
        this.klasses = services;
    }

    protected void configure() {
        Iterator var1 = this.klasses.iterator();

        while(var1.hasNext()) {
            Class klass = (Class)var1.next();

            try {
                LOG.info("start active class:" + klass.getName());
                this.addActiveDescriptor(klass);
            } catch (Exception var4) {
                LOG.info("active class error:" + klass.getName(), var4);
            }
        }

    }
}
