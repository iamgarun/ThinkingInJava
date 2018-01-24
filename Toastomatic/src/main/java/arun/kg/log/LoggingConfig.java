package arun.kg.log;

import java.io.IOException;
import java.util.logging.*;

public class LoggingConfig {

    private LoggingConfig() {
    }

    public static void initializeLogging() {
        try {
            LogManager.getLogManager().readConfiguration(LoggingConfig.class.getResourceAsStream("/logging.properties"));
        } catch (IOException e) {
            Logger.getGlobal().log(Level.SEVERE, "Unable to init logging system", e);
        }
    }
}
