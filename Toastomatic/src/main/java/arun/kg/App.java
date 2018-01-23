package arun.kg;

import java.util.concurrent.*;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Toast> dryQueue = new LinkedBlockingQueue<>(),
                butterQueue = new LinkedBlockingQueue<>(),
                peanutButterQueue = new LinkedBlockingQueue<>(),
                jamQueue = new LinkedBlockingQueue<>(),
                finalQueue = new LinkedBlockingQueue<>();

        ExecutorService toastExecutorService = Executors.newFixedThreadPool(5);

        toastExecutorService.execute(new Toaster(dryQueue));

        // Apply butter
        toastExecutorService.execute(() -> {
                    Work.doWork(dryQueue, butterQueue, toast -> {
                        toast.butterToast();
                        System.out.println(String.format("Adding butter to toast: %s", toast.getId()));
                    });
                }
        );

        // Apply peanutButter
        toastExecutorService.execute(() -> {
                    Work.doWork(butterQueue, peanutButterQueue, toast -> {
                        toast.peanutButterToast();
                        System.out.println(String.format("Adding peanut butter to  toast: %s", toast.getId()));
                    });
                }
        );

        // Apply Jam
        toastExecutorService.execute(() -> {
                    Work.doWork(peanutButterQueue, jamQueue, toast -> {
                        toast.jamToast();
                        System.out.println(String.format("Adding jam to toast : %s", toast.getId()));
                    });
                }
        );

        // Apply jelly
        toastExecutorService.execute(() -> {
                    Work.doWork(jamQueue, finalQueue, toast -> {
                        toast.jellyToast();
                        System.out.println(String.format("Adding jelly to toast : %s", toast.getId()));
                    });

                }
        );

        TimeUnit.SECONDS.sleep(10);
        toastExecutorService.shutdownNow();
    }
}
