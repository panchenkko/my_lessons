package Books.HeadFirst.Factory_4.AbstractFactory.PizzaStore.PizzaIngredientFactory;

import Books.HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Cheese.Interface.Cheese;
import Books.HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Cheese.ReggianoCheese;
import Books.HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Clam.Interface.Clams;
import Books.HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Clam.FreshClams;
import Books.HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Dough.Interface.Dough;
import Books.HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Dough.ThinCrustDough;
import Books.HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Pepperoni.Interface.Pepperoni;
import Books.HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Pepperoni.SlicedPepperoni;
import Books.HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Sauce.MarinaraSauce;
import Books.HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Sauce.Interface.Sauce;
import Books.HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Veggies.*;
import Books.HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Veggies.Interface.Veggies;
import Books.HeadFirst.Factory_4.AbstractFactory.PizzaStore.PizzaIngredientFactory.Interface.PizzaIngredientFactory;

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
