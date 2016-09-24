package LessonsJavaCore.Other.Multithreading.CourseGolovach.v6.Example9Monitor;

/**
 * Этот класс выступает в роли "Производителя".
 * Мы производим данные с указанным интервалом.
 * Выводим номер производимого элемента, вносим в список и спим указанное время
 */

public class Producer implements Runnable {

    private int startValue;
    private final int period;
    private final SingleElementBuffer buffer;

    public Producer(int startValue, int period, SingleElementBuffer buffer) {
        this.startValue = startValue;
        this.period = period;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(startValue + " produced");
                buffer.put(startValue++);
                Thread.sleep(period);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " error!");
                break;
            }
        }
    }
}
