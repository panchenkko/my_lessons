package HeadFirst.Factory_4.SimpleFactory.PizzaStore.Store;

import HeadFirst.Factory_4.SimpleFactory.PizzaStore.Pizza;
import HeadFirst.Factory_4.SimpleFactory.PizzaStore.PizzaType.CheesePizza;
import HeadFirst.Factory_4.SimpleFactory.PizzaStore.PizzaType.ClamPizza;
import HeadFirst.Factory_4.SimpleFactory.PizzaStore.PizzaType.PepperoniPizza;
import HeadFirst.Factory_4.SimpleFactory.PizzaStore.PizzaType.VeggiePizza;

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
