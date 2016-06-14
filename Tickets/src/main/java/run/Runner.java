package run;

import settings.DBConnection;
import workingInTable.Delete;
import workingInTable.Insert;
import workingInTable.Select;
import workingInTable.Update;
import workingInTickets.Reservation;

import java.util.Scanner;

public class Runner {

    public int selectAction(Scanner sc) {
        title("", " ВЫБЕРИТЕ ЧТО-НИБУДЬ ", "");
        System.out.println("1. Работа над таблицами");
        System.out.println("2. Бронь билета" + "\n");
        System.out.print("Ответ: ");
        return sc.nextInt();
    }

    public void working(Scanner sc, DBConnection connection) {
        int select = selectAction(sc);
        switch (select) {
            case 1: workingInTable(sc, connection);
                    break;
            case 2: workingInTickets(sc, connection);
                    break;
            default:
                title("", " ВЫ НЕ ВЫБРАЛИ. ПОПРОБУЙТЕ ЕЩЁ РАЗ! ", "");
                working(sc, connection);
        }
    }

    public void workingInTable(Scanner sc, DBConnection connection) {
        title("", " ВЫБЕРИТЕ ДЕЙСТВИЕ НАД ТАБЛИЦАМИ ", "");
        System.out.println("1. Добавить новые места для поездок");
        System.out.println("2. Посмотреть таблицу");
        System.out.println("3. Отредактировать данные");
        System.out.println("4. Удалить ненужные билеты" + "\n");
        System.out.print("Ответ: ");

        switch (sc.nextInt()) {
            case 1: new Insert().input(sc, connection);
                break;
            case 2: new Select().input(sc, connection);
                break;
            case 3: new Update().input(sc, connection);
                break;
            case 4: new Delete().input(sc, connection);
                break;
        }
        working(sc, connection);
    }

    public void workingInTickets(Scanner sc, DBConnection connection) {
        new Reservation().input(sc, connection);
        working(sc, connection);
    }

    public static void title(String startColor, String inscription, String endColor) {
        System.out.println();
        System.out.println(String.format("%s==============%s==============%s", startColor, inscription, endColor));
        System.out.println();
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            DBConnection connection = new DBConnection();
            new Runner().working(sc, connection);
        }
    }
}
