package LessonsJavaCore.Golovach.Multithreading.v6;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Если мы перед выводом переменной counter дождемся конца двух потоков, и даже если сделаем counter volatile, то всё
 * равно не выведет число N. Всё потому что каждый поток перезаписывает результат другого потока. Они не успевают дать
 * друг другу знать, что он уже заинкрементил и значение переменной изменилось. Операция не атомарна.
 * Чтобы исправить эту ситуацию, есть класс AtomicInteger, он делает все операции над этой переменной атомарными.
 * Чтение, инкрементация, запись - происходит атомарно, только после этих операций другой поток может взять
 * эту переменную.
 *
 * Так же есть дополнительный вариант. Переменную counter можно занести в синхронизированный метод, где будет
 * происходить сама инкрементация. Таким образом инкрементировать переменную сможет только один поток.
 */

public class Example4Atomic {
    public static final int N = 1_000_000_000;
//    public static volatile int counter = 0;
    public static AtomicInteger counter = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        Thread t0 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < N; i++) {
                    counter.incrementAndGet();
                }
            }
        });
        t0.start();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < N; i++) {
                    counter.incrementAndGet();
                }
            }
        });
        t1.start();

        t0.join();
        t1.join();

        System.out.println(counter);
    }
}
