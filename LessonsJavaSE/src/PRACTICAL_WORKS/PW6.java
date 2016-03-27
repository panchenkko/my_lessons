package PRACTICAL_WORKS;

import java.util.*;

class Family {
    private String name;
    private int hierarchy;

    public Family(String n, int h) {
        this.name = n;
        this.hierarchy = h;
    }

    public String getName() {
        return name;
    }

    public int getHierarchy() {
        return hierarchy;
    }

    public static void add(List family) {
        Scanner sc = new Scanner(System.in);
        String question;
        do {
            System.out.print("Введите имя: ");
            String n = sc.next();

            System.out.print("Введите иерархию: ");
            int h = sc.nextInt();

            family.add(new Family(n, h));

            System.out.print("\nПродолжить (y/n)? ");
            question = sc.next();

            System.out.println();
        } while (!Objects.equals(question, "n"));

        menu(family);
    }

    public static void menu(List family) {
        Scanner sc = new Scanner(System.in);
        String answer;
        System.out.println(
                "\033[1;37mВыберите действие: \033[0m"
                        + "\n\033[36m   1. Вывести всё\033[0m"
                        + "\n\033[32m   2. Выбрать ветку дерева\033[0m"
                        + "\n\033[34m   3. Добавить\033[0m"
                        + "\n\033[33m   4. Удалить\033[0m"
                        + "\n\033[37m   5. Выход\033[0m");
        System.out.print("\n\033[1;30mОтвет: \033[0m");
        answer = sc.next();
        System.out.println();
        switch(answer) {
            case "1": Family.show(family);
                      break;
            case "2": Family.choice(family);
                      break;
            case "3": Family.add(family);
                      break;
            case "4": Family.remove(family);
                      break;
            case "5": break;
            default:
                System.err.println("Вы кажется промахнулись.. Попробуйте ещё раз.");
                System.out.println();
                menu(family);
        }
    }

    public static void show(List family) {

        Collections.sort(family, new Comparator<Family>() {
            @Override
            public int compare(Family c1, Family c2) {
                return c1.getHierarchy()- c2.getHierarchy();
            }
        });

        Iterator<Family> iter = family.iterator();

        while (iter.hasNext()) {
            Family f = iter.next();
            System.out.println("Иерархия: " + f.getHierarchy() + ", Имя: " + f.getName() );
            System.out.println();
        }

        menu(family);
    }

    public static void choice(List family) {
        Scanner sc = new Scanner(System.in);

        System.out.print(
                "\033[1;37mВыберите: \033[0m"
                        + "\n   1. Выбор по имени"
                        + "\n   2. Выбор по иерархии");
        System.out.print("\nОтвет: ");
        String question = sc.next();

        Iterator<Family> iter = family.iterator();

        switch (question) {
            case "1":
                System.out.print("\nВведите лидирующее имя иерархии: ");
                String name = sc.next();
                int first = 0;

                while (iter.hasNext()) {
                    Family f = iter.next();
                    if (Objects.equals(name, f.getName())) {
                        first = f.getHierarchy();
                    }
                    if (first != 0)
                        if (f.getHierarchy() >= first) {
                            System.out.println("Иерархия: " + f.getHierarchy() + ", Имя: " + f.getName());
                        }
                }

                System.out.println();
                menu(family);
                break;
            case "2":
                System.out.print("\nВведите лидирующий номер иерархии: ");
                int hierarchy = sc.nextInt();

                while (iter.hasNext()) {
                    Family f = iter.next();
                    if (f.getHierarchy() >= hierarchy) {
                        System.out.println("Иерархия: " + f.getHierarchy() + ", Имя: " + f.getName());
                    }
                }

                System.out.println();
                menu(family);
                break;
            default:
                System.err.println("Вы кажется промахнулись.. Попробуйте ещё раз.");
                System.out.println();
                choice(family);
        }
    }

    public static void remove(List family) {
        Scanner sc = new Scanner(System.in);

        System.out.print(
                "\033[1;37mВыберите: \033[0m"
                        + "\n   1. Удаление по имени"
                        + "\n   2. Удаление по иерархии");
        System.out.print("\nОтвет: ");
        String question = sc.next();

        Iterator<Family> iter = family.iterator();

        switch (question) {
            case "1":
                System.out.print("\nВведите имя: ");
                String name = sc.next();
                while (iter.hasNext()) {
                    Family f = iter.next();
                    if (Objects.equals(f.getName(), name)) {
                        System.out.println("\nИмя: " + f.getName() + ", Иерархия: " + f.getHierarchy());
                        System.err.println("\nУдалить (y/n)? ");
                        String answerRemove = sc.next();
                        if (Objects.equals(answerRemove, "y"))
                            iter.remove();
                    }
                }
                System.out.println();
                menu(family);
                break;
            case "2":
                System.out.print("\nВведите номер иерархии: ");
                int hierarchy = sc.nextInt();

                while (iter.hasNext()) {
                    Family f = iter.next();
                    if (Objects.equals(f.getHierarchy(), hierarchy)) {
                        System.out.println("\nИерархия: " + f.getHierarchy() + ", Имя: " + f.getName());
                        System.err.print("\nУдалить (y/n)? ");
                        String answerRemove = sc.next();
                        if (Objects.equals(answerRemove, "y"))
                            iter.remove();
                    }
                }
                System.out.println();
                menu(family);
                break;
            default:
                System.err.println("Вы кажется промахнулись.. Попробуйте ещё раз.");
                System.out.println();
                remove(family);
        }
    }
}

/*
Побудувати генеалогічне дерево родини.
Виводити на екран повне дерево або обрану гілку.
Передбачити функції видалення та додавання елементів до дерева.
*/

public class PW6 {
    public static void main(String[] args) {
        List<Family> families = new ArrayList<>();
        System.out.print("\033[1;37mЗаполните дерево: \033[0m\n");
        Family.add(families);
    }
}