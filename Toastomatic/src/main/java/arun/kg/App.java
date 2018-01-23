package arun.kg;

import java.util.concurrent.*;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Toast> dryQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Toast>   butterQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Toast>  peanutButterQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Toast>  jamQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Toast> finalQueue = new LinkedBlockingQueue<>();

        ExecutorService toastExecutorService = Executors.newFixedThreadPool(5);

        toastExecutorService.execute(new Toaster(dryQueue));

        // Apply butter
        toastExecutorService.execute(() -> Work.doWork("Butter",dryQueue, butterQueue, toast -> {
            toast.butterToast();
            System.out.println(String.format("Adding butter to toast: %s", toast.getId()));
        })
        );

        // Apply peanutButter
        toastExecutorService.execute(() -> Work.doWork("PeanutButter",butterQueue, peanutButterQueue, toast -> {
            toast.peanutButterToast();
            System.out.println(String.format("Adding peanut butter to  toast: %s", toast.getId()));
        })
        );

        // Apply Jam
        toastExecutorService.execute(() -> Work.doWork("Jammer",peanutButterQueue, jamQueue, toast -> {
            toast.jamToast();
            System.out.println(String.format("Adding jam to toast : %s", toast.getId()));
        })
        );

        // Apply jelly
        toastExecutorService.execute(() -> Work.doWork("Jelly",jamQueue, finalQueue, toast -> {
            toast.jellyToast();
            System.out.println(String.format("Adding jelly to toast : %s", toast.getId()));
        })
        );

        TimeUnit.SECONDS.sleep(10);
        System.out.println("Shutting down executor gracefully");
        toastExecutorService.shutdownNow();
    }
}
