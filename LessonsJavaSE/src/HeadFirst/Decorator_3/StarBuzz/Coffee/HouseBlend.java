package HeadFirst.Decorator_3.StarBuzz.Coffee;

import HeadFirst.Decorator_3.StarBuzz.Beverage;

public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "House Bland Coffee";
    }

    @Override
    public double cost() {
        return .89;
    }
}
