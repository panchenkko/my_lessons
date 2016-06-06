package ru.poker.Classes;

import ru.poker.Interfaces.ICombination;

public class Combination implements ICombination {

    @Override
    public int highCards(Cart cartGamer1, Cart cartGamer2) {
        if(cartGamer1.getValueNum() > cartGamer2.getValueNum())
            return cartGamer1.getValueNum();
        else
            return cartGamer2.getValueNum();
    }

    @Override
    public int onePair(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five) {
        Cart[] cartsTable = {one, two, three, four, five};
        int counter ;
        int pairCounter;
        int retValue = 0;
        int buff = 1;

        if (cartGamer1.getValueNum() == cartGamer2.getValueNum()) {
            buff = cartGamer1.getValueNum();
        } else {
            counter = 0;
            pairCounter = buff;
            while (counter != cartsTable.length) {
                if(cartGamer1.getValueNum() == cartsTable[counter].getValueNum()) {
                    retValue = cartGamer1.getValueNum();
                    pairCounter++;
                }
                counter++;
            }
            if(pairCounter == 2)
                retValue = cartGamer1.getValueNum();
            // Сравнили первую карту с колодой, дальше сравниваем вторую:

            counter = 0;
            pairCounter = buff;
            while (counter != cartsTable.length) {
                if(cartGamer2.getValueNum() == cartsTable[counter].getValueNum()) {
                    pairCounter++;
                    retValue = cartGamer2.getValueNum();
                }
                counter++;
            }
            if(pairCounter == 2)
                retValue = cartGamer2.getValueNum();
        }
        return retValue;
    }

    @Override
    public int twoPairs(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five) {
        Cart[] cartsTable = {one, two, three, four, five};
        int retValue = 0; // returtValue, а не rateValue
        int counter ;
        int pairCounter;
        int firstPair = onePair(cartGamer1, cartGamer2, one, two, three, four, five);
        int buff = 1;

        if(firstPair != 0) {
            if (cartGamer1.getValueNum() == cartGamer2.getValueNum() ||
                cartGamer1.getValueNum() != firstPair) {
            buff = 2;
            }
            counter = 0;
            pairCounter = buff;
            while (counter != cartsTable.length) {
                if (cartGamer1.getValueNum() == cartsTable[counter].getValueNum() &&
                        cartsTable[counter].getValueNum() != firstPair) {
                    pairCounter++;
                    if(cartGamer1.getValueNum() > firstPair)
                        retValue = cartGamer1.getValueNum();
                    else
                        retValue = firstPair;
                }
                counter++;
            }
            if (pairCounter == 2)
                retValue = cartGamer1.getValueNum();
            else
                retValue = 0;
            // Сравнили первую карту с колодой, дальше сравниваем вторую:

            counter = 0;
            pairCounter = buff;
            while (counter != cartsTable.length) {
                if (cartGamer2.getValueNum() == cartsTable[counter].getValueNum() &&
                        cartsTable[counter].getValueNum() != firstPair) {
                    pairCounter++;
                    if(cartGamer2.getValueNum() > firstPair)
                        retValue = cartGamer1.getValueNum();
                    else
                        retValue = firstPair;
                }
                counter++;
            }
            if (pairCounter == 2)
                retValue = cartGamer2.getValueNum();
            else
                retValue = 0;
        }
        return retValue;
    }

    @Override
    public int threeOfAKind(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five) {
        Cart[] cartsTable = {one, two, three, four, five};
        int counter ;
        int pairCounter;
        int buff = 1;
        int retValue = 0;

        if (cartGamer1.getValueNum() == cartGamer2.getValueNum()) {
            buff = 2;
        }
        counter = 0;
        pairCounter = buff;
        while (counter != cartsTable.length) {
            if(cartGamer1.getValueNum() == cartsTable[counter].getValueNum())
                pairCounter++;
            counter++;
        }
        if(pairCounter == 3) // Поменяли значение с метода "пара" тут
            retValue = cartGamer1.getValueNum();
        // Сравнили первую карту с колодой, дальше сравниваем вторую:

        counter = 0;
        pairCounter = buff;
        while (counter != cartsTable.length) {
            if(cartGamer1.getValueNum() == cartsTable[counter].getValueNum())
                pairCounter++;
            counter++;
        }
        if(pairCounter == 3) // Поменяли значение с метода "пара" тут
            retValue = cartGamer1.getValueNum();

        return retValue;
    }

    @Override
    public int straight(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five) {
        Cart[] cartsTable = {one, two, three, four, five};
        int counter ;
        int straightCounter;
        int straightValue;
        int higtestCart = cartGamer1.getValueNum();

        counter = 0;
        straightCounter = 1;
        straightValue = cartGamer1.getValueNum();
        while(counter != cartsTable.length) {
            if(cartsTable[counter].getValueNum() == (straightValue + 1)) {
                straightCounter++;
                straightValue++;
                higtestCart = cartsTable[counter].getValueNum();
                counter = -1;
            } else
            if(cartGamer2.getValueNum() == (straightValue + 1)) {
                straightCounter++;
                straightValue++;
                higtestCart = cartGamer2.getValueNum();
                counter = -1;
            }
            counter++;
        }

        if(cartGamer1.getValueNum() == (cartGamer2.getValueNum() - 1)){
            straightCounter++;
            counter = 0;
            straightValue = cartGamer2.getValueNum();

            while(counter != cartsTable.length) {
                if(cartsTable[counter].getValueNum() == (straightValue - 1)) {
                    straightCounter++;
                    straightValue--;
                    counter = -1;
                } else
                if(cartGamer2.getValueNum() == (straightValue - 1)) {
                    straightCounter++;
                    straightValue--;
                    counter = -1;
                }
                counter++;
            }
        } else {
            counter = 0;
            straightValue = cartGamer1.getValueNum();
            while (counter != cartsTable.length) {
                if (cartsTable[counter].getValueNum() == (straightValue - 1)) {
                    straightCounter++;
                    straightValue--;
                    counter = -1;
                } else
                if(cartGamer2.getValueNum() == (straightValue - 1)) {
                    straightCounter++;
                    straightValue--;
                    counter = -1;
                }
                counter++;
            }

        }

        if(straightCounter >= 5)
            return higtestCart;

        counter = 0;
        straightCounter = 1;
        straightValue = cartGamer2.getValueNum();
        while(counter != cartsTable.length) {
            if(cartsTable[counter].getValueNum() == (straightValue + 1)) {
                straightCounter++;
                straightValue++;
                higtestCart = cartsTable[counter].getValueNum();
                counter = -1;
            } else
            if(cartGamer1.getValueNum() == (straightValue + 1)) {
                straightCounter++;
                straightValue++;
                higtestCart = cartGamer1.getValueNum();
                counter = -1;
            }
            counter++;
        }

        if(cartGamer2.getValueNum() == (cartGamer1.getValueNum() - 1)){
            straightCounter++;
            counter = 0;
            straightValue = cartGamer1.getValueNum();

            while(counter != cartsTable.length) {
                if(cartsTable[counter].getValueNum() == (straightValue - 1)) {
                    straightCounter++;
                    straightValue--;
                    counter = -1;
                } else
                if(cartGamer1.getValueNum() == (straightValue - 1)) {
                    straightCounter++;
                    straightValue--;
                    higtestCart = cartGamer1.getValueNum();
                    counter = -1;
                }
                counter++;
            }
        } else {
            counter = 0;
            straightValue = cartGamer2.getValueNum();
            while (counter != cartsTable.length) {
                if (cartsTable[counter].getValueNum() == (straightValue - 1)) {
                    straightCounter++;
                    straightValue--;
                    counter = -1;
                } else
                if(cartGamer1.getValueNum() == (straightValue - 1)) {
                    straightCounter++;
                    straightValue--;
                    counter = -1;
                }
                counter++;
            }
        }

        if(straightCounter >= 5)
            return higtestCart;
        else
            return 0;
    }

    @Override
    public int flush(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five) {
        Cart[] cartsTable = {one, two, three, four, five};
        int counter ;
        int suitCounter;
        int buff = 0;
        int retValue = 0;

        if (cartGamer1.getSuit() == cartGamer2.getSuit()) {
            buff = 2;
        }
        counter = 0;
        suitCounter = buff;
        while (counter != cartsTable.length) {
            if(cartGamer1.getSuit() == cartsTable[counter].getSuit())
                suitCounter++;
            counter++;
        }
        if(suitCounter >= 5)
            retValue = cartGamer1.getValueNum();
        // Сравнили первую карту с колодой, дальше сравниваем вторую:

        counter = 0;
        suitCounter = buff;
        while (counter != cartsTable.length) {
            if(cartGamer2.getSuit() == cartsTable[counter].getSuit())
                suitCounter++;
            counter++;
        }
        if(suitCounter >= 5)
            retValue = cartGamer2.getValueNum();

        return retValue;
    }

    @Override
    public int fullHouse(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five) {
        int retValue = 0;
        if((((onePair(cartGamer1, cartGamer2, one, two, three, four, five) > 0) &&
           (threeOfAKind(cartGamer1, cartGamer2, one, two, three, four, five) > 0)) ||
          (((twoPairs(cartGamer1, cartGamer2, one, two, three, four, five) > 0) &&
           (threeOfAKind(cartGamer1, cartGamer2, one, two, three, four, five) > 0)))) &&
                ((twoPairs(cartGamer1, cartGamer2, one, two, three, four, five)) !=
                        (threeOfAKind(cartGamer1, cartGamer2, one, two, three, four, five)))) {
            if(threeOfAKind(cartGamer1, cartGamer2, one, two, three, four, five)
                    > twoPairs(cartGamer1, cartGamer2, one, two, three, four, five))
                retValue = threeOfAKind(cartGamer1, cartGamer2, one, two, three, four, five);
            else
                retValue = twoPairs(cartGamer1, cartGamer2, one, two, three, four, five);
        }
        return retValue;
    }

    @Override
    public int fourOfAKind(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five) {
        Cart[] cartsTable = {one, two, three, four, five};
        int counter ;
        int pairCounter;
        int buff = 1;
        int retValue = 0;

        if (cartGamer1.getValueNum() == cartGamer2.getValueNum()) {
            buff = 2;
        }
        counter = 0;
        pairCounter = buff;
        while (counter != cartsTable.length) {
            if(cartGamer1.getValueNum() == cartsTable[counter].getValueNum())
                pairCounter++;
            counter++;
        }
        if(pairCounter == 4) // Поменяли значение с метода "пара" тут
            retValue = cartGamer1.getValueNum();
        // Сравнили первую карту с колодой, дальше сравниваем вторую:

        counter = 0;
        pairCounter = buff;
        while (counter != cartsTable.length) {
            if(cartGamer1.getValueNum() == cartsTable[counter].getValueNum())
                pairCounter++;
            counter++;
        }
        if(pairCounter == 4) // Поменяли значение с метода "пара" тут
            retValue = cartGamer1.getValueNum();

        return retValue;
    }

    @Override
    public int straightFlush(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five) {
        Cart[] cartsTable = {one, two, three, four, five};
        int counter ;
        int straightCounter;
        int straightValue;
        String suit;
        int higtestCart = cartGamer1.getValueNum();

        counter = 0;
        straightCounter = 1;
        straightValue = cartGamer1.getValueNum();
        suit = cartGamer1.getSuit();
        while(counter != cartsTable.length) {
            if(cartsTable[counter].getValueNum() == (straightValue + 1) &&
                    suit == cartsTable[counter].getSuit()) {
                straightCounter++;
                straightValue++;
                higtestCart = cartsTable[counter].getValueNum();
                counter = -1;
            } else
            if(cartGamer2.getValueNum() == (straightValue + 1)) {
                    straightCounter++;
                    straightValue++;
                    higtestCart = cartGamer2.getValueNum();
                    counter = -1;
            }
            counter++;
        }

        if(cartGamer1.getValueNum() == (cartGamer2.getValueNum() - 1)
                && cartGamer2.getSuit() == suit){
            straightCounter++;
            counter = 0;
            straightValue = cartGamer2.getValueNum();

            while(counter != cartsTable.length) {
                if(cartsTable[counter].getValueNum() == (straightValue - 1) &&
                        suit == cartsTable[counter].getSuit()) {
                    straightCounter++;
                    straightValue--;
                    counter = -1;
                } else
                if(cartGamer2.getValueNum() == (straightValue - 1)) {
                    straightCounter++;
                    straightValue--;
                    counter = -1;
                }
                counter++;
            }
        } else {
            counter = 0;
            straightValue = cartGamer1.getValueNum();
            while (counter != cartsTable.length) {
                if (cartsTable[counter].getValueNum() == (straightValue - 1) &&
                        suit == cartsTable[counter].getSuit()) {
                    straightCounter++;
                    straightValue--;
                    counter = -1;
                } else
                if(cartGamer2.getValueNum() == (straightValue - 1)) {
                    straightCounter++;
                    straightValue--;
                    counter = -1;
                }
                counter++;
            }

        }

        if(straightCounter >= 5)
            return higtestCart;

        counter = 0;
        straightCounter = 1;
        straightValue = cartGamer2.getValueNum();
        higtestCart = cartGamer2.getValueNum();
        suit = cartGamer2.getSuit();
        while(counter != cartsTable.length) {
            if(cartsTable[counter].getValueNum() == (straightValue + 1) &&
                    suit == cartsTable[counter].getSuit()) {
                straightCounter++;
                straightValue++;
                higtestCart = cartsTable[counter].getValueNum();
                counter = -1;
            } else
            if(cartGamer1.getValueNum() == (straightValue + 1)) {
                straightCounter++;
                straightValue++;
                higtestCart = cartGamer1.getValueNum();
                counter = -1;
            }
            counter++;
        }

        if(cartGamer2.getValueNum() == (cartGamer2.getValueNum() - 1)
                || cartGamer1.getSuit() == suit){
            straightCounter++;
            counter = 0;
            straightValue = cartGamer1.getValueNum();

            while(counter != cartsTable.length) {
                if(cartsTable[counter].getValueNum() == (straightValue - 1) &&
                        suit == cartsTable[counter].getSuit()) {
                    straightCounter++;
                    straightValue--;
                    counter = -1;
                } else
                if(cartGamer1.getValueNum() == (straightValue - 1)) {
                    straightCounter++;
                    straightValue--;
                    counter = -1;
                }
                counter++;
            }
        } else {
            counter = 0;
            straightValue = cartGamer2.getValueNum();
            while (counter != cartsTable.length) {
                if (cartsTable[counter].getValueNum() == (straightValue - 1) &&
                        suit == cartsTable[counter].getSuit()) {
                    straightCounter++;
                    straightValue--;
                    counter = -1;
                } else
                if(cartGamer1.getValueNum() == (straightValue - 1)) {
                    straightCounter++;
                    straightValue--;
                    counter = -1;
                }
                counter++;
            }
        }

        if(straightCounter >= 5)
            return higtestCart;
        else
            return 0;
    }

    @Override
    public int royalFlush(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five) {
        if(straightFlush(cartGamer1, cartGamer2, one, two, three, four, five) == 14)
            return straightFlush(cartGamer1, cartGamer2, one, two, three, four, five);
        else
            return 0;
    }
}
