package LessonsJavaCore.Golovach.Multithreading.v6.Example9Monitor;

/**
 * Мы создали утильный класс, для перехвата исключения и удобочитаемости при вызове Thread.sleep()
 */
public class ThreadUtils {
    public static void sleep(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
