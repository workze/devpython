//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ze.hk2;

import io.dropwizard.lifecycle.Managed;
import org.glassfish.hk2.api.ServiceLocator;

public class ServiceLocatorManaged implements Managed {
    private ServiceLocator locator;

    public ServiceLocatorManaged(ServiceLocator locator) {
        this.locator = locator;
    }

    public void start() throws Exception {
    }

    public void stop() throws Exception {
        this.locator.shutdown();
    }
}
