//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ze.hk2;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.cli.CheckCommand;
import io.dropwizard.cli.Cli;
import io.dropwizard.cli.ServerCommand;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.util.JarLocation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;

public abstract class DwExtApplication<T extends Configuration> extends Application<T> {
    private static final Logger logger = LoggerFactory.getLogger(DwExtApplication.class);
    private static final long defaultBootProcessDuration = 300L;
    private static final String bootProcessDurationFlag = "Boot_Process_Duration";
    private ExtFileConfigurationSourceProvider configProvider = new ExtFileConfigurationSourceProvider();

    public DwExtApplication() {
    }

    public void run(final String... arguments) throws Exception {
        FrameWorkThreadGroup group = new FrameWorkThreadGroup("FrameWorkThreadGroup");
        Thread frameWorkSuperThread = new Thread(group, new Runnable() {
            public void run() {
                Thread thread = DwExtApplication.this.bootTerminationAppWorker();

                try {
                    Bootstrap<T> bootstrap = new Bootstrap(DwExtApplication.this);
                    bootstrap.setConfigurationSourceProvider(DwExtApplication.this.configProvider);
                    bootstrap.addCommand(new ServerCommand(DwExtApplication.this));
                    bootstrap.addCommand(new CheckCommand(DwExtApplication.this));
                    DwExtApplication.this.initialize(bootstrap);
                    Cli cli = new Cli(new JarLocation(this.getClass()), bootstrap, System.out, System.err);

                    try {
                        if (!cli.run(arguments)) {
                            System.exit(1);
                        }
                    } catch (Exception var8) {
                        System.exit(1);
                    }
                } finally {
                    thread.interrupt();
                }

            }
        });
        frameWorkSuperThread.start();
        frameWorkSuperThread.join();
    }

    private Thread bootTerminationAppWorker() {
        long startMaxDuration = this.getStartMaxDuration();
        TerminationAppWorker termWorker = new TerminationAppWorker(startMaxDuration);
        Thread thread = new Thread(termWorker);
        thread.setName("TerminationAppWorker");
        thread.setDaemon(true);
        thread.start();
        return thread;
    }

    private long getStartMaxDuration() {
        String startMaxDuration = System.getenv("Boot_Process_Duration");
        if (StringUtils.isEmpty(startMaxDuration)) {
            startMaxDuration = System.getProperty("Boot_Process_Duration", String.valueOf(300L));
        }

        return Long.valueOf(startMaxDuration) * 1000L;
    }

    public void run(T configuration, Environment environment) throws Exception {
        try {
            String envString = (String)System.getenv().entrySet().stream().map((entry) -> {
                return (String)entry.getKey() + "=" + (String)entry.getValue();
            }).collect(Collectors.joining("\r\n"));
            logger.info("system env properties:" + envString);
            String systemPropString = (String)System.getProperties().entrySet().stream().map((entry) -> {
                return entry.getKey() + "=" + entry.getValue();
            }).collect(Collectors.joining("\r\n"));
            logger.info("system properties:" + systemPropString);
        } catch (Exception var5) {
            logger.error("", var5);
        }

        logger.info("yaml config:{}", this.configProvider.transformConfigString);
        ObjectMapper objectMapper = environment.getObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setSerializationInclusion(Include.NON_NULL);
    }
}
