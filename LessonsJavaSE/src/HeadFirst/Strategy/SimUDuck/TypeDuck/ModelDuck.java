package HeadFirst.Strategy.SimUDuck.TypeDuck;

import HeadFirst.Strategy.SimUDuck.Duck;
import HeadFirst.Strategy.SimUDuck.Fly.FlyNoWay;
import HeadFirst.Strategy.SimUDuck.Quack.Quack;

public class ModelDuck extends Duck {

    public ModelDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("I'm model duck");
    }
}
