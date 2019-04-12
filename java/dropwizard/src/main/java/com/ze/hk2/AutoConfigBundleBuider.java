//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ze.hk2;

import io.dropwizard.Configuration;

import java.util.ArrayList;
import java.util.List;

public class AutoConfigBundleBuider<T extends Configuration> {
    private static final String DEFAULT_PACKAGE_NAME = "com.zte";
    private List<String> packageNames = new ArrayList();

    public AutoConfigBundleBuider() {
        this.packageNames.add("com.zte");
    }

    public AutoConfigBundleBuider<T> addPackageName(String packageName) {
        this.packageNames.add(packageName);
        return this;
    }

    public AutoConfigBundle<T> build() {
        return new AutoConfigBundle(this.packageNames);
    }
}
