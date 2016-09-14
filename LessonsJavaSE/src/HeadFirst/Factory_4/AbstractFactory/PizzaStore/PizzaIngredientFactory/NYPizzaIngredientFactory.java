package HeadFirst.Factory_4.AbstractFactory.PizzaStore.PizzaIngredientFactory;

import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Cheese.Interface.Cheese;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Cheese.ReggianoCheese;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Clam.Interface.Clams;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Clam.FreshClams;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Dough.Interface.Dough;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Dough.ThinCrustDough;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Pepperoni.Interface.Pepperoni;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Pepperoni.SlicedPepperoni;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Sauce.MarinaraSauce;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Sauce.Interface.Sauce;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Veggies.*;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Veggies.Interface.Veggies;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.PizzaIngredientFactory.Interface.PizzaIngredientFactory;

public class NYPizzaIngredientFactory implements PizzaIngredientFactory {
 
	public Dough createDough() {
		return new ThinCrustDough();
	}
 
	public Sauce createSauce() {
		return new MarinaraSauce();
	}
 
	public Cheese createCheese() {
		return new ReggianoCheese();
	}
 
	public Veggies[] createVeggies() {
		Veggies veggies[] = { new Garlic(), new Onion(), new Mushroom(), new RedPepper() };
		return veggies;
	}
 
	public Pepperoni createPepperoni() {
		return new SlicedPepperoni();
	}

	public Clams createClam() {
		return new FreshClams();
	}
}
