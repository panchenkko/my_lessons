package LessonsJavaCore.Other.Multithreading.CourseGolovach.v6;

/**
 * Мы синхронизировались по одному объекту, в разных потоках. Искуственно зависли в синхронизации и когда
 * поток main доходит до синхронизации он становится в очередь, так как объект лок не вышел с первой синхронизации.
 *
 * - Нельзя синхронизироваться по объекту, какой равен null
 * - Только в синхронизированных методах можно вызывать методы монитора (wait, notify, notifyAll)
 *
 *                                              - Концепция Монитора -
 * synchronized - если блок кода имеет это ключевое слово, то в него может зайти только один поток, остальные переходят
 *                в blocked set и ждут пока поток выйдет с этого блока, после собравшейся очереди JVM случайным
 *                образом определяет какой поток следующим зайдет в этот блок
 *
 * wait()       - Мы усыпили поток, в каком вызвали этот метод, он проснется когда другой поток вызовет метод notify()
 *
 * notify()     - пробуждает вызванный поток, какой уснул из-за метода wait()
 *
 * notifyAll()  - пробудит все потоки, какие уснули из-за метода wait()
 */

public class Example8Synchronized {
    public static void main(String[] args) throws InterruptedException {
        final Object lock = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("RUN: started");
                synchronized (lock) {
                    System.out.println("RUN: I`m in!");
                    while (true);
                }
            }
        }).start();
        System.out.println("MAIN: Start sleep");
        Thread.sleep(1000);
        System.out.println("MAIN: Stop sleep");
        synchronized (lock) {
            System.out.println("MAIN: I`m in!");
        }
    }
}
