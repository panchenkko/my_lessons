package LessonsJavaCore.Golovach.Multithreading.v6;

/**
 * Яркий пример того, что приоритеты к потоку не всегда дают нужный результат.
 * Поэтому приоритеты считаются костылем в программировании.
 *
 * Мы создаем массив потоков, после каждому присваиваем увеличивать переменную до огромного числа, до момента, пока
 * переменная stop не будет равняться true и вывести число, до какого оно заинкрементировало.
 * После первым 16-ти потокам задаем минимальный приоритет, а остальным 16-ть задаем приоритет выше.
 * После их запускаем.
 * Ждем секунду и останавливаем все потоки.
 *
 * После видим что много потоков с приоритетом выше даже не начали свою работу, в то время как большинство потоков
 * с низким приоритетом работали на максимум.
 *
 * Доп.
 * Метод yield тоже считается костылем в программировании.
 *
 * Доп.
 * Иногда потоки могут ускорить работу программы, если её разделить на несколько частей. И создать несколько потоков
 * чтобы каждий выполнял её часть, таким образом программа завершится быстрее. Также нужно учитывать что одним потоком
 * занято одно ядро. Поэтому потоков не должно быть больше чем ядер на компьютере, в ином случае будет только визуально
 * казаться что все потоки выполняются параллельно, на самом деле одно ядро будет управлять несколькими потоками и
 * каждому давать по n времени на выполнение. Пока один поток выполняет свою работу другой поток спит.
 *
 * Чтобы узнать сколько ядер на компьютере у пользователя, есть метод возвращающий int
 * Runtime.getRuntime().availableProcessors();
 */

public class Example5Priority {
    public static volatile boolean stop = false;
    public static void main(String[] args) throws InterruptedException {
        final Thread[] threads = new Thread[32];
        for (int i = 0; i < 32; i++) {
            final int finalI = i;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (long index = 0; index < 1000_000_000_000_000_000L; index++) {
                        if (stop) {
                            System.out.println(finalI + ": " + index);
                            break;
                        }
                    }
                }
            });
        }
        for (int i = 0; i < 32; i++) {
            threads[i].setPriority(i < 16 ? Thread.MIN_PRIORITY : Thread.NORM_PRIORITY);
        }
        for (int i = 0; i < 32; i++) {
            threads[i].start();
        }

        Thread.sleep(1000);

        stop = true;
    }
}
