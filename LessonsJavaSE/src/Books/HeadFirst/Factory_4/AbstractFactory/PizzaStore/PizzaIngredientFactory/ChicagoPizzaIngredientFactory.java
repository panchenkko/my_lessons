package Books.HeadFirst.Factory_4.AbstractFactory.PizzaStore.PizzaIngredientFactory;

import Books.HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Cheese.Interface.Cheese;
import Books.HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Cheese.MozzarellaCheese;
import Books.HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Clam.Interface.Clams;
import Books.HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Clam.FrozenClams;
import Books.HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Dough.Interface.Dough;
import Books.HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Dough.ThickCrustDough;
import Books.HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Pepperoni.Interface.Pepperoni;
import Books.HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Pepperoni.SlicedPepperoni;
import Books.HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Sauce.PlumTomatoSauce;
import Books.HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Sauce.Interface.Sauce;
import Books.HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Veggies.BlackOlives;
import Books.HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Veggies.Eggplant;
import Books.HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Veggies.Spinach;
import Books.HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Veggies.Interface.Veggies;
import Books.HeadFirst.Factory_4.AbstractFactory.PizzaStore.PizzaIngredientFactory.Interface.PizzaIngredientFactory;

public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {

	public Dough createDough() {
		return new ThickCrustDough();
	}

	public Sauce createSauce() {
		return new PlumTomatoSauce();
	}

	public Cheese createCheese() {
		return new MozzarellaCheese();
	}

	public Veggies[] createVeggies() {
		Veggies veggies[] = { new BlackOlives(), new Spinach(), new Eggplant() };
		return veggies;
	}

	public Pepperoni createPepperoni() {
		return new SlicedPepperoni();
	}

	public Clams createClam() {
		return new FrozenClams();
	}
}
