package ru.poker.Classes;

public class Cart {
    private String value;
    private int valueNum;
    private String suit;
    private boolean inUse;

    public Cart(String value, int valueNum, String suit, boolean inUse) {
        this.value = value;
        this.valueNum = valueNum;
        this.suit = suit;
        this.inUse = inUse;
    }

    public String getValue() {
        return value;
    }

    public int getValueNum() {
        return valueNum;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setValueNum(int valueNum) {
        this.valueNum = valueNum;
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
