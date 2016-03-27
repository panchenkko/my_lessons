package lessons_average;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class Students {
    private String name;
    private int course;

    public Students(String name, int course) {
        this.name = name;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public int getCourse() {
        return course;
    }

    public static Set union(Set students1, Set students2){
        students1.addAll(students2);
        return students1;
    }

    public static Set incret(Set students1, Set students2){
        students1.retainAll(students2);
        return students1;
    }

    public static void printSet(Set students1, Set students2){

        Students.union(students1, students2);
        Students.incret(students1, students2);
        System.out.println("Сложение множеств: ");

        Iterator<Students> iter = students1.iterator();

        while(iter.hasNext()){
            Students st = iter.next();
            System.out.println(st.getName() + ", курс: " + st.getCourse());
        }
    }
}

public class LinkedHashSet {

    public static void main(String[] args) {

        Set<Students> students1 = new HashSet();
        Set<Students> students2 = new HashSet();

        students1.add(new Students("Ворснап", 1));
        students1.add(new Students("Джейми", 3));
        students1.add(new Students("Адам", 4));
        students1.add(new Students("Лакер", 3));
        students1.add(new Students("СиДжей", 4));
        students1.add(new Students("Олейна Сукис", 1));
        students1.add(new Students("Пельц", 2));
        students1.add(new Students("Ворснап", 1));

        students2.add(new Students("Джейми", 3));
        students2.add(new Students("Адам", 4));
        students2.add(new Students("Лакер", 3));
        students2.add(new Students("СиДжей", 4));
        students2.add(new Students("Олейна Сукис", 1));
        students2.add(new Students("Пельц", 2));
        students2.add(new Students("Ворснап", 1));
        students2.add(new Students("Койлер", 4));
        students2.add(new Students("Бозмен", 4));
        students2.add(new Students("Фронзак", 2));

        Students.printSet(students1, students2);
    }
}