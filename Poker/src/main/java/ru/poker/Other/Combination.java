package ru.poker.Other;

import ru.poker.Classes.Cart;
import ru.poker.Classes.Gamer;
import ru.poker.Interfaces.ICombination;
import ru.poker.Classes.Table;

public class Combination implements ICombination {

    private Table table;
    private Gamer[] gamers;

    public Combination(Table table, Gamer[] gamers) {
        this.table = table;
        this.gamers = gamers;
    }

    @Override
    public boolean highCards(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable) {
        return false;
    }

    @Override
    public boolean onePair(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable) {
        return false;
    }

    @Override
    public boolean twoPairs(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable) {
        return false;
    }

    @Override
    public boolean threeOfAKind(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable) {
        return false;
    }

    @Override
    public boolean straight(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable) {
        return false;
    }

    @Override
    public boolean flush(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable) {
        return false;
    }

    @Override
    public boolean fullHouse(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable) {
        return false;
    }

    @Override
    public boolean fourOfAKind(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable) {
        return false;
    }

    @Override
    public boolean straightFlush(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable) {
        return false;
    }

    @Override
    public boolean royalFlush(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable) {
        return false;
    }
}
