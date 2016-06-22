package Multithreading.CourseTkach.Example3Callable;

import Multithreading.CourseTkach.Example2Atomic.Account;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

public class Operations {
    public static void main(String[] args) throws InterruptedException {
        final Account a = new Account(1000);
        final Account b = new Account(2000);
        final Random rand = new Random();

        ExecutorService service = Executors.newFixedThreadPool(3);
        Future<Boolean> future;

        for (int i = 0; i < 10; i++) {
            service.submit(new Transfer(i + 1, a, b, rand.nextInt(400)));
        }

        service.shutdown();
//        service.awaitTermination(10L, TimeUnit.SECONDS);

        ScheduledExecutorService scheduledService = Executors.newSingleThreadScheduledExecutor();
        for (int i = 0; i < 10; i++) {
//            scheduledService.scheduleAtFixedRate();
        }
    }
}
