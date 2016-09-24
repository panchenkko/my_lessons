package Books.HeadFirst.Decorator_3.StarBuzz.Coffee;

import Books.HeadFirst.Decorator_3.StarBuzz.Beverage;

public class Decaf extends Beverage {

    public Decaf() {
        description = "Decaf Coffee";
    }

    @Override
    public double cost() {
        return 1.05;
    }
}
