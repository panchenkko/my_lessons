package LessonsJavaCore.Other.Multithreading.CourseGolovach.v6.Example9Monitor.Optimization;

import java.util.concurrent.TimeoutException;

public class ProducedTimed implements Runnable {
    private int startValue; // С какого значения начнется отсчет элементов в производстве
    private final int period; // Время сна между изготовлениями
    private final SingleElementBufferTimed buffer;
    private final long timeout; // Вреся сна, после какого он должен проверить, не появилась ли работа для него

    public ProducedTimed(int startValue, int timeSleep, SingleElementBufferTimed buffer, long timeout) {
        this.startValue = startValue;
        this.period = timeSleep;
        this.buffer = buffer;
        this.timeout = timeout;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(startValue + " produced");
                buffer.put(startValue++, timeout);
                Thread.sleep(period);
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
