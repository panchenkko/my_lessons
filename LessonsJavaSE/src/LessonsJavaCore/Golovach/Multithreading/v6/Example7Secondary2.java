package LessonsJavaCore.Golovach.Multithreading.v6;

public class Example7Secondary2 {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String spaces = spaces(i);
            Runnable printer = new PrintRunnable(spaces + i, 100);
            Thread t = new Thread(printer);
            t.start();
            Thread.sleep(100);
        }
    }

    private static String spaces(int count) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            result.append(" ");
        }
        return String.valueOf(result);
    }
}
