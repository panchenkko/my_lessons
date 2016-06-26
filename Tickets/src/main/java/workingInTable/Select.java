package workingInTable;

import run.Runner;
import settings.DBConnection;
import tables.Customer;
import tables.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Select {

    private static final String SELECT_TICKETS = "SELECT * FROM tickets ORDER BY id";
    private static final String SELECT_CUSTOMER = "SELECT * FROM reservations r " +
                                                  "LEFT JOIN tickets t ON t.id = r.ticket_id " +
                                                  "ORDER BY r.id";

    public void input(Scanner sc, DBConnection connection) {
        Runner.title("", " ВЫБЕРИТЕ ТАБЛИЦУ ", "");
        System.out.println("1. Вывести все билеты");
        System.out.println("2. Вывести все заказы" + "\n");
        System.out.print("Ответ: ");
        switch (sc.nextInt()) {
            case 1: outputTickets(connection);
                    break;
            case 2: outputReservations(connection);
                    break;
        }
    }

    public static void outputTickets(DBConnection connection) {
        Runner.title("", " СПИСОК ВСЕХ БИЛЕТОВ ", "");
        try (Statement statement = connection.getConnection().createStatement();
             ResultSet rs = statement.executeQuery(SELECT_TICKETS)) {
            while (rs.next()) {
                Ticket ticket = new Ticket(rs.getInt("id"),
                                           rs.getString("country"),
                                           rs.getInt("price"));
                System.out.println(ticket.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void outputReservations(DBConnection connection) {
        Runner.title("", " СПИСОК ВСЕХ БРОНЕЙ ", "");
        try (Statement statement = connection.getConnection().createStatement();
             ResultSet rs = statement.executeQuery(SELECT_CUSTOMER)) {
            while (rs.next()) {
                Ticket ticket = new Ticket(rs.getInt("id"),
                                           rs.getString("country"),
                                           rs.getInt("price"));
                Customer customer = new Customer(rs.getString("name"),
                                                 rs.getString("surname"),
                                                 rs.getLong("telephone"), ticket);
                System.out.println(customer.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
