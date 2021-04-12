import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MultiThread {

    public static void main(String... args) {
        ThreadPoolExecutor executor =
            (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        executor.submit(() -> {
            System.out.println(Thread.currentThread().getId());
            return null;
        });


        System.out.println(executor.getPoolSize());
        for (int i = 0; i < 100; i++) {
            executor.submit(() -> {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getId());
                return null;
            });
        }
    }
}

