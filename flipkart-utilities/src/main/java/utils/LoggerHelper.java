package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerHelper {
    public static Logger  logger(Class<?> className){
        Logger logger = LogManager.getLogger(className);
        return logger;

    }

}
