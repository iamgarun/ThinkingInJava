package arun.kg;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Toaster implements Runnable {

    private BlockingQueue<Toast> currentQueue;

    public Toaster(BlockingQueue<Toast> currentQueue) {
        this.currentQueue = currentQueue;
    }

    @Override
    public void run() {

        int count = 1;
        Toast toast = null;

        try {
            while (!Thread.interrupted()) {
                TimeUnit.SECONDS.sleep(1);
                currentQueue.add(new Toast(ToastStatus.DRY, count));
                System.out.println(String.format("Adding toast with id %s", count));
                count++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
