package HeadFirst.Factory_4.FactoryMethod.PizzaStore.RegionalStores.AbstractClass;

import HeadFirst.Factory_4.FactoryMethod.PizzaStore.Pizza;

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
