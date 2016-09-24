package LessonsJavaCore.Other.Multithreading.CourseGolovach.v6;

class PrintRunnable implements Runnable {

    private String msg;
    private int milliseconds;

    public PrintRunnable(String msg, int milliseconds) {
        this.msg = msg;
        this.milliseconds = milliseconds;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(milliseconds);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(msg);
        }
    }
}

public class Example7Secondary {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new PrintRunnable("A   .", 100));
            t.start();
            Thread t2 = new Thread(new PrintRunnable(".   B", 99));
            t2.start();

            t.join();
            t2.join();

            System.out.println("-----");
            Runnable run = new PrintRunnable(". C .", 100);
            run.run();
            System.out.println("-----");
        }
    }
}
