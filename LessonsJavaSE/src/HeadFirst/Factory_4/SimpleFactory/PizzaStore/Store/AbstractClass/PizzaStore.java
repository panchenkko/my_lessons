package HeadFirst.Factory_4.SimpleFactory.PizzaStore.Store.AbstractClass;

import HeadFirst.Factory_4.SimpleFactory.PizzaStore.Pizza;
import HeadFirst.Factory_4.SimpleFactory.PizzaStore.Store.SimplePizzaFactory;

public class PizzaStore {
	SimplePizzaFactory factory;
 
	public PizzaStore(SimplePizzaFactory factory) { 
		this.factory = factory;
	}
 
	public Pizza orderPizza(String type) {
		Pizza pizza;
 
		pizza = factory.createPizza(type);
 
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();

		return pizza;
	}

}
