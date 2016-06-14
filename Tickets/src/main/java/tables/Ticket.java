package tables;

public class Ticket {
    private int id;
    private String country;
    private int price;

    public Ticket(int id, String country, int price) {
        this.id = id;
        this.country = country;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Билет{" +
                "место='" + country + '\'' +
                ", цена=" + price + "$" +
                '}';
    }
}
