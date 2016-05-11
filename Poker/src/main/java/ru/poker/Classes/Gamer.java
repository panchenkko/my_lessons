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

    public void initializationOneCarts() {
        this.oneCart = new Cart("", "", false);
    }

    public void initializationTwoCarts() {
        this.twoCart = new Cart("", "", false);
    }

    public void nullCarts() {
        this.oneCart = null;
        this.twoCart = null;
    }

    public void setOneCart(Cart oneCart) {
        this.oneCart = oneCart;
    }

    public void setTwoCart(Cart twoCart) {
        this.twoCart = twoCart;
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
                "Активность игрока: " + this.inGame + "\n" +
                "Карты: \n" + drawCartLogic();
    }

    public String drawCartLogic() {
        if (this.oneCart.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m") &&
                this.twoCart.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m"))
            return drawDoubleSpaceCart();
        else if (this.oneCart.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m"))
            return drawOneCart();
        else if (this.twoCart.getValue().equals("\033[1;31;40m" + "10" + "\033[1;40m"))
            return drawTwoCart();
        else
            return drawCartStandard();
    }

    public String drawDoubleSpaceCart() {
        return String.format(
                "\033[1;40m" + " %s      " + "\033[0m" + "    " + "\033[1;40m" +" %s      "  + "\033[0m" + "\n" +
                "\033[1;40m" + "         " + "\033[0m" + "    " + "\033[1;40m" +"         "  + "\033[0m" + "\n" +
                "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " + "\033[1;40m" +"    %s%s  "  + "\033[0m" + "\n" +
                "\033[1;40m" + "         " + "\033[0m" + "    " + "\033[1;40m" +"         "  + "\033[0m" + "\n" +
                "\033[1;40m" + "      %s " + "\033[0m" + "    " + "\033[1;40m" +"      %s "  + "\033[0m" + "\n",
                this.oneCart.getValue(), this.twoCart.getValue(),
                this.oneCart.getSuit(), "\033[1;30;40m♥\033[1;40m", this.twoCart.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.oneCart.getValue(), this.twoCart.getValue());
    }

    public String drawOneCart() {
        return String.format(
                "\033[1;40m" + " %s      " + "\033[0m" + "    " + "\033[1;40m" +" %s       "  + "\033[0m" + "\n" +
                "\033[1;40m" + "         " + "\033[0m" + "    " + "\033[1;40m" +"         "  + "\033[0m" + "\n" +
                "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " + "\033[1;40m" +"    %s%s  "  + "\033[0m" + "\n" +
                "\033[1;40m" + "         " + "\033[0m" + "    " + "\033[1;40m" +"         "  + "\033[0m" + "\n" +
                "\033[1;40m" + "      %s " + "\033[0m" + "    " + "\033[1;40m" +"       %s "  + "\033[0m" + "\n",
                this.oneCart.getValue(), this.twoCart.getValue(),
                this.oneCart.getSuit(), "\033[1;30;40m♥\033[1;40m", this.twoCart.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.oneCart.getValue(), this.twoCart.getValue());
    }

    public String drawTwoCart() {
        return String.format(
                "\033[1;40m" + " %s       " + "\033[0m" + "    " + "\033[1;40m" +" %s      "  + "\033[0m" + "\n" +
                "\033[1;40m" + "         " + "\033[0m" + "    " + "\033[1;40m" +"         "  + "\033[0m" + "\n" +
                "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " + "\033[1;40m" +"    %s%s  "  + "\033[0m" + "\n" +
                "\033[1;40m" + "         " + "\033[0m" + "    " + "\033[1;40m" +"         "  + "\033[0m" + "\n" +
                "\033[1;40m" + "       %s " + "\033[0m" + "    " + "\033[1;40m" +"      %s "  + "\033[0m" + "\n",
                this.oneCart.getValue(), this.twoCart.getValue(),
                this.oneCart.getSuit(), "\033[1;30;40m♥\033[1;40m", this.twoCart.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.oneCart.getValue(), this.twoCart.getValue());
    }

    public String drawCartStandard() {
        return String.format(
                "\033[1;40m" + " %s       " + "\033[0m" + "    " + "\033[1;40m" +" %s       "  + "\033[0m" + "\n" +
                "\033[1;40m" + "         " + "\033[0m" + "    " + "\033[1;40m" +"         "  + "\033[0m" + "\n" +
                "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " + "\033[1;40m" +"    %s%s  "  + "\033[0m" + "\n" +
                "\033[1;40m" + "         " + "\033[0m" + "    " + "\033[1;40m" +"         "  + "\033[0m" + "\n" +
                "\033[1;40m" + "       %s " + "\033[0m" + "    " + "\033[1;40m" +"       %s "  + "\033[0m" + "\n",
                this.oneCart.getValue(), this.twoCart.getValue(),
                this.oneCart.getSuit(), "\033[1;30;40m♥\033[1;40m", this.twoCart.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.oneCart.getValue(), this.twoCart.getValue());
    }
}
