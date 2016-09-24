package Books.HeadFirst.Factory_4.FactoryMethod.PizzaStore.RegionalStores;

import Books.HeadFirst.Factory_4.FactoryMethod.PizzaStore.NYPizzaType.NYStyleCheesePizza;
import Books.HeadFirst.Factory_4.FactoryMethod.PizzaStore.NYPizzaType.NYStyleClamPizza;
import Books.HeadFirst.Factory_4.FactoryMethod.PizzaStore.NYPizzaType.NYStylePepperoniPizza;
import Books.HeadFirst.Factory_4.FactoryMethod.PizzaStore.NYPizzaType.NYStyleVeggiePizza;
import Books.HeadFirst.Factory_4.FactoryMethod.PizzaStore.Pizza;
import Books.HeadFirst.Factory_4.FactoryMethod.PizzaStore.RegionalStores.AbstractClass.PizzaStore;

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
