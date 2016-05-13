package ru.poker.Classes;

import ru.poker.Interfaces.ICombination;

public class Combination implements ICombination {

    @Override
    public boolean highCards(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five) {
        return false;
    }

    @Override
    public boolean onePair(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five) {
        return false;
    }

    @Override
    public boolean twoPairs(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five) {
        return false;
    }

    @Override
    public boolean threeOfAKind(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five) {
        return false;
    }

    @Override
    public boolean straight(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five) {
        return false;
    }

    @Override
    public boolean flush(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five) {
        return false;
    }

    @Override
    public boolean fullHouse(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five) {
        return false;
    }

    @Override
    public boolean fourOfAKind(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five) {
        return false;
    }

    @Override
    public boolean straightFlush(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five) {
        return false;
    }

    @Override
    public boolean royalFlush(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five) {
        return false;
    }
}
