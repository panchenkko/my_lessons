package HeadFirst.Factory_4.PizzaStore.RegionalStores;

import HeadFirst.Factory_4.PizzaStore.ChicagoPizzaType.ChicagoStyleCheesePizza;
import HeadFirst.Factory_4.PizzaStore.ChicagoPizzaType.ChicagoStyleClamPizza;
import HeadFirst.Factory_4.PizzaStore.ChicagoPizzaType.ChicagoStylePepperoniPizza;
import HeadFirst.Factory_4.PizzaStore.ChicagoPizzaType.ChicagoStyleVeggiePizza;
import HeadFirst.Factory_4.PizzaStore.Pizza;
import HeadFirst.Factory_4.PizzaStore.RegionalStores.AbstractClass.PizzaStore;

public class ChicagoPizzaStore extends PizzaStore {

	public Pizza createPizza(String item) {
		switch (item) {
			case "cheese":    return new ChicagoStyleCheesePizza();
			case "veggie":    return new ChicagoStyleVeggiePizza();
			case "clam":      return new ChicagoStyleClamPizza();
			case "pepperoni": return new ChicagoStylePepperoniPizza();
			default:          return null;
		}
	}
}
