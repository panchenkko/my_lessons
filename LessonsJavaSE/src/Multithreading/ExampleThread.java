package Multithreading;

class MyThread extends Thread {

    public MyThread(String str) {
        super(str);
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " " + getName());
            try {
                sleep((long)(Math.random() * 1000));
            } catch (InterruptedException ignored) {}
        }
        System.out.println("= DONE: " + getName() + " =");
    }

}

public class ExampleThread {
    public static void main (String[] args) {
        new MyThread("Do it!").start();
        new MyThread("Definitely not!").start();
    }
}