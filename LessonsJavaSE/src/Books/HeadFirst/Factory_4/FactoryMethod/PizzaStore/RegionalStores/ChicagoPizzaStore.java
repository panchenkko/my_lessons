package Books.HeadFirst.Factory_4.FactoryMethod.PizzaStore.RegionalStores;

import Books.HeadFirst.Factory_4.FactoryMethod.PizzaStore.ChicagoPizzaType.ChicagoStyleCheesePizza;
import Books.HeadFirst.Factory_4.FactoryMethod.PizzaStore.ChicagoPizzaType.ChicagoStyleClamPizza;
import Books.HeadFirst.Factory_4.FactoryMethod.PizzaStore.ChicagoPizzaType.ChicagoStylePepperoniPizza;
import Books.HeadFirst.Factory_4.FactoryMethod.PizzaStore.ChicagoPizzaType.ChicagoStyleVeggiePizza;
import Books.HeadFirst.Factory_4.FactoryMethod.PizzaStore.Pizza;
import Books.HeadFirst.Factory_4.FactoryMethod.PizzaStore.RegionalStores.AbstractClass.PizzaStore;

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
