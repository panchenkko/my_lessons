package tables;

public class Customer {
    private String name;
    private String surname;
    private long telephone;
    private Ticket ticket;

    public Customer(String name, String surname, long telephone, Ticket ticket) {
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "Заказ{" +
                "Имя='" + name + '\'' +
                ", Фамилия='" + surname + '\'' +
                ", Телефон=" + telephone +
                ", Место='" + ticket.getCountry() + '\'' +
                ", Цена=" + ticket.getPrice() +
                '}';
    }
}