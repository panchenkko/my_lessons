package LessonsJavaCore.Other.Generics.Generic3;

public class Phone extends Product<Phone> {

    private String model;

    public Phone() {
    }

    @Override
    int subCompare(Phone p) {
        return this.model.compareTo(p.getModel());
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
