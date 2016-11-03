package Books.HeadFirst.Strategy_1.SimUDuck.TypeDuck;

import Books.HeadFirst.Strategy_1.SimUDuck.Duck;
import Books.HeadFirst.Strategy_1.SimUDuck.Fly.FlyWithWings;
import Books.HeadFirst.Strategy_1.SimUDuck.Quack.Quack;

public class MallardDuck extends Duck {

    public MallardDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    @Override
    public void display() {
        System.out.println("I'm a real Mallard duck");
    }
}
