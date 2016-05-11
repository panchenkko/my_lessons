package ru.poker.Classes;

import java.util.Random;

public class Table {

    private Gamer[] gamers;
    private Cart[] deck;

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

    public void loadGamers(Gamer[] gamers) {
        this.gamers = gamers;
    }

    public void loadDeck(Cart[] deck) {
        this.deck = deck;
    }

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
            }
        }
    }

    public void drawDeck(Cart[] deck) {
        for (Cart cart : deck) {
            System.out.println(cart.getSuit() + " " + cart.getValue());
        }
    }

    public void drawGamers() {
        for (Gamer gamer : this.gamers) {
            System.out.println(gamer.toString());
            System.out.println();
        }
    }
}
