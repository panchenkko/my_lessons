package HeadFirst.Adapter_7.Ducks.Turkey;

import HeadFirst.Adapter_7.Ducks.Duck.DuckAdapter;
import HeadFirst.Adapter_7.Ducks.Duck.MallardDuck;

public class TurkeyTestDrive {
	public static void main(String[] args) {
		MallardDuck duck = new MallardDuck();
		Turkey duckAdapter = new DuckAdapter(duck);
 
		for(int i=0;i<10;i++) {
			System.out.println("The DuckAdapter says...");
			duckAdapter.gobble();
			duckAdapter.fly();
		}
	}
}
