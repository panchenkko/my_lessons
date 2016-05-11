package ru.poker.Classes;

import java.util.Random;

public class Table {

    private Gamer[] gamers; // Игроков за столом
    private Cart[] deck; // Колода

    private int sumCartOnTable = 0; // Сколько на данный момент карт на столе
    private int purse; // Сумма входа (Минимальная сумма, какая должна быть у игрока)

    // Карточки на стол
    private Cart cart1;
    private Cart cart2;
    private Cart cart3;
    private Cart cart4;
    private Cart cart5;

    public int getSumCartOnTable() {
        return sumCartOnTable;
    }

    public int getPurse() {
        return purse;
    }

    public void loadGamers(Gamer[] gamers) {
        this.gamers = gamers;
    }

    public void loadDeck(Cart[] deck) {
        this.deck = deck;
    }

    /* Инициализация карточек */
    public void initializationCart1() {
        this.cart1 = new Cart("", "", false);
    }
    public void initializationCart2() {
        this.cart2 = new Cart("", "", false);
    }
    public void initializationCart3() {
        this.cart3 = new Cart("", "", false);
    }
    public void initializationCart4() {
        this.cart4 = new Cart("", "", false);
    }
    public void initializationCart5() {
        this.cart5 = new Cart("", "", false);
    }

    /* Создаём колоду карт */
    public void writingDeck() {
        for (int i = 0; i < this.deck.length; i++) {
            this.deck[i].setInUse(false);
            heart(i);
            diamonds( i);
            club(i);
            pike(i);
        }
    }
    public void heart(int i) {
        if (i >= 0 && i < 13 ) {
            this.deck[i].setSuit("\033[1;31;40m♥\033[1;40m");
            if (i < 9) {
                this.deck[i].setValue("\033[1;31;40m" + String.valueOf(i + 2) + "\033[1;40m");
            }
            switch (i) {
                case 9:  this.deck[i].setValue("\033[1;31;40m" + "В" + "\033[1;40m");
                         break;
                case 10: this.deck[i].setValue("\033[1;31;40m" + "Д" + "\033[1;40m");
                         break;
                case 11: this.deck[i].setValue("\033[1;31;40m" + "К" + "\033[1;40m");
                         break;
                case 12: this.deck[i].setValue("\033[1;31;40m" + "Т" + "\033[1;40m");
                         break;
            }
        }
    }
    public void diamonds(int i) {
        if (i >= 13 && i < 26 ) {
            this.deck[i].setSuit("\033[1;31;40m♦\033[1;40m");
            if (i < 22) {
                deck[i].setValue("\033[1;31;40m" + String.valueOf(i - 11) + "\033[1;40m");
            }
            switch (i) {
                case 22: this.deck[i].setValue("\033[1;31;40m" + "В" + "\033[1;40m");
                         break;
                case 23: this.deck[i].setValue("\033[1;31;40m" + "Д" + "\033[1;40m");
                         break;
                case 24: this.deck[i].setValue("\033[1;31;40m" + "К" + "\033[1;40m");
                         break;
                case 25: this.deck[i].setValue("\033[1;31;40m" + "Т" + "\033[1;40m");
                         break;
            }
        }
    }
    public void club(int i) {
        if (i >= 26 && i < 39 ) {
            this.deck[i].setSuit("\033[1;37;40m♣\033[1;40m");
            if (i < 35) {
                this.deck[i].setValue("\033[1;31;40m" + String.valueOf(i - 24) + "\033[1;40m");
            }
            switch (i) {
                case 35: this.deck[i].setValue("\033[1;31;40m" + "В" + "\033[1;40m");
                         break;
                case 36: this.deck[i].setValue("\033[1;31;40m" + "Д" + "\033[1;40m");
                         break;
                case 37: this.deck[i].setValue("\033[1;31;40m" + "К" + "\033[1;40m");
                         break;
                case 38: this.deck[i].setValue("\033[1;31;40m" + "Т" + "\033[1;40m");
                         break;
            }
        }
    }
    public void pike(int i) {
        if (i >= 39 && i < 52 ) {
            this.deck[i].setSuit("\033[1;37;40m♠\033[1;40m");
            if (i < 48) {
                this.deck[i].setValue("\033[1;31;40m" + String.valueOf(i - 37) + "\033[1;40m");
            }
            switch (i) {
                case 48: this.deck[i].setValue("\033[1;31;40m" + "В" + "\033[1;40m");
                         break;
                case 49: this.deck[i].setValue("\033[1;31;40m" + "Д" + "\033[1;40m");
                         break;
                case 50: this.deck[i].setValue("\033[1;31;40m" + "К" + "\033[1;40m");
                         break;
                case 51: this.deck[i].setValue("\033[1;31;40m" + "Т" + "\033[1;40m");
                         break;
            }
        }
    }

    // Раздаём карты игрокам
    public void randomCartForGamer() {
        for (Gamer gamer : this.gamers) {
            gamer.randomCart(this.deck);
        }
    }

    // Титулка
    public void inscription(String inscription) {
        System.out.println();
        System.out.println(String.format("==============%s==============", inscription));
        System.out.println();
    }

    // Вывод всех игроков за столом
    public void drawGamers() {
        inscription(" ВСЕ ИГРОКИ ");
        for (Gamer gamer : this.gamers) {
            System.out.println(gamer.toString());
            System.out.println();
        }
    }

    // Случайные три карточки на стол
    public void randomCartForTable() {
        this.sumCartOnTable = 3;
        int inc = 0;
        while (inc < sumCartOnTable || this.cart1 == null || this.cart2 == null || this.cart3 == null) {
            int rand = new Random().nextInt(51) + 1;
            if (!this.deck[rand].isInUse()) {
                if (this.cart1 == null) {
                    initializationCart1();
                    this.cart1.setSuit(this.deck[rand].getSuit());
                    this.cart1.setValue(this.deck[rand].getValue());
                    this.deck[rand].setInUse(true);
                } else
                if (this.cart2 == null) {
                    initializationCart2();
                    this.cart2.setSuit(this.deck[rand].getSuit());
                    this.cart2.setValue(this.deck[rand].getValue());
                    this.deck[rand].setInUse(true);
                } else
                if (this.cart3 == null) {
                    initializationCart3();
                    this.cart3.setSuit(this.deck[rand].getSuit());
                    this.cart3.setValue(this.deck[rand].getValue());
                    this.deck[rand].setInUse(true);
                }
            }
            inc++;
        }
    }

    public void drawTable() {
        inscription("============СТОЛ============");
        System.out.print(draw3CartLogic());
        inscription("============СТОЛ============");
    }

    /* ЛОГИКА ВЫВОДА ДЛЯ ТРЁХ КАРТ */
    public String draw3CartLogic() {
        if (this.cart1.getValue().equals("10") &&
            this.cart2.getValue().equals("10") &&
            this.cart3.getValue().equals("10"))
            return draw3CartFirstSecondThird10();
        else
        if (this.cart1.getValue().equals("10") && this.cart2.getValue().equals("10"))
            return draw3CartFirstSecond10();
        else
        if (this.cart1.getValue().equals("10") && this.cart3.getValue().equals("10"))
            return draw3CartFirstThird10();
        else
        if (this.cart2.getValue().equals("10") && this.cart3.getValue().equals("10"))
            return draw3CartSecondThird10();
        else if (this.cart1.getValue().equals("10")) return draw3CartFirst10();
        else if (this.cart2.getValue().equals("10")) return draw3CartSecond10();
        else if (this.cart3.getValue().equals("10")) return draw3CartThird10();
        else return draw3CartStandard();
    }

    public String draw3CartFirstSecondThird10() {
        return String.format(
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue());
    }

    public String draw3CartSecondThird10() {
        return String.format(
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue());
    }
    public String draw3CartFirstThird10() {
        return String.format(
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue());
    }
    public String draw3CartFirstSecond10() {
        return String.format(
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue());
    }

    public String draw3CartThird10() {
        return String.format(
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue());
    }
    public String draw3CartSecond10() {
        return String.format(
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue());
    }
    public String draw3CartFirst10() {
        return String.format(
                        "\033[1;40m" + " %s      " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "      %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue());
    }

    public String draw3CartStandard() {
        return String.format(
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "    " +
                        "\033[1;40m" + " %s       " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "    " +
                        "\033[1;40m" + "    %s%s  " + "\033[0m" + "\n" +

                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "    " +
                        "\033[1;40m" + "         " + "\033[0m" + "\n" +

                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "    " +
                        "\033[1;40m" + "       %s " + "\033[0m" + "\n",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue(),
                this.cart1.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart2.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart3.getSuit(), "\033[1;30;40m♥\033[1;40m",
                this.cart1.getValue(), this.cart2.getValue(), this.cart3.getValue());
    }
}
