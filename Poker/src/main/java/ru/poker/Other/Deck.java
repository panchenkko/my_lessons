package ru.poker.Other;

import ru.poker.Classes.Cart;

public class Deck {

    private final Cart[] deck;

    public Deck(Cart[] deck) {
        this.deck = deck;
    }

    public void writingDeck() {
        for (int i = 0; i < deck.length; i++) {
            deck[i].setInUse(false);
            heart(i);
            diamonds(i);
            club(i);
            pike(i);
        }
    }

    public void heart(int i) {
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
    public void diamonds(int i) {
        if (i >= 13 && i < 26 ) {
            deck[i].setSuit("Бубна");
            if (i < 22) {
                deck[i].setValue(String.valueOf(i + 2));
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
    public void club(int i) {
        if (i >= 26 && i < 39 ) {
            deck[i].setSuit("Крести");
            if (i < 35) {
                deck[i].setValue(String.valueOf(i + 2));
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
    public void pike(int i) {
        if (i >= 39 && i < 52 ) {
            deck[i].setSuit("Крести");
            if (i < 48) {
                deck[i].setValue(String.valueOf(i + 2));
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
}
