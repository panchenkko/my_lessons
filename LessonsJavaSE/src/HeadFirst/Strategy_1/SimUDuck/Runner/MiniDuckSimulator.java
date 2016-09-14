package HeadFirst.Strategy_1.SimUDuck.Runner;

import HeadFirst.Strategy_1.SimUDuck.Duck;
import HeadFirst.Strategy_1.SimUDuck.Fly.FlyRocketPowered;
import HeadFirst.Strategy_1.SimUDuck.TypeDuck.MallardDuck;
import HeadFirst.Strategy_1.SimUDuck.TypeDuck.ModelDuck;

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
