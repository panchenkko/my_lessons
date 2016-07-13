package HeadFirst.Factory_4.PizzaStore.RegionalStores.AbstractClass;

import HeadFirst.Factory_4.PizzaStore.Pizza;

public abstract class PizzaStore {

    public Pizza orderPizza(String type) {
        Pizza pizza;

        pizza = createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    protected abstract Pizza createPizza(String type);
}
