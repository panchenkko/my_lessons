package LessonsJavaCore.Other.Multithreading;

class Counter {
    private int c = 0;

    // synchronized - чтобы к методу мог обратиться только один поток,
    // остальные заснут дожидаясь пока метод освободиться
    public synchronized int inc() {
        return c++;
    }

    public synchronized int dec() {
        return c--;
    }

    public synchronized int getC() {
        return c;
    }
}

class MyThreadRunnable implements Runnable {

    private Counter counter = null;

    public MyThreadRunnable(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int random = (int) (Math.random() * 2);
            if (random == 0)
                System.out.println(i + 1 + ". " + "Increment: " + counter.inc());
            else
                System.out.println(i + 1 + ". " + "Decrement: " + counter.dec());
        }
        System.out.println("Поток закончен! Конечный результат: " + counter.getC());
    }
}

public class ExampleThread3 {
    public static void main(String[] args) {
        Counter counter = new Counter();
        // Создаем поток и в параметр закидываем интерфейс Runnable, то бишь наш уже созданный класс MyThreadRunnable
        Thread thread1 = new Thread(new MyThreadRunnable(counter));
        Thread thread2 = new Thread(new MyThreadRunnable(counter));
        thread1.start();
        thread2.start();
    }
}
