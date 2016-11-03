package LessonsJavaCore.IO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class OutputHtmlPage {

    public static void copyHtmlPage(InputStream src, OutputStream dst) throws IOException {
        while (true) {
            int data = src.read();
            if (data != -1) {
                dst.write(data);
            } else {
                return;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // В url обязательно нужно записывать протокол, чтобы поток понимал какой протокол использовать
        InputStream src = new URL("http://fs.to/video/films/").openStream();
        OutputStream dst = new FileOutputStream("D://Downloads/Считанная html-страница.html");
//        copyHtmlPage(src, dst); // Данный вариант запишет всю html-страницу в файл
        copyHtmlPage(src, System.out); // Данный вариант виведет всю html-страницу в консоль
    }
}
