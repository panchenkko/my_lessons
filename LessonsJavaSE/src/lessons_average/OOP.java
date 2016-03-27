package lessons_average;

class Dog {
    private int age; // возраст
    private String name; // кличка
    static int count = 0;

    public Dog(String n, int a){
        name = n;
        age = a;
        count += 1;
    }
    public Dog(int a){ // конструктор добавляется в класс, если в момент создания объекта нужно выполнить какие-то действия
        name = "Незнакомец";
        age = a;
        count += 1;
    }
    public Dog(){
        name = "Незнакомец";
        count += 1;
    }

    public void voice() { // метод
        for (int i = 1; i <= age; i++) {
            System.out.println("гав-гав");
        }
    }
}

class BigDog extends Dog {
    public BigDog (String n, int a){
        super(n, a);
    }

    public void voice(){
        for (int i = 0; i < 10; i++) {
            System.out.println("ГАВ-ГАВ");
        }
    }
}

class Dalmatian extends Dog { // Наследование — это отношение между классами, при котором один класс расширяет функциональность другого.

    public Dalmatian(String n, int a) {
        super(n, a);
        count += 1;
    }
}

interface PriceItem {
    String getTitle();
    int getPrice(int count);
    String getDescription();
}

class Dog2 extends Dog implements PriceItem {
    private int price;
    public String getTitle() {
        return ("Умная собака");
    };
    public int getPrice(int count) {
        return price * count;
    };
    void setPrice(int p) {
        price = p;
    }
    public String getDescription() {
        return ("Умная собака, которая знает свой возраст и умеет сообщать его с помощью лая");
    }
}

public class OOP {
    public static void main(String[] args) {
        Dog dog1 = new Dog("Тузик", 2);
        dog1.voice();

        BigDog bigdog = new BigDog("Овчарка", 5);
        bigdog.voice();

        Dog2 dog = new Dog2();

        System.out.println(dog.getTitle());
        System.out.println(dog.getDescription());

        //dogs[10] = new lessons_average.Dog(){
        //    public void voice() {
        //        System.out.println("Я уникальная собака");
        //    }
        //};
        //dogs[10].voice();

        System.out.println("Всего было создано собак: " + Dog.count);
    }
}