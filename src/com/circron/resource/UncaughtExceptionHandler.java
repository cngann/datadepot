package com.circron.resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.SQLException;
public class UncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override public void uncaughtException(Thread t, Throwable e) {
        Log log = LogFactory.getLog(this.getClass());
        log.debug("Uncaught Error, logging stack trace.");
        if (e.getCause() instanceof SQLException) {
            System.out.println(e.getCause().getMessage());
        }
    }
}
