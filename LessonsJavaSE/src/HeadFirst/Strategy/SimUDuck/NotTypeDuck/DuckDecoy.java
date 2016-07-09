package HeadFirst.Strategy.SimUDuck.NotTypeDuck;

import HeadFirst.Strategy.SimUDuck.Duck;
import HeadFirst.Strategy.SimUDuck.Fly.FlyNoWay;
import HeadFirst.Strategy.SimUDuck.Quack.Quack;

public class DuckDecoy extends Duck {

    public DuckDecoy() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("I'm not duck");
    }
}
