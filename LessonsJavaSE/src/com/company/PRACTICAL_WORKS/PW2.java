package com.company.PRACTICAL_WORKS;

import java.util.*;

class Cars {
    private String model;
    private String brand;
    private String engine_capacity;
    private String run;
    private String body_type;
    private String category;
    private int price;

    public Cars(String m, String b, String e_c, String r, String b_t, String c, int p) {
        this.model = m;
        this.brand = b;
        this.engine_capacity = e_c;
        this.run = r;
        this.body_type = b_t;
        this.category = c;
        this.price = p;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public String getEngine_capacity() {
        return engine_capacity;
    }

    public String getRun() {
        return run;
    }

    public String getBody_type() {
        return body_type;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public static void showList(Cars c) {
        System.out.println(
                "Модель автомобиля: " + c.getModel() +
                        "\nМарка автомобиля: " + c.getBrand() +
                        "\nОбъем автомобиля: " + c.getEngine_capacity() + " л. " +
                        "\nПробег автомобиля: " + c.getRun() + " км. " +
                        "\nТип кузова: " + c.getBody_type() +
                        "\nКатегория автомобиля: " + c.getCategory() +
                        "\nЦена автомобиля: " + c.getPrice() + " грн. "
        );
        System.out.println();
    }

    public static void add(List cars) {
        Scanner sc = new Scanner(System.in);
        String m, b, b_t, c, e_c, r, question;
        int p = 0;

        do {
            System.out.print("Введите модель автомобиля: ");
            m = sc.next();
            System.out.print("Введите марку автомобиля: ");
            b = sc.next();
            System.out.print("Введите объём двигателя: ");
            e_c = sc.next();
            System.out.print("Введите пробег автомобиля: ");
            r = sc.next();
            System.out.print("Введите тип кузова: ");
            b_t = sc.next();
            System.out.print("Введите категорию автомобиля: ");
            c = sc.next();
            try {
                System.out.print("Введите цену автомобиля: ");
                p = sc.nextInt();
            } catch (Exception e) {
                System.err.println("\nВы ввели не целочисленный тип. Попробуйте снова!");
                System.out.println();
                add(cars);
            }

            cars.add(new Cars(m, b, e_c, r, b_t, c, p));

            System.out.print("\nПродолжить? (y/n) ");
            question = sc.next();

            System.out.println();
        } while (!Objects.equals(question, "n"));

        menu(cars);
    }

    public static void menu(List cars) {
        Scanner sc = new Scanner(System.in);
        String answer;
        System.out.println(
                "Выберите действие: "
                        + "\n   1. Вывести всё"
                        + "\n   2. Поиск по данным автомобиля"
                        + "\n   3. Сортировка по данным автомобиля");
        System.out.print("\nОтвет: ");
        answer = sc.next();
        System.out.println();
        switch(answer) {
            case "1":
                Cars.show(cars);
                break;
            case "2":
                Cars.search(cars);
                break;
            case "3":
                Cars.sort(cars);
                break;
            default:
                System.err.println("Вы кажется промахнулись.. Попробуйте ещё раз.");
                System.out.println();
                menu(cars);
        }
    }

    public static void show(List cars) {
        Iterator<Cars> iter = cars.iterator();
        while(iter.hasNext()) {
            Cars c = iter.next();
            System.out.println(
                    "Модель автомобиля: " + c.getModel() +
                    "\nМарка автомобиля: " + c.getBrand() +
                    "\nОбъем автомобиля: " + c.getEngine_capacity() + " л. " +
                    "\nПробег автомобиля: " + c.getRun() + " км. " +
                    "\nТип кузова: " + c.getBody_type() +
                    "\nКатегория автомобиля: " + c.getCategory() +
                    "\nЦена автомобиля: " + c.getPrice() + " грн. "
            );
            System.out.println();
        }
    }

    public static void search(List cars) {
        Scanner sc = new Scanner(System.in);
        String answer_search, brand, body_type, price;
        System.out.println(
                "Поиск по: "
                        + "\n   1. Марке"
                        + "\n   2. Типом кузова"
                        + "\n   3. Цене");
        System.out.print("\nОтвет: ");
        answer_search = sc.next();
        System.out.println();

        Iterator<Cars> iter = cars.iterator();

        switch(answer_search) {
            case "1":
                System.out.print("Введите искомую марку: ");
                brand = sc.next();
                System.out.println();

                while(iter.hasNext()) {
                    Cars c = iter.next();
                    if(Objects.equals(c.getBrand(), brand)) {
                        showList(c);
                    }
                }
                break;
            case "2":
                System.out.print("Введите искомый тип кузова: ");
                body_type = sc.next();
                System.out.println();

                while(iter.hasNext()) {
                    Cars c = iter.next();
                    if(Objects.equals(c.getBody_type(), body_type)) {
                        showList(c);
                    }
                }
                break;
            case "3":
                System.out.print("Введите искомую цену: ");
                price = sc.next();
                System.out.println();

                while(iter.hasNext()) {
                    Cars c = iter.next();
                    if(Objects.equals(c.getPrice(), price)) {
                        showList(c);
                    }
                }
                break;
            default:
                System.err.println("Вы немного промахнулись.. Попробуйте ещё раз.");
                System.out.println();
                search(cars);
        }
    }

    public static void sort(List cars) {
        Scanner sc = new Scanner(System.in);
        String answer_sort;
        System.out.println(
                "Сортировка по: "
                        + "\n   1. Марке"
                        + "\n   2. Типом кузова"
                        + "\n   3. Цене");
        System.out.print("\nОтвет: ");
        answer_sort = sc.next();
        System.out.println();

        switch(answer_sort) {
            case "1":

                Collections.sort(cars, new Comparator<Cars>() {
                    @Override
                    public int compare(Cars c1, Cars c2) {
                        return c1.getBrand().compareTo(c2.getBrand());
                    }
                });

                show(cars);

                break;
            case "2":

                Collections.sort(cars, new Comparator<Cars>() {
                    @Override
                    public int compare(Cars c1, Cars c2) {
                        return c1.getBody_type().compareTo(c2.getBody_type());
                    }
                });

                show(cars);

                break;
            case "3":

                Collections.sort(cars, new Comparator<Cars>() {
                    @Override
                    public int compare(Cars c1, Cars c2) {
                        return c1.getPrice() - c2.getPrice();
                    }
                });

                show(cars);

                break;
            default:
                System.err.println("Вы немного промахнулись.. Попробуйте ещё раз.");
                System.out.println();
                sort(cars);
        }
    }
}

/*
За допомогою масиву структур описати каталог автомобілів, передбачивши такі характеристики:
модель, марка, об'єм двигуна, пробіг, тип кузова, категорія, ціна.
Передбачивши функцію пошуку та сортування за маркою, типом кузова та ціною.
Обрати автомобілі із пробігом.
*/

public class PW2 {
    public static void main(String[] args) {
        List<Cars> cars = new ArrayList<>();
        Cars.add(cars);
    }
}