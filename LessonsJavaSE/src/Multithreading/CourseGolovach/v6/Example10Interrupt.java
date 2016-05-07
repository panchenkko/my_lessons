package Multithreading.CourseGolovach.v6;

/**
 * Чтобы прекратить работу потока, есть несколько методов, но они действуют по разному.
 * Например метод nameThread.stop() - дейсвует жестче всех, он прекращает сразу же работу потока и не важно, какая
 * выполнялась работа в потоке. Сейчас этот метод объявлен как @Deprecated, так как разработчики java поняли, что
 * использовать его не лучший вариант. Этот метод может убить поток, когда этого делать нежелательно.
 * Есть ещё метод nameThread.destroy() - он тоже объявлен как @Deprecated, он действует не так жестко как метод stop(),
 * но его использование тоже нежелательное.
 *
 * Также существует метод nameThread.interrupt() - он убивает поток мягче всех. Он действует как флажок, он говорит
 * своему вспомогательному boolean методу nameThread.isInterrupted() поставить своё значение как true. Таким образом
 * при написании кода мы в нужном месте можем поставить флажок как true, а в другом месте проверить состояние этого
 * флажка и с помощью условий сделать разную реализацию.
 *
 * Например:
 *      while (true) {
 *          if (nameThread.isInterrupted()) {
 *              return;
 *          } else {
 *              ...
 *          }
 *      }
 *
 * Важно ещё понимать, что если флажок выставлен как true и происходит вызов таких методов как Thread.sleep(), t.join()
 * t.wait(), то вылетит InterruptedException, это потому, что данные методы предупреждены вызовом этого исключения
 */

public class Example10Interrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread myThread = Thread.currentThread();
                while (true) {
                    System.out.println(myThread.isInterrupted());
//                    for (long i = 0; i < 3_000_000_00L; i++) ;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println("Поток прекратил свою работу!");
                        break;
                    }
                }
            }
        });
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
