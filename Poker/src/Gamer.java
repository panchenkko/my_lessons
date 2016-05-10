
public class Gamer {
    private int id;
    private String name;
    private int money;
    private Cart oneCart;
    private Cart twoCart;
    private int store;
    private boolean inGame;
    private int rate;

    public Gamer(int id, String name, int money, boolean inGame) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.inGame = inGame;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Cart getOneCart() {
        return oneCart;
    }

    public void setOneCart(Cart oneCart) {
        this.oneCart = oneCart;
    }

    public Cart getTwoCart() {
        return twoCart;
    }

    public void setTwoCart(Cart twoCart) {
        this.twoCart = twoCart;
    }

    public int getStore() {
        return store;
    }

    public void setStore(int store) {
        this.store = store;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
