package workingInTable;

import run.Runner;
import settings.DBConnection;
import tables.Ticket;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Update {

    private static final String UPDATE_TICKET_COUNTRY = "UPDATE tickets SET country = (?), price = (?) WHERE country = (?)";
    private static final String UPDATE_TICKET_PRICE = "UPDATE tickets SET country = (?), price = (?) WHERE price = (?)";

    public void input(Scanner sc, DBConnection connection) {
        Select.outputTickets(connection);
        Runner.title("", " ВЫБЕРИТЕ КРИТЕРИЙ ОБНОВЛЕНИЯ ", "");
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

    public String newCountry(Scanner sc) {
        System.out.print("Место: ");
        return sc.next();
    }

    public int newPrice(Scanner sc) {
        System.out.print("Цена: ");
        return sc.nextInt();
    }

    public void country(Scanner sc, DBConnection connection) {
        Runner.title("", " КРИТЕРИЙ ОБНОВЛЕНИЯ (МЕСТО) ", "");
        System.out.print("Введите место: ");
        String country = sc.next();

        if (checkData(connection, country, null) > 0) {
            Runner.title("", " ВВЕДИТЕ НОВЫЕ ДАННЫЕ ", "");
            String newCountry = newCountry(sc);
            int newPrice = newPrice(sc);

            try (PreparedStatement statement = connection.getConnection().prepareStatement(UPDATE_TICKET_COUNTRY)) {
                statement.setString(1, newCountry);
                statement.setInt(2, newPrice);
                statement.setString(3, country);
                statement.executeUpdate();
                Runner.title("\033[1;32m", " ДАННЫЕ УСПЕШНО ОБНОВЛЕНЫ ", "\033[0m");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            Runner.title("\033[1;31m", " НИЧЕГО НЕ НАЙДЕНО. ПОПРОБУЙТЕ ЕЩЁ РАЗ. ", "\033[0m");
        }
    }

    public void price(Scanner sc, DBConnection connection) {
        Runner.title("", " КРИТЕРИЙ УДАЛЕНИЯ (ЦЕНА) ", "");
        System.out.print("Введите цену: ");
        int price = sc.nextInt();

        if (checkData(connection, null, price) > 0) {
            Runner.title("", " ВВЕДИТЕ НОВЫЕ ДАННЫЕ ", "");
            String newCountry = newCountry(sc);
            int newPrice = newPrice(sc);

            try (PreparedStatement statement = connection.getConnection().prepareStatement(UPDATE_TICKET_PRICE) ) {
                statement.setString(1, newCountry);
                statement.setInt(2, newPrice);
                statement.setInt(3, price);
                statement.executeUpdate();
                Runner.title("\033[1;32m", " ДАННЫЕ УСПЕШНО ОБНОВЛЕНЫ ", "\033[0m");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            Runner.title("\033[1;31m", " НИЧЕГО НЕ НАЙДЕНО. ПОПРОБУЙТЕ ЕЩЁ РАЗ. ", "\033[0m");
        }
    }

    public int checkData(DBConnection connection, String country, Integer price) {
        Runner.title("", " РЕДАКТИРУЕМЫЕ ДАННЫЕ ", "");
        int numFound = 0;
        try (final Statement statement = connection.getConnection().createStatement();
             final ResultSet rs = statement.executeQuery("SELECT * FROM tickets")) {
            while (rs.next()) {
                if (country != null && country.equals(rs.getString("country"))) {
                    System.out.println(new Ticket(rs.getInt("id"),
                            rs.getString("country"),
                            rs.getInt("price")).toString());
                    numFound++;
                } else
                if (price != null && price == rs.getInt("price")) {
                    System.out.println(new Ticket(rs.getInt("id"),
                            rs.getString("country"),
                            rs.getInt("price")).toString());
                    numFound++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numFound;
    }
}