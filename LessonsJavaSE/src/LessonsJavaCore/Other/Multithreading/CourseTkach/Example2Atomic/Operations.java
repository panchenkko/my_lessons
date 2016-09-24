package LessonsJavaCore.Other.Multithreading.CourseTkach.Example2Atomic;

import java.util.concurrent.TimeUnit;

public class Operations {

    static void transfer(Account acc1, Account acc2, int amount) throws Exception {
        if (acc1.getBalance() < amount)
            throw new Exception();

        if (acc1.getLock().tryLock(5L, TimeUnit.SECONDS)) {
            try {
                if (acc2.getLock().tryLock(5L, TimeUnit.SECONDS)) {
                    acc1.getLock().lock();
                    acc2.getLock().lock();
                    try {
                        acc1.withdraw(amount);
                        acc2.deposit(amount);
                    } finally {
                        acc2.getLock().unlock();
                    }
                } else {
                    acc2.incFailedTransferCount();
                }
            } finally {
                acc1.getLock().unlock();
            }
        } else {
            acc1.incFailedTransferCount();
        }
        System.out.println("Передача прошла успешно!");
    }

    public static void main(String[] args) {
        final Account a = new Account(1000);
        final Account b = new Account(2000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    transfer(a, b, 500);
                } catch (Exception e) {
                    System.out.println("Недостаточный баланс!");
                }
            }
        }).start();

        try {
            transfer(b, a, 300);
        } catch (Exception e) {
            System.out.println("Недостаточный баланс!");
        }
    }
}
