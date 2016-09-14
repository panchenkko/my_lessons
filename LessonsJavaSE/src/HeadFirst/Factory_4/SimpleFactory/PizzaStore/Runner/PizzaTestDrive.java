package HeadFirst.Factory_4.SimpleFactory.PizzaStore.Runner;

import HeadFirst.Factory_4.SimpleFactory.PizzaStore.Pizza;
import HeadFirst.Factory_4.SimpleFactory.PizzaStore.Store.AbstractClass.PizzaStore;
import HeadFirst.Factory_4.SimpleFactory.PizzaStore.Store.SimplePizzaFactory;

public class PizzaTestDrive {
 
	public static void main(String[] args) {
		SimplePizzaFactory factory = new SimplePizzaFactory();
		PizzaStore store = new PizzaStore(factory);

		Pizza pizza = store.orderPizza("cheese");
		System.out.println("We ordered a " + pizza.getName() + "\n");
 
		pizza = store.orderPizza("veggie");
		System.out.println("We ordered a " + pizza.getName() + "\n");
	}
}
