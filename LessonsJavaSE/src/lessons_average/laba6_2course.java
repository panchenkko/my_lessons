package lessons_average;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

class Worker {
    private String name;
    private String initials;
    private int year;

    public Worker(String n, String i, int y){
        this.name = n;
        this.initials = i;
        this.year = y;
    }

    public String getName() {
        return name;
    }
    public String getInitials() {
        return initials;
    }
    public int getYear() {
        return year;
    }

    public static void printList(List worker, int year){

        Iterator<Worker> iter = worker.iterator();

        while(iter.hasNext()){
            Worker w = iter.next();
            if(w.getYear() == year){
                System.out.println(w.getName() + " " + w.getInitials());
            }
        }
    }
}

public class laba6_2course {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Сколько работников вы хотите ввести? ");
        int sum = sc.nextInt();
        System.out.println("Какая специальность работников? ");
        String work = sc.next();
        System.out.println();
        System.out.println(work);

        List<Worker> w = new ArrayList<>();

        for (int i = 1; i <= sum ; i++) {
            System.out.println("Введите фамилию работника: ");
            String name = sc.next();

            System.out.println("Введите инициалы работника: ");
            String initials = sc.next();

            System.out.println("Введите год рождения: ");
            int year = sc.nextInt();

            System.out.println();

            w.add(new Worker(name, initials, year));
        }

        System.out.println("Какого года рождения вы хотите? ");
        int year = sc.nextInt();

        sc.close();
        Worker.printList(w, year);
    }
}