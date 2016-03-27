package lessons_average;

import java.text.*;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Date_ {

    // Вывод текущей даты в формате: dd.MMMM.yyyy, hh:mm
    public void today() {
        Date day = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-е MMMM yyyy год, hh:mm");
        System.out.println(formatter.format(day));
    }

    // Вывод даты, через определенное кол-во дней
    public void time(Scanner sc) {
        System.out.println("\n<----- Определение даты ----->");
        System.out.print("\nВведите кол-во дней: ");
        int num = sc.nextInt();

        GregorianCalendar result = new GregorianCalendar();
        // к месяцу +1, так как отсчет начинается с нуля
        result.add(Calendar.MONTH, 1);
        // к текущему дню добавляем количество дней введенные пользователем
        result.add(Calendar.DAY_OF_MONTH, num);

        System.out.println(
                "\nЧЕРЕЗ " + num + " ДНЕЙ " + "НАСТУПИТ:" +
                        "\n День: " + result.get(Calendar.DAY_OF_MONTH) +
                        "\n Месяц: " + result.get(Calendar.MONTH) +
                        "\n Год: " + result.get(Calendar.YEAR)
        );
    }

    // Определяет ваш возраст, с точностью до дня
    public void born(Scanner sc) {
        System.out.println("\n<----- Ваш возраст с точностью до дня ----->");
        System.out.print("\nГод рождения: ");
        int y = sc.nextInt();
        System.out.print("Месяц по счету: ");
        int m = sc.nextInt();
        System.out.print("День: ");
        int d = sc.nextInt();

        GregorianCalendar result = new GregorianCalendar();
        result.add(Calendar.MONTH, 1);
        // Отнимаем от текущего года, год рождения введенные пользователем
        result.add(Calendar.YEAR, -y);
        // Тоже самое
        result.add(Calendar.MONTH, -m);
        // Тоже самое
        result.add(Calendar.DAY_OF_MONTH, -d);

        System.out.println(
                "\nВам сейчас: " +
                        result.get(Calendar.YEAR) + " лет " +
                        result.get(Calendar.MONTH) + " месяцев и " +
                        result.get(Calendar.DAY_OF_MONTH) + " дней."
        );
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Date_ date = new Date_();

        date.today();
        date.time(sc);
        date.born(sc);

        sc.close();
    }
}