package HeadFirst.Factory_4.FactoryMethod.PizzaStore.RegionalStores;

import HeadFirst.Factory_4.FactoryMethod.PizzaStore.NYPizzaType.NYStyleCheesePizza;
import HeadFirst.Factory_4.FactoryMethod.PizzaStore.NYPizzaType.NYStyleClamPizza;
import HeadFirst.Factory_4.FactoryMethod.PizzaStore.NYPizzaType.NYStylePepperoniPizza;
import HeadFirst.Factory_4.FactoryMethod.PizzaStore.NYPizzaType.NYStyleVeggiePizza;
import HeadFirst.Factory_4.FactoryMethod.PizzaStore.Pizza;
import HeadFirst.Factory_4.FactoryMethod.PizzaStore.RegionalStores.AbstractClass.PizzaStore;

public class NYPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        switch (type) {
            case "cheese":    return new NYStyleCheesePizza();
            case "veggie":    return new NYStyleVeggiePizza();
            case "clam":      return new NYStyleClamPizza();
            case "pepperoni": return new NYStylePepperoniPizza();
            default:          return null;
        }
    }
}
