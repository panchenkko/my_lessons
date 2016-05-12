package ru.poker.Interfaces;

import ru.poker.Classes.Cart;
import ru.poker.Classes.Gamer;

public interface ICombination {
    int highCards(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable);
    int onePair(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable);
    int twoPairs(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable);
    int threeOfAKind(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable);
    int straight(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable);
    int flush(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable);
    int fullHouse(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable);
    int fourOfAKind(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable);
    int straightFlush(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable);
    int royalFlush(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable);
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
