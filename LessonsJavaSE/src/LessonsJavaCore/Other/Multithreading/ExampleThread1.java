package LessonsJavaCore.Other.Multithreading;

/**
 * Два варианта реализации потоков
 *
 * Предпочтителен первый вариант (он более гибкий)
 */

class ImplementsRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("ImplementsRunnable, repeat: " + i);
        }
    }
}

class ExtendsThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("ExtendsThread, repeat: " + i);
        }
    }
}

public class ExampleThread1 {
    public static void main(String[] args) {
        new ExtendsThread().start();
        Thread t = new Thread(new ImplementsRunnable());
        t.start();
    }
}
