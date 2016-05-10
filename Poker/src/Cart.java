
public class Cart {
    private String value;
    private String suit;
    private boolean inUse;

    public Cart(String value, String suit, boolean inUse) {
        this.value = value;
        this.suit = suit;
        this.inUse = inUse;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }
}
