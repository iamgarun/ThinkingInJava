package arun.kg.log;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggingConfig {

    private LoggingConfig(){

    }

    private static final Logger LOGGER = Logger.getLogger(LoggingConfig.class.getName());

    static {
        // Why is this not working?
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS.%1$tL %4$-7s [%3$s] (%2$s) %5$s %6$s%n");
    }

    public static Logger getNewLogger(String name){

        // Programmatic configuration

        LOGGER.entering(LoggingConfig.class.getName(),"getNewLogger");
        Logger newLogger = Logger.getLogger(name);

        ConsoleHandler consoleHandlerConfig = new ConsoleHandler();
        consoleHandlerConfig.setFormatter(new SimpleFormatter());

        newLogger.addHandler(consoleHandlerConfig);
        LOGGER.exiting(LoggingConfig.class.getName(),"getNewLogger");
        return newLogger;

    }
}
