package LessonsJavaCore.Other.Generics.Generic3;

/**
 * Обобщенный тип "Т".
 * Все кто вызовут этот класс, сразу должны будут сказать какой тип вставить вместо "Т"
 * Так как класс Product генерализированный тип,
 * а это значит что он может принять Любой тип, поэтому мы должны уточнить "Product<T extends Product>".
 * Все переданные типы должны быть на уровне класса Product или ниже.
 * И опять же. Так как класс Product генерализированный, мы должны добавить генерик: "Product<T extends Product<T>>".
 * Это называется "Рекурсивное расширение типа".
 */
public abstract class Product<T extends Product<T>> implements Comparable<T> {

    private String name;
    private double price;

    public Product() {
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    /**
     * Проверяем данные на совпадение в дочерних классах.
     * Этот класс вызывается в случае совпадения данных в род. классе.
     *
     * Таким образом, мы изначально проверяем родительский класс, а после в случае совпадения переходим к дочерним.
     */
    abstract int subCompare(T p);

    @Override
    public int compareTo(T p) {
        if (Double.compare(this.price, p.getPrice()) == 0)
            return subCompare(p);
        return Double.compare(this.price, p.getPrice());
    }

    public String toString(T p) {
        if (compareTo(p) == 0)
            return "Данные " + this.name + " и " + p.getName() + " совпали";
        else
            return "Данные не совпали! (" + this.name + " и " + p.getName() + ")";
    }
}
