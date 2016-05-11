package ru.poker.Classes;

import java.util.Random;

public class Gamer {
    private int id;
    private String name;
    private int money; // кошелёк
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

    public void initializationOneCart() {
        this.oneCart = new Cart("", "", false);
    }

    public void initializationTwoCart() {
        this.twoCart = new Cart("", "", false);
    }

    public void nullCarts() {
        this.oneCart = null;
        this.twoCart = null;
    }

    @Override
    public String toString() {
        return this.id + ". " + this.name + "\n" +
                "Кошелёк: " + this.money + "\n" +
                "Сделанная ставка: " + this.rate + "\n" +
                "Активность игрока: " + this.inGame + "\n" +
                "Карты: \n" + drawCartLogic();
    }

    // Логика вывода карт игрока
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
                "\033[1;40m" + " %s      "  + "\033[0m" + "    " + "\033[1;40m" + " %s      "   + "\033[0m" + "\n" +
                "\033[1;40m" + "         "  + "\033[0m" + "    " + "\033[1;40m" + "         "   + "\033[0m" + "\n" +
                "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " + "\033[1;40m" + "    %s%s  "  + "\033[0m" + "\n" +
                "\033[1;40m" + "         "  + "\033[0m" + "    " + "\033[1;40m" + "         "   + "\033[0m" + "\n" +
                "\033[1;40m" + "      %s "  + "\033[0m" + "    " + "\033[1;40m" + "      %s "   + "\033[0m" + "\n",
                this.oneCart.getValue(), this.twoCart.getValue(),
                this.oneCart.getSuit(), "\033[1;30;40m♥\033[1;40m", this.twoCart.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.oneCart.getValue(), this.twoCart.getValue());
    }
    public String drawOneCart() {
        return String.format(
                "\033[1;40m" + " %s      "  + "\033[0m" + "    " + "\033[1;40m" + " %s       "  + "\033[0m" + "\n" +
                "\033[1;40m" + "         "  + "\033[0m" + "    " + "\033[1;40m" + "         "   + "\033[0m" + "\n" +
                "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " + "\033[1;40m" + "    %s%s  "  + "\033[0m" + "\n" +
                "\033[1;40m" + "         "  + "\033[0m" + "    " + "\033[1;40m" + "         "   + "\033[0m" + "\n" +
                "\033[1;40m" + "      %s "  + "\033[0m" + "    " + "\033[1;40m" + "       %s "  + "\033[0m" + "\n",
                this.oneCart.getValue(), this.twoCart.getValue(),
                this.oneCart.getSuit(), "\033[1;30;40m♥\033[1;40m", this.twoCart.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.oneCart.getValue(), this.twoCart.getValue());
    }
    public String drawTwoCart() {
        return String.format(
                "\033[1;40m" + " %s       " + "\033[0m" + "    " + "\033[1;40m" + " %s      "  + "\033[0m" + "\n" +
                "\033[1;40m" + "         "  + "\033[0m" + "    " + "\033[1;40m" + "         "  + "\033[0m" + "\n" +
                "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " + "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +
                "\033[1;40m" + "         "  + "\033[0m" + "    " + "\033[1;40m" + "         "  + "\033[0m" + "\n" +
                "\033[1;40m" + "       %s " + "\033[0m" + "    " + "\033[1;40m" + "      %s "  + "\033[0m" + "\n",
                this.oneCart.getValue(), this.twoCart.getValue(),
                this.oneCart.getSuit(), "\033[1;30;40m♥\033[1;40m", this.twoCart.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.oneCart.getValue(), this.twoCart.getValue());
    }
    public String drawCartStandard() {
        return String.format(
                "\033[1;40m" + " %s       " + "\033[0m" + "    " + "\033[1;40m" + " %s       "  + "\033[0m" + "\n" +
                "\033[1;40m" + "         "  + "\033[0m" + "    " + "\033[1;40m" + "         "   + "\033[0m" + "\n" +
                "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " + "\033[1;40m" + "    %s%s  "  + "\033[0m" + "\n" +
                "\033[1;40m" + "         "  + "\033[0m" + "    " + "\033[1;40m" + "         "   + "\033[0m" + "\n" +
                "\033[1;40m" + "       %s " + "\033[0m" + "    " + "\033[1;40m" + "       %s "  + "\033[0m" + "\n",
                this.oneCart.getValue(), this.twoCart.getValue(),
                this.oneCart.getSuit(), "\033[1;30;40m♥\033[1;40m", this.twoCart.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.oneCart.getValue(), this.twoCart.getValue());
    }

    // Раздаем случайные карты игроку
    public void randomCart(Cart[] deck) {
        nullCarts();
        while (this.oneCart == null || this.twoCart == null) {
            int rand = new Random().nextInt(51) + 1;
            if (!deck[rand].isInUse()) {
                if (this.oneCart == null) {
                    initializationOneCart();
                    this.oneCart.setSuit(deck[rand].getSuit());
                    this.oneCart.setValue(deck[rand].getValue());
                    deck[rand].setInUse(true);
                }
                else if (this.twoCart == null) {
                    initializationTwoCart();
                    this.twoCart.setSuit(deck[rand].getSuit());
                    this.twoCart.setValue(deck[rand].getValue());
                    deck[rand].setInUse(true);
                }
            }
        }
    }
}
