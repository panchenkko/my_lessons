package HeadFirst.Factory_4.AbstractFactory.PizzaStore.PizzaIngredientFactory.Interface;

import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Cheese.Interface.Cheese;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Clam.Interface.Clams;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Dough.Interface.Dough;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Pepperoni.Interface.Pepperoni;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Sauce.Interface.Sauce;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Veggies.Interface.Veggies;

public interface PizzaIngredientFactory {
	Dough createDough();
	Sauce createSauce();
	Cheese createCheese();
	Veggies[] createVeggies();
	Pepperoni createPepperoni();
	Clams createClam();
}
