package com.spribe.test.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    private static final Logger log = LogManager.getLogger(Log.class);

    public static void info(String str) {
        log.info(str);
    }

    public static void debug(String str) {
        log.debug(str);
    }

    public static void error(String str) {
        log.error(str);
    }


    public static void error(Throwable e) {
        log.error(e);
    }
}
