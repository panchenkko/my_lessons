package LessonsJavaCore.Golovach.Multithreading.v6;

public class Example1FirstThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new XRunnable());
        t.start();

        for (int i = 0; i < 1000; i++) {
            Thread.sleep(120);
            System.out.println("main: " + i);
        }
    }
}

class XRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException ignored) {/*NOP*/}
            System.out.println("run: " + i);
        }
    }
}
