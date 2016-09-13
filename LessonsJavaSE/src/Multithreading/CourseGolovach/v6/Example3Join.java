package Multithreading.CourseGolovach.v6;

/**
 * При вызове метода join мы говорим, что хотим дождаться конца этого потока и только потом продолжить работу
 * основого потока
 *
 * Недетерминированность - когда результат определить невозможно
 * Детерминированность — определённость, ясность, конкретность, чёткость, точность
 * (Многопоточность является недетерминированной)
 *
 * Чтобы любую многопоточную программу сделать детерменированной, используется метод join.
 * Таким образом исход программы всегда будет один и тот же.
 *
 * Доп.
 * У каждого потока своя временная полоса. К примеру, если мы без пяти двенадцать присвоили x = 1 в одном потоке и
 * в полночь хотим прочитать значение x в другом потоке, то не факт что x в этом потоке будет равен 1.
 * НО в потоке где мы присвоили x = 1, x уже всегда будет равен 1
 */

public class Example3Join {
    public static final int N = 1_000_000_000;
    public static int counter = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread t0 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < N; i++) {
                    counter++;
                }
            }
        });
        t0.start();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < N; i++) {
                    counter++;
                }
            }
        });
        t1.start();

        t0.join();
        t1.join();

//        Thread.sleep(1);

        System.out.println(counter);
    }
}
