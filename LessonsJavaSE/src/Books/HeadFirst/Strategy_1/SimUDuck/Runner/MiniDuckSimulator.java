package Books.HeadFirst.Strategy_1.SimUDuck.Runner;

import Books.HeadFirst.Strategy_1.SimUDuck.Duck;
import Books.HeadFirst.Strategy_1.SimUDuck.Fly.FlyRocketPowered;
import Books.HeadFirst.Strategy_1.SimUDuck.TypeDuck.MallardDuck;
import Books.HeadFirst.Strategy_1.SimUDuck.TypeDuck.ModelDuck;

public class MiniDuckSimulator {
    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.performQuack();
        mallard.performFly();

        Duck model = new ModelDuck();
        model.performFly();
        model.setFlyBehavior(new FlyRocketPowered());
        model.performFly();
    }
}
