package HeadFirst.TemplateMethod_9.Barista_1;

import HeadFirst.TemplateMethod_9.Barista_1.CaffeineBeverage;

public class Tea extends CaffeineBeverage {
	public void brew() {
		System.out.println("Steeping the tea");
	}
	public void addCondiments() {
		System.out.println("Adding Lemon");
	}
}
