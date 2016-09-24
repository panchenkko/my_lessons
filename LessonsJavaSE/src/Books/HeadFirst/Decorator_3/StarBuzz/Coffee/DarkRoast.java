package Books.HeadFirst.Decorator_3.StarBuzz.Coffee;

import Books.HeadFirst.Decorator_3.StarBuzz.Beverage;

public class DarkRoast extends Beverage {

    public DarkRoast() {
        description = "Dark Roast Coffee";
    }

    @Override
    public double cost() {
        return .99;
    }
}
