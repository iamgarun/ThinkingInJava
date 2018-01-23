package arun.kg;

import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

class Work {

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
            System.out.println(String.format("Interrupted worker: %s",workerName));
        }
    }
}
