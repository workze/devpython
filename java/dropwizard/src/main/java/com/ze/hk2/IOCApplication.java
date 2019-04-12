//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ze.hk2;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public abstract class IOCApplication<T extends Configuration> extends DwExtApplication<T> {
    public IOCApplication() {
    }

    public void initialize(Bootstrap<T> bootstrap) {
        super.initialize(bootstrap);
        bootstrap.addBundle((new AutoConfigBundleBuider()).build());
    }

    public void run(T configuration, Environment environment) throws Exception {
        super.run(configuration, environment);
    }
}
