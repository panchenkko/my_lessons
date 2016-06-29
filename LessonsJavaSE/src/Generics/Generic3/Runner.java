package Generics.Generic3;

public class Runner {

    public static void main(String[] args) {
        Camera camera1 = new Camera("Nokia", 3.99, 8.0);
        Camera camera2 = new Camera("Nokia", 3.99, 9.0);

        System.out.println(camera1.toString(camera2));

        Phone phone1 = new Phone();
//        camera1.compareTo(phone1); // Ошибка! Сравнить можем только одинаковые классы!
    }
}
