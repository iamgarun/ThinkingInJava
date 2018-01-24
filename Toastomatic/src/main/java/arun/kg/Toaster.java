package arun.kg;

import arun.kg.log.LoggingConfig;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

class Toaster implements Runnable {

    private BlockingQueue<Toast> currentQueue;

    Toaster(BlockingQueue<Toast> currentQueue) {
        this.currentQueue = currentQueue;
    }

    private static final Logger LOGGER = LoggingConfig.getNewLogger(Toaster.class.getName());

    @Override
    public void run() {

        int[] count = {1};

        try {
            while (!Thread.interrupted()) {
                TimeUnit.SECONDS.sleep(1);
                currentQueue.add(new Toast(ToastStatus.DRY, count[0]));
                LOGGER.warning(()-> String.format("Adding toast with id %s", count[0]));
                count[0]++;
            }
        } catch (InterruptedException e) {
            LOGGER.warning("Interrupted worker: Toaster");
            Thread.currentThread().interrupt();
        }
    }
}
