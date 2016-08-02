package HeadFirst.Combining.decorator_3;

public class MallardDuck implements Quackable {
 
	public void quack() {
		System.out.println("Quack");
	}
 
	public String toString() {
		return "Mallard Duck";
	}
}
