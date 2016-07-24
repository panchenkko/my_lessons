package LessonsJavaCore.Tkach.Generics.Generic2;

public class Phone extends Product {

    private String model;

    public Phone() {
    }

    public Phone(String model) {
        this.model = model;
    }

    public Phone(String name, double price, String model) {
        super(name, price);
        this.model = model;
    }

    public String getModel() {
        return model;
    }
}
