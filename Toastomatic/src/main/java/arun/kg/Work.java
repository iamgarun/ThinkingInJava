package arun.kg;

import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;
import java.util.logging.Logger;

class Work {

    private static final Logger LOGGER = Logger.getLogger(Work.class.getName());

    private Work(){

    }

    public static void doWork(String workerName, BlockingQueue<Toast> incomingQueue, BlockingQueue<Toast> outgoingQueue, Consumer<Toast> toastWork){
        try {
            while(!Thread.interrupted()) {
                Toast toast = incomingQueue.take();
                toastWork.accept(toast);
                outgoingQueue.add(toast);
            }
        } catch (InterruptedException e) {
            LOGGER.warning(()-> String.format("Interrupted worker: %s",workerName));
            Thread.currentThread().interrupt();
        }
    }
}
