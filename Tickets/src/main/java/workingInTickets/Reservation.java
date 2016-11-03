package workingInTickets;

import run.Runner;
import settings.DBConnection;
import tables.Ticket;
import workingInTable.Select;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Reservation {

    private static final String INSERT_RESERVATION = "INSERT INTO reservations (name, surname, telephone, ticket_id)  " +
            "VALUES (?,?,?,?)";

    private Ticket ticket;

    public void input(Scanner sc, DBConnection connection) {
        Select.outputTickets(connection);
        Runner.title("", " ВВЕДИТЕ ЖЕЛАЕМОЕ МЕСТО ", "");
        System.out.print("Место: ");
        String country = sc.next();
        Integer price = checkData(connection, country);

        if (price != null) {
            System.out.println("Данное место стоит: " + price);
            System.out.println();
            System.out.print("Вы подтверждаете заказ? (y/n) ");
            if (sc.next().equals("y")) customerData(sc, connection);
        } else {
            Runner.title("\033[1;31m", " К СОЖАЛЕНИЮ, ТАКИХ МЕСТ НЕ НАЙДЕНО ", "\033[0m");
        }
    }

    public void customerData(Scanner sc, DBConnection connection) {
        Runner.title("", " ВНЕСИТЕ ПОЖАЛУЙСТА ЛИЧНЫЕ ДАННЫЕ ", "");
        System.out.print("Имя: ");
        String name = sc.next();
        System.out.print("Фамилия: ");
        String surname = sc.next();
        System.out.print("Телефон: ");
        long telephone = sc.nextLong();

        try (PreparedStatement statement = connection.getConnection().prepareStatement(INSERT_RESERVATION)) {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setLong(3, telephone);
            statement.setInt(4, this.ticket.getId());
            statement.executeUpdate();
            Runner.title("\033[1;32m", " ВАС УСПЕШНО ЗАНЕСЕНО В БАЗУ ", "\033[0m");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer checkData(DBConnection connection, String country) {
        Runner.title("", " РЕДАКТИРУЕМЫЕ ДАННЫЕ ", "");
        try (final Statement statement = connection.getConnection().createStatement();
             final ResultSet rs = statement.executeQuery("SELECT * FROM tickets")) {
            while (rs.next()) {
                if (country.equals(rs.getString("country"))) {
                    this.ticket = new Ticket(rs.getInt("id"),
                            rs.getString("country"),
                            rs.getInt("price"));
                    return rs.getInt("price");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}