package HeadFirst.Singleton_5.Examples_For_Multithreading.SynchronizedMethod;

public class Singleton {
	private static Singleton uniqueInstance;
 
	// Другие переменные экземпляра
 
	private Singleton() {}
 
	public static synchronized Singleton getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Singleton();
		}
		return uniqueInstance;
	}
 
	// Другие методы
}
