package workingInTable;

import run.Runner;
import settings.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Delete {

    private static final String DELETE_TICKET_COUNTRY = "DELETE FROM tickets WHERE country = (?)";
    private static final String DELETE_TICKET_PRICE = "DELETE FROM tickets WHERE price = (?)";

    public void input(Scanner sc, DBConnection connection) {
        Select.outputTickets(connection);
        Runner.title("", " ВЫБЕРИТЕ КРИТЕРИЙ УДАЛЕНИЯ ", "");
        System.out.println("1. По месту");
        System.out.println("2. По цене" + "\n");
        System.out.print("Ответ: ");
        switch (sc.nextInt()) {
            case 1: country(sc, connection);
                    break;
            case 2: price(sc, connection);
                    break;
        }
    }

    public void country(Scanner sc, DBConnection connection) {
        Runner.title("", " КРИТЕРИЙ УДАЛЕНИЯ (МЕСТО) ", "");
        System.out.print("Введите место: ");
        String country = sc.next();
        try (PreparedStatement statement = connection.getConnection().prepareStatement(DELETE_TICKET_COUNTRY) ) {
            statement.setString(1, country);
            statement.executeUpdate();
            Runner.title("\033[1;32m", " ДАННЫЕ УСПЕШНО УДАЛЕНЫ ", "\033[0m");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void price(Scanner sc, DBConnection connection) {
        Runner.title("", " КРИТЕРИЙ УДАЛЕНИЯ (ЦЕНА) ", "");
        System.out.print("Введите цену: ");
        int price = sc.nextInt();
        try (PreparedStatement statement = connection.getConnection().prepareStatement(DELETE_TICKET_PRICE) ) {
            statement.setInt(1, price);
            statement.executeUpdate();
            Runner.title("\033[1;32m", " ДАННЫЕ УСПЕШНО УДАЛЕНЫ ", "\033[0m");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
