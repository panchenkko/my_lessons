package LessonsJavaCore.Other.Multithreading;

class MyThread extends Thread {

    public MyThread(String str) {
        super(str);
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " " + getName());
            // Если сон потока прервется по каким либо причинам, будет вызван InterruptedException
            try {
                sleep((long)(Math.random() * 1000));
            } catch (InterruptedException ignored) {}
        }
        System.out.println("= DONE: " + getName() + " =");
    }

}

public class ExampleThread2 {
    public static void main (String[] args) {
        new MyThread("Do it!").start();
        new MyThread("Definitely not!").start();
    }
}