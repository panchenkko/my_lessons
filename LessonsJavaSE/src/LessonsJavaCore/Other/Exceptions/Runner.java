package LessonsJavaCore.Other.Exceptions;

// return в try, catch не работает, сработает только в finally.

public class Runner {
    public static void main(String[] args) {
        // Создаем наш класс для генерации исключений
        Generator generator = new Generator();

        // Данный блок будет обрабатывать исключение
        // и оно там действительно возникнет - мы же передали null
        try {
            String answer = generator.helloMessage(null);
            System.out.println("Answer 1 : " + answer);
        } catch (SimpleException ex) {
            // Здесь мы можем обработать объект-исключение,
            // получить некоторую информаицию
            System.out.println("Error code : " + ex.getErrorCode());
            System.out.println("Error message : " + ex.getMessage());
        } finally {
            System.out.println("Этот блок вызываетя всегда");
        }

        // Данный блок будет обрабатывать исключение
        // но его не будет - мы передали корректный параметр
        try {
            String answer = generator.helloMessage("Yoda");
            System.out.println("Answer 2 : " + answer);
        } catch (SimpleException ex) {
            // Здесь мы можем обработать объект-исключение,
            // получить некоторую информаицию
            System.out.println("Error : " + ex.getMessage());
        } finally {
            System.out.println("Этот блок вызываетя всегда");
        }
    }
}