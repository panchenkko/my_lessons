package Books.HeadFirst.Singleton_5.OtherExamples.Chocolate;
 
public class ChocolateBoiler {
	private boolean empty;
	private boolean boiled;
	private static ChocolateBoiler uniqueInstance;
  
	private ChocolateBoiler() {
		empty = true;
		boiled = false;
	}
  
	public static ChocolateBoiler getInstance() {
		if (uniqueInstance == null) {
			System.out.println("Создание единственного экземпляра Chocolate Boiler");
			uniqueInstance = new ChocolateBoiler();
		}
		System.out.println("Возврат экземпляра Chocolate Boiler");
		return uniqueInstance;
	}

	public void fill() {
		if (isEmpty()) {
			empty = false;
			boiled = false;
			// Заполнение нагревателя молочно-шоколадной смесью
		}
	}
 
	public void drain() {
		if (!isEmpty() && isBoiled()) {
			// слить нагретое молоко и шоколад
			empty = true;
		}
	}
 
	public void boil() {
		if (!isEmpty() && !isBoiled()) {
			// Доевсти содержимое до кипения
			boiled = true;
		}
	}
  
	public boolean isEmpty() {
		return empty;
	}
 
	public boolean isBoiled() {
		return boiled;
	}
}
