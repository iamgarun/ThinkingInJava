package arun.kg;

import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

public class Work {

    public static void doWork(BlockingQueue<Toast> incomingQueue, BlockingQueue<Toast> outgoingQueue, Consumer<Toast> toastWork){
        try {
            while(!Thread.interrupted()) {
                Toast toast = null;
                toast = incomingQueue.take();
                toastWork.accept(toast);
                outgoingQueue.add(toast);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
