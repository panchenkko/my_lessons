package HeadFirst.Factory_4.AbstractFactory.PizzaStore.RegionalStore;

import HeadFirst.Factory_4.AbstractFactory.PizzaStore.Pizza;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.PizzaIngredientFactory.NYPizzaIngredientFactory;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.PizzaIngredientFactory.Interface.PizzaIngredientFactory;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.PizzaType.CheesePizza;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.PizzaType.ClamPizza;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.PizzaType.PepperoniPizza;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.PizzaType.VeggiePizza;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.RegionalStore.AbstractClass.PizzaStore;

public class NYPizzaStore extends PizzaStore {
 
	protected Pizza createPizza(String item) {
		Pizza pizza = null;
		PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();

		switch (item) {
			case "cheese":
				pizza = new CheesePizza(ingredientFactory);
				pizza.setName("New York Style Cheese Pizza");
				break;
			case "veggie":
				pizza = new VeggiePizza(ingredientFactory);
				pizza.setName("New York Style Veggie Pizza");
				break;
			case "clam":
				pizza = new ClamPizza(ingredientFactory);
				pizza.setName("New York Style Clam Pizza");
				break;
			case "pepperoni":
				pizza = new PepperoniPizza(ingredientFactory);
				pizza.setName("New York Style Pepperoni Pizza");
				break;
		}
		return pizza;
	}
}
