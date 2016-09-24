package LessonsJavaCore.Other.Multithreading.CourseGolovach.v6.Example9Monitor;

/**
 * Этот класс выступает в роли "Потребителя".
 * Мы ждем пока в списке не появятся данные, после их считываем и выводим номер элемента какой считали со списка
 */

public class Consumer implements Runnable {

    private final SingleElementBuffer buffer;

    public Consumer(SingleElementBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Integer elem = buffer.get();
                System.out.println(elem + " consumed");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " error!");
                return;
            }
        }
    }
}
