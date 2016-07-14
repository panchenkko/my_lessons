package HeadFirst.Factory_4.AbstractFactory.PizzaStore.PizzaType;

import HeadFirst.Factory_4.AbstractFactory.PizzaStore.Pizza;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.PizzaIngredientFactory.Interface.PizzaIngredientFactory;

public class CheesePizza extends Pizza {
	PizzaIngredientFactory ingredientFactory;
 
	public CheesePizza(PizzaIngredientFactory ingredientFactory) {
		this.ingredientFactory = ingredientFactory;
	}

	public void prepare() {
		System.out.println("Preparing " + name);
		dough = ingredientFactory.createDough();
		sauce = ingredientFactory.createSauce();
		cheese = ingredientFactory.createCheese();
	}
}
