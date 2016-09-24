package Books.HeadFirst.Singleton_5.OtherExamples.Classic;

// Этот код не потокобезопасный!

public class Singleton {
	private static Singleton uniqueInstance;
 
	// Дргуие переменные экземпляра
 
	private Singleton() {}
 
	public static Singleton getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Singleton();
		}
		return uniqueInstance;
	}
 
	// Другие методы
}
