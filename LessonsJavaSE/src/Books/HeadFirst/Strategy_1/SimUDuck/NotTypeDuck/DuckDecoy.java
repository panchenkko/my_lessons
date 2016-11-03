package Books.HeadFirst.Strategy_1.SimUDuck.NotTypeDuck;

import Books.HeadFirst.Strategy_1.SimUDuck.Duck;
import Books.HeadFirst.Strategy_1.SimUDuck.Fly.FlyNoWay;
import Books.HeadFirst.Strategy_1.SimUDuck.Quack.Quack;

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
