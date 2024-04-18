package Utilities;

import org.apache.logging.log4j.LogManager;
import org.junit.Assert;

public class Logger {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Logger.class);

    public static void info(String message) {
        logger.info(message);
    }

    public static void assertWithLogging(String message) {
        logger.error(message);
        Assert.fail();
    }

    public static void assertEquals(Object actual, Object expected) {
        info("Expected: " + expected + "Actual: " + actual);
        Assert.assertEquals(actual, expected);
    }
}