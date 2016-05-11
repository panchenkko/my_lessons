package ru.poker.Interfaces;

import ru.poker.Classes.Cart;

public interface ICombination {
    boolean highCards(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable);
    boolean onePair(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable);
    boolean twoPairs(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable);
    boolean threeOfAKind(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable);
    boolean straight(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable);
    boolean flush(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable);
    boolean fullHouse(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable);
    boolean fourOfAKind(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable);
    boolean straightFlush(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable);
    boolean royalFlush(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable);
}
