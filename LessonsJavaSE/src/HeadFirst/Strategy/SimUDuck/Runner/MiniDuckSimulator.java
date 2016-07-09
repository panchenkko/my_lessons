package HeadFirst.Strategy.SimUDuck.Runner;

import HeadFirst.Strategy.SimUDuck.Duck;
import HeadFirst.Strategy.SimUDuck.Fly.FlyRocketPowered;
import HeadFirst.Strategy.SimUDuck.TypeDuck.MallardDuck;
import HeadFirst.Strategy.SimUDuck.TypeDuck.ModelDuck;

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
