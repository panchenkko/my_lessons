package workingInTable;

import run.Runner;
import settings.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Insert {

    private static final String INSERT_TICKET = "INSERT INTO tickets (country, price) VALUES (?,?)";

    public void input(Scanner sc, DBConnection connection) {
        do {
            Runner.title("", " ЗАПОЛНИТЕ ИНФОРМАЦИЮ О БИЛЕТЕ ", "");
            System.out.print("Место: ");
            String country = sc.next();
            System.out.print("Цена поездки: ");
            int price = sc.nextInt();

            try (PreparedStatement statement = connection.getConnection().prepareStatement(INSERT_TICKET)) {
                statement.setString(1, country);
                statement.setInt(2, price);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            System.out.print("\n" + "Добавить ещё билет? (y/n) ");
        } while (!sc.next().equals("n"));
    }
}
