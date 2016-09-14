package HeadFirst.Decorator_3.StarBuzz.Runner;

import HeadFirst.Decorator_3.StarBuzz.Addition.Mocha;
import HeadFirst.Decorator_3.StarBuzz.Addition.Soy;
import HeadFirst.Decorator_3.StarBuzz.Addition.Whip;
import HeadFirst.Decorator_3.StarBuzz.Beverage;
import HeadFirst.Decorator_3.StarBuzz.Coffee.DarkRoast;
import HeadFirst.Decorator_3.StarBuzz.Coffee.Espresso;
import HeadFirst.Decorator_3.StarBuzz.Coffee.HouseBlend;

public class StarBuzzCoffee {
    public static void main(String[] args) {
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + " $" + beverage.cost());

        Beverage beverage2 = new DarkRoast();
        beverage2 = new Mocha(beverage2);
        beverage2 = new Mocha(beverage2);
        beverage2 = new Whip(beverage2);
        System.out.println(beverage2.getDescription() + " $" + beverage2.cost());

        Beverage beverage3 = new HouseBlend();
        beverage3 = new Soy(beverage3);
        beverage3 = new Mocha(beverage3);
        beverage3 = new Whip(beverage3);
        System.out.println(beverage3.getDescription() + " $" + beverage3.cost());
    }
}
