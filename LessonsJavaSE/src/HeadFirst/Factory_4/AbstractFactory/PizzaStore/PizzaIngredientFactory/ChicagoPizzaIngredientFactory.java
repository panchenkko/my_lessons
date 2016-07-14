package HeadFirst.Factory_4.AbstractFactory.PizzaStore.PizzaIngredientFactory;

import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Cheese.Interface.Cheese;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Cheese.MozzarellaCheese;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Clam.Interface.Clams;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Clam.FrozenClams;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Dough.Interface.Dough;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Dough.ThickCrustDough;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Pepperoni.Interface.Pepperoni;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Pepperoni.SlicedPepperoni;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Sauce.PlumTomatoSauce;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Sauce.Interface.Sauce;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Veggies.BlackOlives;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Veggies.Eggplant;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Veggies.Spinach;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Veggies.Interface.Veggies;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.PizzaIngredientFactory.Interface.PizzaIngredientFactory;

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
