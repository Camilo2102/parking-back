package co.com.parking.utils;

import co.com.parking.constants.LogConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {
    private static final Logger logger = LoggerFactory.getLogger(LoggerUtil.class);

    public static void info(String msg) {
        logger.info(msg);
    }

    public static void petitionLog(String... args) {
        String log = StringUtil.getFromParking(LogConstants.LOG_TEMPLATE_PETITION, args);
        logger.info(log);
    }
}
