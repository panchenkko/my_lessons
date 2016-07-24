package HeadFirst.TemplateMethod_9.Barista_1;

import HeadFirst.TemplateMethod_9.Barista_1.CaffeineBeverage;

public class Coffee extends CaffeineBeverage {
	public void brew() {
		System.out.println("Dripping Coffee through filter");
	}
	public void addCondiments() {
		System.out.println("Adding Sugar and Milk");
	}
}
