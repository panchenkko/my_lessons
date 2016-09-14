package LessonsJavaCore.Tkach.Multithreading.Example1DeadLock;

import java.util.concurrent.TimeUnit;

/**
 * Для решения проблемы с deadlock-ом, нам пришлось в каждый класс аккаунт внести переменную Lock, чтобы в методе
 * transfer мы могли проверять, занят ли этот конкретный аккаунт в данный момент.
 * В методе transfer мы сначала проверяем, не занят ли аккаунт1, если к примеру занят, то мы ждем 5 секунд, и если
 * за это время он не смог зайти, то с else выполняем другую логику. Если всё таки зашел, то создаем блок try{}catch{},
 * проверяем, не занят ли второй аккаунт, если нет, то мы заходим в его блок лочим два аккаунт производим действия
 * смены баланса и обязательно в конце должны разлочить два аккаунта, иначе программа повиснет.
 */

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
                }
            } finally {
                acc1.getLock().unlock();
            }
        }
//        Синхронизируем доступ к обоим ресурсам (делаем потокобезопасным)
//        Этот вариант не устраняет полностью проблему
//        synchronized (acc1) {
//            Thread.sleep(1000);
//            synchronized (acc2) {
//                acc1.withdraw(amount);
//                acc2.deposit(amount);
//            }
//        }
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
