package lessons_average;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

class Student {
    private String name;
    private int course;

    public Student(String n, int c) {
        this.name = n;
        this.course = c;
    }

    public String getName() {
        return name;
    }
    public int getCourse() {
        return course;
    }

    public static void printList(List students, int course) {

        Iterator<Student> iter = students.iterator();

        while(iter.hasNext()){
            Student loadedStudent = iter.next();
            if(loadedStudent.getCourse() == course)
                System.out.println(loadedStudent.getName());
        }
    }
}

public class collection_list {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("В этом заведении 4 курса, введите желаемый: ");
        int num = sc.nextInt();
        sc.close();

        if(num > 4 || num < 1) {
            System.out.println("Данного курса не существует!");
        } else {
            int course;
            course = num;

            System.out.println("Количество студентов " + course + " курса:");

            List<Student> students = new ArrayList<>();

            students.add(new Student("Ворснап", 1));
            students.add(new Student("Койлер", 4));
            students.add(new Student("Бозмен", 4));
            students.add(new Student("Фронзак", 2));
            students.add(new Student("Джейми", 3));
            students.add(new Student("Адам", 4));
            students.add(new Student("Лакер", 3));
            students.add(new Student("СиДжей", 4));
            students.add(new Student("Олейна Сукис", 1));
            students.add(new Student("Пельц", 2));

            Student.printList(students, course);
        }
    }
}
