package FactoryMethod;

import FactoryMethod.FactoryClasses.FDelete;
import FactoryMethod.FactoryClasses.FInsert;
import FactoryMethod.FactoryClasses.FSelect;

import java.util.Scanner;

public class Runner {

    public void delete(Scanner sc, DBWorker dbWorker, FDelete delete) {
        System.out.print("1. Удалить все данные\n" +
                         "2. Удалить выбранный ряд\n\n" + "Ответ: ");
        int whatToDelete = sc.nextInt();

        if (whatToDelete == 1) {
            delete.deleteAll(dbWorker);
            SPACE();
            System.out.println("Удалено!");
        } else
        if (whatToDelete == 2) {
            System.out.print("Выберите таблицу: \n" +
                    "1. Снег\n" +
                    "2. Дождь\n" +
                    "3. Снегопад \n\n" +
                    "Ответ: ");
            int answer = sc.nextInt();
            SPACE();
            if (answer == 1 || answer == 2 || answer == 3) {
                System.out.print("Укажите номер ряда: ");
                int numRow = sc.nextInt();
                switch (answer) {
                    case 1: delete.deleteSnow(dbWorker, numRow);
                            break;
                    case 2: delete.deleteRain(dbWorker, numRow);
                            break;
                    case 3: delete.deleteBlizzard(dbWorker, numRow);
                            break;
                    default: SPACE();
                        System.out.println("Вы не выбрали таблицу!");
                }
                SPACE();
                System.out.println("Удалено!");
            } else {
                SPACE();
                System.out.println("Попробуйте ещё раз.");
            }
        } else {
            SPACE();
            System.out.println("Попробуйте ещё раз.");
        }
    }

    public void insert(int table, Scanner sc, DBWorker dbWorker, FInsert insert) {
        if (table == 1) {
            int numLastColumn = insert.insertSnow(sc, dbWorker, null);
            insert.insertWeather(sc, dbWorker, null, numLastColumn);
        } else
        if (table == 2) {
            int numLastColumn = insert.insertRain(sc, dbWorker);
            insert.insertWeather(sc, dbWorker, numLastColumn, null);
        } else
        if (table == 3) {
            int numLast1 = insert.insertBlizzard(sc, dbWorker);
            int numLast2 = insert.insertSnow(sc, dbWorker, numLast1);
            insert.insertWeather(sc, dbWorker, null, numLast2);
        }
    }

    public void select(int table, DBWorker dbWorker, FSelect select) {
        if (table == 4) select.selectSnow(dbWorker);
        else if (table == 5) select.selectRain(dbWorker);
        else if (table == 6) select.selectBlizzard(dbWorker);
    }

    public void SPACE() {
        System.out.println();
        System.out.println("=================================");
        System.out.println();
    }

    public int selectMenu(Scanner sc) {
        System.out.print(
                        "1. Заполнить таблицу \"Снег\" \n" +
                        "2. Заполнить таблицу \"Дождь\" \n" +
                        "3. Заполнить таблицу \"Снегопад\" \n \n" +

                        "4. Вывести таблицу \"Снег\" \n" +
                        "5. Вывести таблицу \"Дождь\" \n" +
                        "6. Вывести таблицу \"Снегопад\" \n \n" +

                        "0. Удалить данные \n \n" +

                        "7. Выйти \n \n" +
                        "Ответ: "
        );
        return sc.nextInt();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DBWorker dbWorker = new DBWorker();
        Runner runner = new Runner();
        int table;
        do {
            runner.SPACE();
            table = runner.selectMenu(sc);
            runner.SPACE();
            if (table == 1 || table == 2 || table == 3) {
                FInsert insert = new FInsert();
                runner.insert(table, sc, dbWorker, insert);
            } else if (table == 4 || table == 5 || table == 6) {
                FSelect select = new FSelect();
                runner.select(table, dbWorker, select);
            } else if (table == 0) {
                FDelete delete = new FDelete();
                runner.delete(sc, dbWorker, delete);
            } else System.out.println("Пока");
        } while(table != 7);
    }
}