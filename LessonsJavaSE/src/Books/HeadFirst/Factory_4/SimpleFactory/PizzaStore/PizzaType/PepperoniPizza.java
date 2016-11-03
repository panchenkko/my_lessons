package Books.HeadFirst.Factory_4.SimpleFactory.PizzaStore.PizzaType;

import Books.HeadFirst.Factory_4.SimpleFactory.PizzaStore.Pizza;

public class PepperoniPizza extends Pizza {
	public PepperoniPizza() {
		name = "Pepperoni Pizza";
		dough = "Crust";
		sauce = "Marinara sauce";
		toppings.add("Sliced Pepperoni");
		toppings.add("Sliced Onion");
		toppings.add("Grated parmesan cheese");
	}
}
