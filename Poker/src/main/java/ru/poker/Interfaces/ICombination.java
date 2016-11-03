package ru.poker.Interfaces;

import ru.poker.Classes.Cart;
import ru.poker.Classes.Gamer;

public interface ICombination {
    int highCards(Cart cartGamer1, Cart cartGamer2);
    int onePair(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five);
    int twoPairs(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five);
    int threeOfAKind(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five);
    int straight(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five);
    int flush(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five);
    int fullHouse(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five);
    int fourOfAKind(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five);
    int straightFlush(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five);
    int royalFlush(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five);
}