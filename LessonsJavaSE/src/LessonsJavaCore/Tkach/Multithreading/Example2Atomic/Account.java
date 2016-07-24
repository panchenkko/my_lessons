package LessonsJavaCore.Tkach.Multithreading.Example2Atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private Lock lock;
    private int balance;
    private AtomicInteger failCounter;

    public Account(int initialBalance) {
        this.balance = initialBalance;
        this.lock = new ReentrantLock();
    }

    public AtomicInteger getFailCounter() {
        return failCounter;
    }

    public Lock getLock() {
        return lock;
    }

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void incFailedTransferCount() {
        failCounter.incrementAndGet();
    }
}
