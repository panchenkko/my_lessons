package Books.HeadFirst.Factory_4.SimpleFactory.PizzaStore.PizzaType;

import Books.HeadFirst.Factory_4.SimpleFactory.PizzaStore.Pizza;

public class ClamPizza extends Pizza {
	public ClamPizza() {
		name = "Clam Pizza";
		dough = "Thin crust";
		sauce = "White garlic sauce";
		toppings.add("Clams");
		toppings.add("Grated parmesan cheese");
	}
}
