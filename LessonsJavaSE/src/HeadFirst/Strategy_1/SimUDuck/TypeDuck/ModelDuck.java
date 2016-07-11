package HeadFirst.Strategy_1.SimUDuck.TypeDuck;

import HeadFirst.Strategy_1.SimUDuck.Duck;
import HeadFirst.Strategy_1.SimUDuck.Fly.FlyNoWay;
import HeadFirst.Strategy_1.SimUDuck.Quack.Quack;

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
