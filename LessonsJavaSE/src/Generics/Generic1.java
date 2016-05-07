package Generics;

//class Box<T extends Number>
class Box<T> {
    private T item;

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public String showType() {
        return item.getClass().getName();
    }
}

public class Generic1 {
    public static void main(String[] args) {
        Box<Integer> box1 = new Box<>();
        Box<String> box2 = new Box<>();
        box1.setItem(5);
        box2.setItem("Мусор");
        System.out.println("box1: " + box1.getItem() + ", " + box1.showType());
        System.out.println("box2: " + box2.getItem() + ", " + box2.showType());
    }
}
