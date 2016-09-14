package HeadFirst.Factory_4.SimpleFactory.PizzaStore.PizzaType;

import HeadFirst.Factory_4.SimpleFactory.PizzaStore.Pizza;

public class CheesePizza extends Pizza {
	public CheesePizza() {
		name = "Cheese Pizza";
		dough = "Regular Crust";
		sauce = "Marinara Pizza Sauce";
		toppings.add("Fresh Mozzarella");
		toppings.add("Parmesan");
	}
}
