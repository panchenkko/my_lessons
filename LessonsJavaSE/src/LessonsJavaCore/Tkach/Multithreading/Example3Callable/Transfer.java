package LessonsJavaCore.Tkach.Multithreading.Example3Callable;

import LessonsJavaCore.Tkach.Multithreading.Example2Atomic.Account;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Transfer implements Callable<Boolean> {
    private int id;
    private Account accFrom;
    private Account accTo;
    private int amount;

    public Transfer(int id, Account accFrom, Account accTo, int amount) {
        this.id = id;
        this.accFrom = accFrom;
        this.accTo = accTo;
        this.amount = amount;
    }

    @Override
    public Boolean call() throws Exception {
        if (accFrom.getLock().tryLock(3L, TimeUnit.SECONDS)) {
            try {
                if (accFrom.getBalance() < amount)
                    throw new Exception(id + ". " + "Недостаточный баланс!");

                if (accTo.getLock().tryLock(3L, TimeUnit.SECONDS)) {
                    accFrom.getLock().lock();
                    accTo.getLock().lock();
                    try {
                        accFrom.withdraw(amount);
                        accTo.deposit(amount);
                        Thread.sleep(new Random().nextInt(5000));
                    } finally {
                        accTo.getLock().unlock();
                    }
                } else {
                    accTo.incFailedTransferCount();
                    return false;
                }
            } finally {
                accFrom.getLock().unlock();
            }
        } else {
            accFrom.incFailedTransferCount();
            return false;
        }
        System.out.println(id + ". " + "Передача прошла успешно!");
        return true;
    }
}
