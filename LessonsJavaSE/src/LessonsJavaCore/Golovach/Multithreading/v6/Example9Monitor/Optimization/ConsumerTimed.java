package LessonsJavaCore.Golovach.Multithreading.v6.Example9Monitor.Optimization;

import java.util.concurrent.TimeoutException;

/**
 * Этот класс выступает в роли "Потребителя".
 * Мы ждем пока в списке не появятся данные, после их считываем и выводим номер элемента какой считали со списка
 */

public class ConsumerTimed implements Runnable {

    private final SingleElementBufferTimed buffer;
    private final long timeout;

    public ConsumerTimed(SingleElementBufferTimed buffer, long timeout) {
        this.buffer = buffer;
        this.timeout = timeout;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Integer elem = buffer.get(timeout);
                System.out.println(elem + " consumed");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " error!");
                return;
            } catch (TimeoutException e) {
                System.out.println(Thread.currentThread().getName() + " timeout!");
                return;
            }
        }
    }
}
