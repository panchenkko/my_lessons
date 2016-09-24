package Books.HeadFirst.Iterator_10.transition_4_here;
import java.util.*;
  
     
public class Waitress {
	ArrayList menus;

	public Waitress(ArrayList menus) {
		this.menus = menus;
	}
   
	public void printMenu() {
		Iterator menuIterator = menus.iterator();
		while(menuIterator.hasNext()) {
			Menu menu = (Menu)menuIterator.next();
			printMenu(menu.createIterator());
            System.out.println();
        }
	}
   
	public void printMenu(Iterator iterator) {
		while (iterator.hasNext()) {
			MenuItem menuItem = (MenuItem)iterator.next();
			System.out.print(menuItem.getName() + ", ");
			System.out.print(menuItem.getPrice() + " -- ");
			System.out.println(menuItem.getDescription());
		}
	}
}  
