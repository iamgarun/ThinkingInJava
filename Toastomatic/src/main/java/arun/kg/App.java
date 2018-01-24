package arun.kg;

import arun.kg.log.LoggingConfig;

import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * Hello world!
 */
public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) throws InterruptedException {

        LoggingConfig.initializeLogging();

        BlockingQueue<Toast> dryQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Toast> butterQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Toast> peanutButterQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Toast> jamQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Toast> finalQueue = new LinkedBlockingQueue<>();

        ExecutorService toastExecutorService = Executors.newFixedThreadPool(5);

        toastExecutorService.execute(new Toaster(dryQueue));

        // Apply butter
        toastExecutorService.execute(() -> Work.doWork("Butter", dryQueue, butterQueue, toast -> {
                    toast.butterToast();
                    LOGGER.info(() -> String.format("Adding butter to toast: %s", toast.getId()));
                })
        );

        // Apply peanutButter
        toastExecutorService.execute(() -> Work.doWork("PeanutButter", butterQueue, peanutButterQueue, toast -> {
                    toast.peanutButterToast();
                    LOGGER.info(() -> String.format("Adding peanut butter to  toast: %s", toast.getId()));
                })
        );

        // Apply Jam
        toastExecutorService.execute(() -> Work.doWork("Jammer", peanutButterQueue, jamQueue, toast -> {
                    toast.jamToast();
                    LOGGER.info(() -> String.format("Adding jam to toast : %s", toast.getId()));
                })
        );

        // Apply jelly
        toastExecutorService.execute(() -> Work.doWork("Jelly", jamQueue, finalQueue, toast -> {
                toast.jellyToast();
                LOGGER.info(() -> String.format("Adding jelly to toast : %s", toast.getId()));
            })
        );

        TimeUnit.SECONDS.sleep(10);
        LOGGER.info("Shutting down executor gracefully");
        toastExecutorService.shutdownNow();
    }
}
