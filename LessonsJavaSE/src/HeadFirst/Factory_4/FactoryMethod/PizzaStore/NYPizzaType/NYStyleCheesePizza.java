package HeadFirst.Factory_4.FactoryMethod.PizzaStore.NYPizzaType;

import HeadFirst.Factory_4.FactoryMethod.PizzaStore.Pizza;

public class NYStyleCheesePizza extends Pizza {
    public NYStyleCheesePizza() {
        name = "NY Style Sauce and Cheese Pizza";
        dough = "Thin Crust Dough";
        sauce = "Marinara Sauce";

        toppings.add("Grated Reggiano Cheese");
    }
}
