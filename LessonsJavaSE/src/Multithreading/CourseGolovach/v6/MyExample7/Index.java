package Multithreading.CourseGolovach.v6.MyExample7;

public class Index implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            Thread a = new Thread(new PrintRunnable("A   .", 100));
            a.start();
            Thread b = new Thread(new PrintRunnable(".   B", 99));
            b.start();

            try {
                a.join();
                b.join();

                System.out.println("-----");
                Thread c = new Thread(new PrintRunnable(". C .", 100));
                c.start();
                c.join();
                System.out.println("-----");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
