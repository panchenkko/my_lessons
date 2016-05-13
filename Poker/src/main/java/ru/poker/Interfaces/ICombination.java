package ru.poker.Interfaces;

import ru.poker.Classes.Cart;
import ru.poker.Classes.Gamer;

public interface ICombination {
    boolean highCards(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five);
    boolean onePair(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five);
    boolean twoPairs(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five);
    boolean threeOfAKind(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five);
    boolean straight(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five);
    boolean flush(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five);
    boolean fullHouse(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five);
    boolean fourOfAKind(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five);
    boolean straightFlush(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five);
    boolean royalFlush(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five);
//    int highCards(Gamer[] gamers, Cart[] cartsTable);
//    int onePair(Gamer[] gamers, Cart[] cartsTable);
//    int twoPairs(Gamer[] gamers, Cart[] cartsTable);
//    int threeOfAKind(Gamer[] gamers, Cart[] cartsTable);
//    int straight(Gamer[] gamers, Cart[] cartsTable);
//    int flush(Gamer[] gamers, Cart[] cartsTable);
//    int fullHouse(Gamer[] gamers, Cart[] cartsTable);
//    int fourOfAKind(Gamer[] gamers, Cart[] cartsTable);
//    int straightFlush(Gamer[] gamers, Cart[] cartsTable);
//    int royalFlush(Gamer[] gamers, Cart[] cartsTable);
}
