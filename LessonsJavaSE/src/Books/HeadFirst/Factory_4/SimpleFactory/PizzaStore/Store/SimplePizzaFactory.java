package Books.HeadFirst.Factory_4.SimpleFactory.PizzaStore.Store;

import Books.HeadFirst.Factory_4.SimpleFactory.PizzaStore.Pizza;
import Books.HeadFirst.Factory_4.SimpleFactory.PizzaStore.PizzaType.CheesePizza;
import Books.HeadFirst.Factory_4.SimpleFactory.PizzaStore.PizzaType.ClamPizza;
import Books.HeadFirst.Factory_4.SimpleFactory.PizzaStore.PizzaType.PepperoniPizza;
import Books.HeadFirst.Factory_4.SimpleFactory.PizzaStore.PizzaType.VeggiePizza;

public class SimplePizzaFactory {

	public Pizza createPizza(String type) {
		Pizza pizza = null;

		if (type.equals("cheese")) {
			pizza = new CheesePizza();
		} else if (type.equals("pepperoni")) {
			pizza = new PepperoniPizza();
		} else if (type.equals("clam")) {
			pizza = new ClamPizza();
		} else if (type.equals("veggie")) {
			pizza = new VeggiePizza();
		}
		return pizza;
	}
}
