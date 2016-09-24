package Books.HeadFirst.Factory_4.FactoryMethod.PizzaStore.ChicagoPizzaType;

import Books.HeadFirst.Factory_4.FactoryMethod.PizzaStore.Pizza;

public class ChicagoStyleVeggiePizza extends Pizza {
	public ChicagoStyleVeggiePizza() {
		name = "Chicago Deep Dish Veggie Pizza";
		dough = "Extra Thick Crust Dough";
		sauce = "Plum Tomato Sauce";
 
		toppings.add("Shredded Mozzarella Cheese");
		toppings.add("Black Olives");
		toppings.add("Spinach");
		toppings.add("Eggplant");
	}

	public void cut() {
		System.out.println("Cutting the pizza into square slices");
	}
}
