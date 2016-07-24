package HeadFirst.Factory_4.AbstractFactory.PizzaStore;

import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Cheese.Interface.Cheese;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Clam.Interface.Clams;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Dough.Interface.Dough;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Pepperoni.Interface.Pepperoni;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Sauce.Interface.Sauce;
import HeadFirst.Factory_4.AbstractFactory.PizzaStore.IngredientsForPizza.Veggies.Interface.Veggies;

public abstract class Pizza {
	public String name;

	public Dough dough;
	public Sauce sauce;
	public Veggies veggies[];
	public Cheese cheese;
	public Pepperoni pepperoni;
	public Clams clam;

	public abstract void prepare();

	public void bake() {
		System.out.println("Bake for 25 minutes at 350");
	}

	public void cut() {
		System.out.println("Cutting the pizza into diagonal slices");
	}

	public void box() {
		System.out.println("Place pizza in official PizzaStore box");
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("---- " + name + " ----\n");
		if (dough != null) {
			result.append(dough);
			result.append("\n");
		}
		if (sauce != null) {
			result.append(sauce);
			result.append("\n");
		}
		if (cheese != null) {
			result.append(cheese);
			result.append("\n");
		}
		if (veggies != null) {
			for (int i = 0; i < veggies.length; i++) {
				result.append(veggies[i]);
				if (i < veggies.length-1) {
					result.append(", ");
				}
			}
			result.append("\n");
		}
		if (clam != null) {
			result.append(clam);
			result.append("\n");
		}
		if (pepperoni != null) {
			result.append(pepperoni);
			result.append("\n");
		}
		return result.toString();
	}
}
