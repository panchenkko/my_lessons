package Multithreading.CourseGolovach.v6.MyExample7;

public class PrintRunnable implements Runnable {
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
