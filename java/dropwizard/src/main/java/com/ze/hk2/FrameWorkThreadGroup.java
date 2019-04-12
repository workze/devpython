//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ze.hk2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FrameWorkThreadGroup extends ThreadGroup {
    private static final Logger logger = LoggerFactory.getLogger(FrameWorkThreadGroup.class);

    public FrameWorkThreadGroup(String name) {
        super(name);
    }

    public void uncaughtException(Thread t, Throwable e) {
        logger.error(t.getName() + " throw this exception ", e);
        boolean containsOOM = containsOOM(e);
        if (containsOOM) {
            logger.error("uncaughtException OutOfMemoryError from the thread(" + t.getName() + "), System will exit -1", e);
            System.exit(-1);
        } else {
            super.uncaughtException(t, e);
        }

    }

    private static boolean containsOOM(Throwable e) {
        if (e == null) {
            return false;
        } else {
            Throwable cause = e;

            while(!(cause instanceof OutOfMemoryError)) {
                if ((cause = cause.getCause()) == null) {
                    return false;
                }
            }

            return true;
        }
    }
}
