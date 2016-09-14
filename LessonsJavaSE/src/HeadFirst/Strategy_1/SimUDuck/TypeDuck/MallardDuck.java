package HeadFirst.Strategy_1.SimUDuck.TypeDuck;

import HeadFirst.Strategy_1.SimUDuck.Duck;
import HeadFirst.Strategy_1.SimUDuck.Fly.FlyWithWings;
import HeadFirst.Strategy_1.SimUDuck.Quack.Quack;

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
