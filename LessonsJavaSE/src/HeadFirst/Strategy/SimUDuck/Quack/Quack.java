package HeadFirst.Strategy.SimUDuck.Quack;

public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Quack");
    }
}
