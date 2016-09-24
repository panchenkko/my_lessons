package Books.HeadFirst.Strategy_1.SimUDuck.Quack;

public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Squeak");
    }
}
