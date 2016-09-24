package LessonsJavaCore.Other.Generics.Generic2;

// Доп. пример
// А - класс или интерфейс
// B, C, D - только интерфейсы

//class Name<T extends A & B & C & D> {
//}
public class Container<T extends Product> {

    private T item;

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }
}
