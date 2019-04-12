//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ze.hk2;

import org.glassfish.hk2.api.ServiceLocator;

public class ServiceLocatorHolder {
    private static ServiceLocator locator;

    public ServiceLocatorHolder() {
    }

    public static ServiceLocator getLocator() {
        return locator;
    }

    public static void setLocator(ServiceLocator locator) {
        locator = locator;
    }
}
