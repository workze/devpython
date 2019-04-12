//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ze.hk2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TerminationAppWorker implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(TerminationAppWorker.class);
    private long bootProcessDuration;

    public TerminationAppWorker(long bootProcessDuration) {
        this.bootProcessDuration = bootProcessDuration;
    }

    public void run() {
        if (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(this.bootProcessDuration);
                if (!Thread.currentThread().isInterrupted()) {
                    logger.warn("process boot process too long,exceed " + this.bootProcessDuration + " ms,process restart");
                    System.exit(1);
                }
            } catch (InterruptedException var2) {
                logger.info("process boot process finish");
            }
        }

    }
}
