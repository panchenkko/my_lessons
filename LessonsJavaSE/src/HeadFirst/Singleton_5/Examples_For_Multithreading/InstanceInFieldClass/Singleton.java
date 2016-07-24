package HeadFirst.Singleton_5.Examples_For_Multithreading.InstanceInFieldClass;

public class Singleton {
	private static Singleton uniqueInstance = new Singleton();
 
	private Singleton() {}
 
	public static Singleton getInstance() {
		return uniqueInstance;
	}
}
