package HeadFirst.Decorator_3.StarBuzz.Addition;

import HeadFirst.Decorator_3.StarBuzz.Beverage;
import HeadFirst.Decorator_3.StarBuzz.CondimentDecorator;

public class Whip extends CondimentDecorator {
    Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Whip";
    }

    @Override
    public double cost() {
        return .10 + beverage.cost();
    }
}
