package HeadFirst.TemplateMethod_9.Barista_1;

/**
 * Абстрактный класс. Центр паттерна "Шаблонный метод".
 * * * В нем содержится финальный метод, какой описывает алгоритм действий программы,
 * * * абстрактные методы и перехватчики.
 * * *
 * * * Финальный метод - в нем вызываются поочередно методы, какие должны выполняться друг за другом, эти методы могут
 * * * быть реализованы как в этом классе так и в субклассах. Субклассов может быть произвольное количество. Просто
 * * * при создании экземпляра мы будем указывать какой тип создавать и таким образом jvm определит с какого класса
 * * * читать реализацию. (Метод финальный, а значит субклассы не могут его переопределять)
 * * *
 * * * Абстрактные методы - абстрактным метод делается, когда есть несколько субклассов и каждый из них должен
 * * * реализовывать данный метод по-разному.
 * * *
 * * * Перехватчики - не финальный метод, какой реализован в абстрактном классе и когда субклассу нужно задать
 * * * собственную реализацию данному методу, он его переопределяет и реализует, иначе будет использована реализация
 * * * по умолчанию.
 */
public abstract class CaffeineBeverage {

    public final void prepareRecipe() {
		boilWater();
		brew();
		pourInCup();
		addCondiments();
	}
 
	public abstract void brew();

    public abstract void addCondiments();

    public void boilWater() {
		System.out.println("Boiling water");
	}

    public void pourInCup() {
		System.out.println("Pouring into cup");
	}
}
