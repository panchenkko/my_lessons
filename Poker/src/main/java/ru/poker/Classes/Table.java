package ru.poker.Classes;

import java.util.Random;

public class Table {

    private Gamer[] gamers;
    private Cart[] deck;

<<<<<<< HEAD
    public Cart randomCart(Cart[] deck) {
        for (Gamer gamer : this.gamers) {
            while (gamer.getOneCart() != null && gamer.getTwoCart() != null) {
                int rand = new Random().nextInt(51) + 1;
                if (!deck[rand].isInUse()) {
                    deck[rand].setInUse(true);
                    gamer.setCart(deck[rand].getValue(), deck[rand].getSuit(), false);
                }
            }
        }
        return null;
    }

    public Cart[] getDeck() {
        return deck;
    }

=======
>>>>>>> 3b6ea5bd9eb56e9d3809b0f54d6ef652df9e94c8
    public void loadGamers(Gamer[] gamers) {
        this.gamers = gamers;
    }

    public void loadDeck(Cart[] deck) {
        this.deck = deck;
    }

<<<<<<< HEAD
    public void writingDeck(Cart[] deck) {
        for (int i = 0; i < deck.length; i++) {
            deck[i].setInUse(false);
            heart(deck, i);
            diamonds(deck, i);
            club(deck, i);
            pike(deck, i);
        }
    }

    public void heart(Cart[] deck, int i) {
        if (i >= 0 && i < 13 ) {
            deck[i].setSuit("Черви");
            if (i < 9) {
                deck[i].setValue(String.valueOf(i + 2));
            }
            switch (i) {
                case 9: deck[i].setValue("Валет");
                    break;
                case 10: deck[i].setValue("Дама");
                    break;
                case 11: deck[i].setValue("Король");
                    break;
                case 12: deck[i].setValue("Туз");
                    break;
            }
        }
    }
    public void diamonds(Cart[] deck, int i) {
        if (i >= 13 && i < 26 ) {
            deck[i].setSuit("Бубна");
            if (i < 22) {
                deck[i].setValue(String.valueOf(i - 11));
            }
            switch (i) {
                case 22: deck[i].setValue("Валет");
                    break;
                case 23: deck[i].setValue("Дама");
                    break;
                case 24: deck[i].setValue("Король");
                    break;
                case 25: deck[i].setValue("Туз");
                    break;
            }
        }
    }
    public void club(Cart[] deck, int i) {
        if (i >= 26 && i < 39 ) {
            deck[i].setSuit("Крести");
            if (i < 35) {
                deck[i].setValue(String.valueOf(i - 24));
            }
            switch (i) {
                case 35: deck[i].setValue("Валет");
                    break;
                case 36: deck[i].setValue("Дама");
                    break;
                case 37: deck[i].setValue("Король");
                    break;
                case 38: deck[i].setValue("Туз");
                    break;
            }
        }
    }
    public void pike(Cart[] deck, int i) {
        if (i >= 39 && i < 52 ) {
            deck[i].setSuit("Пика");
            if (i < 48) {
                deck[i].setValue(String.valueOf(i - 37));
            }
            switch (i) {
                case 48: deck[i].setValue("Валет");
                    break;
                case 49: deck[i].setValue("Дама");
                    break;
                case 50: deck[i].setValue("Король");
                    break;
                case 51: deck[i].setValue("Туз");
                    break;
=======


    public void randomCart() {
        for (Gamer gamer : this.gamers) {
            gamer.nullCarts();
            while (gamer.getOneCart() == null || gamer.getTwoCart() == null) {
                int rand = new Random().nextInt(51) + 1;
                if (!this.deck[rand].isInUse()) {
                    if (gamer.getOneCart() == null) {
                        gamer.initializationOneCarts();
                        gamer.getOneCart().setSuit(this.deck[rand].getSuit());
                        gamer.getOneCart().setValue(this.deck[rand].getValue());
                        this.deck[rand].setInUse(true);
                    }
                    else if (gamer.getTwoCart() == null) {
                        gamer.initializationTwoCarts();
                        gamer.getTwoCart().setSuit(this.deck[rand].getSuit());
                        gamer.getTwoCart().setValue(this.deck[rand].getValue());
                        this.deck[rand].setInUse(true);
                    }
                }
            }
        }
    }

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
>>>>>>> 3b6ea5bd9eb56e9d3809b0f54d6ef652df9e94c8
            }
        }
    }

<<<<<<< HEAD
    public void drawDeck(Cart[] deck) {
        for (Cart cart : deck) {
=======
    public void drawDeck() {
        for (Cart cart : this.deck) {
>>>>>>> 3b6ea5bd9eb56e9d3809b0f54d6ef652df9e94c8
            System.out.println(cart.getSuit() + " " + cart.getValue());
        }
    }

    public void drawGamers() {
        for (Gamer gamer : this.gamers) {
            System.out.println(gamer.toString());
            System.out.println();
        }
    }
<<<<<<< HEAD
=======

    public void draw3Cart(String suit1, String suit2, String suit3,
                          String value1, String value2, String value3) {
        System.out.println(String.format(
                " ------------- " + "    " + " ------------- " + "    " + " ------------- " + "\n" +
                "|%s           |" + "    " + "|%s           |" + "    " + "|%s           |" + "\n" +
                "|             |" + "    " + "|             |" + "    " + "|             |" + "\n" +
                "|             |" + "    " + "|             |" + "    " + "|             |" + "\n" +
                "|             |" + "    " + "|             |" + "    " + "|             |" + "\n" +
                "|     %s      |" + "    " + "|     %s      |" + "    " + "|     %s      |" + "\n" +
                "|             |" + "    " + "|             |" + "    " + "|             |" + "\n" +
                "|             |" + "    " + "|             |" + "    " + "|             |" + "\n" +
                "|             |" + "    " + "|             |" + "    " + "|             |" + "\n" +
                "|           %s|" + "    " + "|           %s|" + "    " + "|           %s|" + "\n" +
                " ------------- " + "    " + " ------------- " + "    " + " ------------- " + "\n",
                value1, value2, value3, suit1, suit2, suit3, value1, value2, value3));
    }
>>>>>>> 3b6ea5bd9eb56e9d3809b0f54d6ef652df9e94c8
}
