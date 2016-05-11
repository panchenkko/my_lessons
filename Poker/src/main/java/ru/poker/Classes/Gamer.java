package ru.poker.Classes;

public class Gamer {
    private int id;
    private String name;
    private int money; // кошелек
    private Cart oneCart;
    private Cart twoCart;
    private int store; // выпавшая комбинация (по нумерации)
    private boolean inGame; // играет ли игрок в этой партии
    private int rate; // ставка

    public Gamer(int id, String name, int money, boolean inGame) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.inGame = inGame;
    }

    public void setCart(String value, String suit, boolean inUse) {
        if (this.oneCart == null)
            this.oneCart = new Cart(value, suit, inUse);
        else if (this.twoCart == null)
            this.twoCart = new Cart(value, suit, inUse);
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

    public Cart getTwoCart() {
        return twoCart;
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

    @Override
    public String toString() {
        return this.id + ". " + this.name + "\n" +
                "Кошелёк: " + this.money + "\n" +
                "Сделанная ставка: " + this.rate + "\n" +
                "Активность игрока: " + this.inGame;
//                +
//                "Карты: " + "(" + this.oneCart.getSuit() + " " + this.oneCart.getValue() + ") " +
//                            "(" + this.twoCart.getSuit() + " " + this.twoCart.getValue() + ")";
    }
}
