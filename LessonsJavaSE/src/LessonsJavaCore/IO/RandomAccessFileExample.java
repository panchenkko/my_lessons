package LessonsJavaCore.IO;

import java.io.IOException;
import java.io.RandomAccessFile;

class WorkWithFile {

    private String path;

    // Экземпляр класса который обеспечит возможность
    // работать с файлом
    private RandomAccessFile file;

    // говорим конструктору проинициализировать путь к файлу
    public WorkWithFile(String path) {
        this.path = path;
    }

    // метод демонстрирует переход на указанный символ
    public long goTo(int num) throws IOException {
        // инициализируем класс RandomAccessFile
        // в параметры передаем путь к файлу
        // и модификатор который говорит, что файл откроется только для чтения
        file = new RandomAccessFile(path, "r");

        // переходим на num символ
        file.seek(num);

        // получаем текущее состояние курсора в файле
        long pointer = file.getFilePointer();
        file.close();

        return pointer;
    }

    // этот метод читает файл и выводит его содержимое
    public StringBuilder read() throws IOException {
        file = new RandomAccessFile(path, "r");
        StringBuilder res = new StringBuilder();
        int b;
        // побитово читаем символы и добавляем их в строку
        while((b = file.read()) != -1) {
            res.append((char) b);
        }
        file.close();

        return res;
    }

    // читаем файл с определенного символа
    public StringBuilder readFrom(int numberSymbol) throws IOException {
        // открываем файл для чтения
        file = new RandomAccessFile(path, "r");
        StringBuilder res = new StringBuilder();

        // ставим указатель на нужный вам символ
        file.seek(numberSymbol);
        int b;

        // побитово читаем и добавляем символы в строку
        while((b = file.read()) != -1){
            res.append((char) b);
        }
        file.close();

        return res;
    }

    // запись в файл
    public void write(String st) throws IOException {
        // открываем файл для записи
        // для этого указываем модификатор rw (read & write)
        // что позволит открыть файл и записать его
        file = new RandomAccessFile(path, "rw");

        // записываем строку переведенную в биты
        file.write(st.getBytes());

        // закрываем файл, после чего данные записываемые данные попадут в файл
        file.close();
    }

}

public class RandomAccessFileExample {
    public static void main(String[] args) throws IOException {
        // инициализируем класс передавая в него путь к файлу
        // так как мой файл лежит в корне проекта, то я просто указываю его имя
        WorkWithFile worker = new WorkWithFile("D://Downloads/Удали меня5.txt");

        // пишем передаваемый текст в файл
        worker.write("Give me a break from your take and your take. \n" +
                     "Come on and give me a break,\n" +
                     "what do you want from me?\n" +
                     "Feeding the rich with that son of a bitch.\n" +
                     "Well that son of a bitch,\n" +
                     "he looks just like me!\n" +
                     "Yeah, yeah");

        // переходим на указанный символ в файле
        System.out.println(worker.goTo(100));
        System.out.println("*******");

        // читаем весь файл
        System.out.println(worker.read());
        System.out.println("*******");

        // читаем файл с указанного символа
        System.out.println(worker.readFrom(105));
    }
}
