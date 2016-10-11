package LessonsJavaCore.IO;

import java.io.*;

class InputOutputStreamExam {

    // класс для чтения файла
    private InputStream inputStream;

    // класс для записи в файл
    private OutputStream outputStream;

    // путь к файлу который будем читать и записывать
    private String path;

    public InputOutputStreamExam(String path) {
        this.path = path;
    }

    // чтение файла используя InputStream
    public void read() throws IOException {
        // инициализируем поток на чтение
        inputStream = new FileInputStream(path);

        // читаем первый символ в байтах
        int data;
        char content;
        // по байтово в ASCII читаем весь файл
        while((data = inputStream.read()) != -1) {
            // преобразуем полученный байт в символ
            content = (char) data;
            // выводим посимвольно
            System.out.print(content);
        }
        // закрываем поток
        inputStream.close();
    }

    // запись в файл используя OutputStream
    public void write(String st) throws IOException {
        // инициализируем поток для вывода данных
        // что позволит нам записать новые данные в файл
        outputStream = new FileOutputStream(path);
        // передаем полученную строку st и приводим её к byte массиву.
        outputStream.write(st.getBytes());
        // закрываем поток вывода
        // только после того как мы закроем поток данные попадут в файл.
        outputStream.close();
    }
}

public class IOExample {

    // указываем путь к файлу с которым мы будем работать
    private static final String PATH = "D://Downloads/Удали меня4.txt";

    // создаем экземпляр нашего класса который мы создали в первом шаге
    private static InputOutputStreamExam streamExam;

    public static void main(String... args) throws IOException {
        // инициализируем наш класс для работы с файлом
        streamExam = new InputOutputStreamExam(PATH);

        // вызываем метод чтения файла
        streamExam.read();

        // вызываем метод записи в файл
        streamExam.write("Hello World!");
    }

}