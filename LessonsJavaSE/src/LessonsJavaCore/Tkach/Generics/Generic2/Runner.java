package LessonsJavaCore.Tkach.Generics.Generic2;

import java.util.ArrayList;
import java.util.List;

public class Runner {

    /**
     * Сравнение двух обобщенных параметров
     * @param list Ловит класс Product и все наследованные от него
     * @param p Ловит класс Product и все наследованные от него
     * @return Метод может сравнивать разные классы и не всегда проверка будет логична
     */
    public boolean find1(List<? extends Product> list, Product p) {
        for (Product product : list) {
            if (product.getClass() == p.getClass())
                return true;
        }
        return false;
    }

    /**
     * Сравнение двух обобщенных параметров
     * @param list Ловит класс Product и все наследованные от него
     * @param p Создается ограничение по классам и нужно внести тот же класс, что и первый параметр
     * @return Метод сравнивает только одинаковые классы, так как обощенный тип C автоматом передается во все параметры
     */
    public <T extends Product> boolean find2(List<T> list, T p) {
        for (T product : list) {
            if (product.getClass() == p.getClass())
                return true;
        }
        return false;
    }

    /**
     * Копирование списка
     * @param src Считываем лист (обощенный тип наследуемый от класса Product)
     * @param dest Записываем в новый лист (считываем переданный тип в первом параметре и если нет совпадения - Ошибка!)
     *
     *                           IN, OUT АРГУМЕНТЫ
     * IN - Если получаем, пишем "extends". В данном примере это параметр "src"
     * OUT - Если записываем, пишем "super". В данном примере это параметр "dest"
     */
    public void copy(List<? extends Product> src, List<? super Product> dest) {
        for (Product p : src) {
            dest.add(p);
        }
    }

    public static void main(String[] args) {
        Runner runner = new Runner();

        List<Camera> cameras = new ArrayList<>();

        runner.find1(cameras, new Camera());
        runner.find1(cameras, new Phone());

        runner.find2(cameras, new Camera());
//        runner.find2(cameras, new Phone()); // Ошибка!

        List<Camera> cameras2 = new ArrayList<>();
//        runner.copy(cameras, cameras2); // Тут ошибки быть не должно.

//        List<Phone> phones = new ArrayList<>();
//        runner.copy(cameras, phones); // Ошибка!

        // Так делать нельзя!
//        List<? extends Product> list = new ArrayList<>();
//        List<? super Product> list = someWTFMethod();
    }
}
