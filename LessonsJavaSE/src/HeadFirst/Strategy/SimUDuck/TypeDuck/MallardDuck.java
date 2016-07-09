package HeadFirst.Strategy.SimUDuck.TypeDuck;

import HeadFirst.Strategy.SimUDuck.Duck;
import HeadFirst.Strategy.SimUDuck.Fly.FlyWithWings;
import HeadFirst.Strategy.SimUDuck.Quack.Quack;

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
