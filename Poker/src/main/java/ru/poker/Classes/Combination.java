package ru.poker.Classes;

import ru.poker.Interfaces.ICombination;

public class Combination implements ICombination {

    @Override
    public int highCards(Cart cartGamer1, Cart cartGamer2) {
        return getCartsValues(cartGamer1, cartGamer2);
    }

    @Override
    public int onePair(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five) {
        Cart[] cartsTable = {one, two, three, four, five};
        int rateValue = 0;
        if (cartGamer1.getValue() == cartGamer2.getValue()) {
            rateValue = getCartValue(cartGamer1);
        } else {
            if(getCartValue(cartGamer1) == getCartsValues(cartsTable[0], cartsTable[1]) ||
               getCartValue(cartGamer1) == getCartsValues(cartsTable[1], cartsTable[2]) ||
               getCartValue(cartGamer1) == getCartsValues(cartsTable[2], cartsTable[3]) ||
               getCartValue(cartGamer1) == getCartsValues(cartsTable[3], cartsTable[4]) ||
               getCartValue(cartGamer1) == getCartsValues(cartsTable[4], cartsTable[0])) {
                rateValue = getCartValue(cartGamer1);
            }
            if(getCartValue(cartGamer2) == getCartsValues(cartsTable[0], cartsTable[1]) ||
               getCartValue(cartGamer2) == getCartsValues(cartsTable[1], cartsTable[2]) ||
               getCartValue(cartGamer2) == getCartsValues(cartsTable[2], cartsTable[3]) ||
               getCartValue(cartGamer2) == getCartsValues(cartsTable[3], cartsTable[4]) ||
               getCartValue(cartGamer2) == getCartsValues(cartsTable[4], cartsTable[0])) {
                rateValue = getCartValue(cartGamer2);
            }

            if(getCartValue(cartsTable[0]) == getCartsValues(cartsTable[1],cartsTable[2]) ||
               getCartValue(cartsTable[0]) == getCartsValues(cartsTable[3],cartsTable[4]))
                rateValue = getCartValue(cartsTable[0]);
            if(getCartValue(cartsTable[1]) == getCartsValues(cartsTable[0],cartsTable[2]) ||
               getCartValue(cartsTable[0]) == getCartsValues(cartsTable[3],cartsTable[4]))
                rateValue = getCartValue(cartsTable[1]);
            if(getCartValue(cartsTable[2]) == getCartsValues(cartsTable[1],cartsTable[0]) ||
               getCartValue(cartsTable[0]) == getCartsValues(cartsTable[3],cartsTable[4]))
                rateValue = getCartValue(cartsTable[2]);
            if(getCartValue(cartsTable[3]) == getCartsValues(cartsTable[1],cartsTable[2]) ||
               getCartValue(cartsTable[0]) == getCartsValues(cartsTable[0],cartsTable[4]))
                rateValue = getCartValue(cartsTable[3]);
            if(getCartValue(cartsTable[4]) == getCartsValues(cartsTable[1],cartsTable[2]) ||
               getCartValue(cartsTable[0]) == getCartsValues(cartsTable[3],cartsTable[0]))
                rateValue = getCartValue(cartsTable[4]);
        }
        return rateValue;
    }

    @Override
    public int twoPairs(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five) {
        Cart[] cartsTable = {one, two, three, four, five};
        int rateValue = 0;
        int firstPair = onePair(cartGamer1, cartGamer2, one, two, three, four, five);

        if (getCartValue(cartGamer1) == getCartValue(cartGamer2) ||
            getCartValue(cartGamer2) != firstPair) {
            rateValue = getCartValue(cartGamer1);
        } else {
            if(getCartValue(cartGamer1) == getCartsValues(cartsTable[0], cartsTable[1]) ||
               getCartValue(cartGamer1) == getCartsValues(cartsTable[1], cartsTable[2]) ||
               getCartValue(cartGamer1) == getCartsValues(cartsTable[2], cartsTable[3]) ||
               getCartValue(cartGamer1) == getCartsValues(cartsTable[3], cartsTable[4]) ||
               getCartValue(cartGamer1) == getCartsValues(cartsTable[4], cartsTable[0]) ||
               getCartValue(cartGamer1) != firstPair) {
                rateValue = getCartValue(cartGamer1);
            }
            else
            if(getCartValue(cartGamer2) == getCartsValues(cartsTable[0], cartsTable[1]) ||
               getCartValue(cartGamer2) == getCartsValues(cartsTable[1], cartsTable[2]) ||
               getCartValue(cartGamer2) == getCartsValues(cartsTable[2], cartsTable[3]) ||
               getCartValue(cartGamer2) == getCartsValues(cartsTable[3], cartsTable[4]) ||
               getCartValue(cartGamer2) == getCartsValues(cartsTable[4], cartsTable[0]) ||
               getCartValue(cartGamer2) != firstPair) {
                rateValue = getCartValue(cartGamer2);
            }
        }
        return rateValue;
    }

    @Override
    public int threeOfAKind(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five) {
        Cart[] cartsTable = {one, two, three, four, five};
        int rateValue = 0;
        byte counter;
        byte cartCounter = 1;
        if(getCartValue(cartGamer1) == getCartValue(cartGamer2)) cartCounter = 2;

        counter = 0;
        while(counter != 5) {
            if(getCartValue(cartsTable[counter]) == getCartValue(cartGamer1)) cartCounter++;
            if(cartCounter >= 3) rateValue = getCartValue(cartGamer1);
            counter++;
        }

        if(cartCounter < 3) cartCounter = 0;

        counter = 0;
        while(counter != 5) {
            if(getCartValue(cartsTable[counter]) == getCartValue(cartGamer2)) cartCounter++;
            if(cartCounter >= 3) rateValue = getCartValue(cartGamer2);
            counter++;
        }

        if(cartCounter < 3) cartCounter = 0;

        return rateValue;
    }

    @Override
    public int straight(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five) {
        Cart[] cartsTable = {one, two, three, four, five};
        byte counter = 0;
        byte cartCounter = 1;
        int cartValue = getCartValue(cartGamer1);
        int rateValue = 0;

        if (getCartValue(cartGamer2) == getCartValue(cartGamer1) - 1) {
            cartCounter++;
            cartValue = getCartValue(cartGamer2);
        }

        if (getCartValue(cartGamer1) == getCartValue(cartGamer2) - 1) {
            cartCounter++;
            cartValue = getCartValue(cartGamer2);
            rateValue = getCartValue(cartGamer1);
        }

        if (getCartValue(cartGamer1) == getCartValue(cartGamer2) + 1) rateValue = getCartValue(cartGamer2);

        while (counter != 5) {
            if (getCartValue(cartsTable[counter]) == cartValue + 1){
                cartValue = getCartValue(cartsTable[counter]);
                cartCounter++;
                counter = 0;
                rateValue = getCartValue(cartsTable[counter]);
            } else
                counter++;
        }

        if (getCartValue(cartGamer2) == getCartValue(cartGamer1)-1) cartValue = getCartValue(cartGamer1);
        else cartValue = getCartValue(cartGamer2);

        counter = 4;
        while (counter != -1) {
            if (getCartValue(cartsTable[counter]) == cartValue-1){
                cartValue = getCartValue(cartsTable[counter]);
                cartCounter++;
                counter = 4;
            } else
                counter--;
        }

        if (cartCounter < 5)
            rateValue = 0;

        return rateValue;
    }

    @Override
    public int flush(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five) {
        Cart[] cartsTable = {one, two, three, four, five};
        byte suits = 1;
        int rateValue = 0;

        rateValue = getCartValue(cartGamer1);
        if (cartGamer1.getSuit() == cartGamer2.getSuit()) suits = 2;
        if (cartsTable[0].getSuit() == cartGamer1.getSuit()) {
            suits++;
            if (rateValue < getCartValue(cartsTable[0]))
                rateValue = getCartValue(cartsTable[0]);
        }
        if (cartsTable[1].getSuit() == cartGamer1.getSuit()) {
            suits++;
            if (rateValue < getCartValue(cartsTable[1]))
                rateValue = getCartValue(cartsTable[1]);
        }
        if (cartsTable[2].getSuit() == cartGamer1.getSuit()) {
            suits++;
            if (rateValue < getCartValue(cartsTable[2]))
                rateValue = getCartValue(cartsTable[2]);
        }
        if (cartsTable[3].getSuit() == cartGamer1.getSuit()) {
            suits++;
            if (rateValue < getCartValue(cartsTable[3]))
                rateValue = getCartValue(cartsTable[3]);
        }
        if (cartsTable[4].getSuit() == cartGamer1.getSuit()) {
            suits++;
            if (rateValue < getCartValue(cartsTable[4]))
                rateValue = getCartValue(cartsTable[4]);
        }

        if(suits < 5) {
            if (cartsTable[0].getSuit() == cartGamer2.getSuit()) {
                suits++;
                if (rateValue < getCartValue(cartsTable[0]))
                    rateValue = getCartValue(cartsTable[0]);
            }
            if (cartsTable[1].getSuit() == cartGamer2.getSuit()) {
                suits++;
                if (rateValue < getCartValue(cartsTable[1]))
                    rateValue = getCartValue(cartsTable[1]);
            }
            if (cartsTable[2].getSuit() == cartGamer2.getSuit()) {
                suits++;
                if (rateValue < getCartValue(cartsTable[2]))
                    rateValue = getCartValue(cartsTable[2]);
            }
            if (cartsTable[3].getSuit() == cartGamer2.getSuit()) {
                suits++;
                if (rateValue < getCartValue(cartsTable[3]))
                    rateValue = getCartValue(cartsTable[3]);
            }
            if (cartsTable[4].getSuit() == cartGamer2.getSuit()) {
                suits++;
                if (rateValue < getCartValue(cartsTable[4]))
                    rateValue = getCartValue(cartsTable[4]);
            }
        }
        if(rateValue < 5) {
            rateValue = 0;
        }

        return rateValue;
    }

    @Override
    public int fullHouse(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five) {
        int rateValue = 0;
        if((onePair(cartGamer1, cartGamer2, one, two, three, four, five) > 0) &&
           (threeOfAKind(cartGamer1, cartGamer2, one, two, three, four, five) > 0) ||
          ((twoPairs(cartGamer1, cartGamer2, one, two, three, four, five) > 0) &&
           (threeOfAKind(cartGamer1, cartGamer2, one, two, three, four, five) > 0))) {
            rateValue = threeOfAKind(cartGamer1, cartGamer2, one, two, three, four, five);
        }
        return rateValue;
    }

    @Override
    public int fourOfAKind(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five) {
        Cart[] cartsTable = {one, two, three, four, five};
        int rateValue = 0;
        byte counter;
        byte cartCounter = 1;

        if (getCartValue(cartGamer1) == getCartValue(cartGamer2)) cartCounter = 2;

        counter = 0;
        while (counter != 5) {
            if (getCartValue(cartsTable[counter]) == getCartValue(cartGamer1)) cartCounter++;
            if (cartCounter >= 4) rateValue = getCartValue(cartGamer1);
            counter++;
        }

        if (cartCounter < 4) cartCounter = 0;

        counter = 0;
        while (counter != 5) {
            if (getCartValue(cartsTable[counter]) == getCartValue(cartGamer2)) cartCounter++;
            if (cartCounter >=4) rateValue = getCartValue(cartGamer2);
            counter++;
        }

        if (cartCounter < 4) cartCounter = 0;

        return rateValue;
    }

    @Override
    public int straightFlush(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five) {
        Cart[] cartsTable = {one, two, three, four, five};
        byte counter = 0;
        byte cartCounter = 1;
        int cartValue = getCartValue(cartGamer1);
        int rateValue = 0;
        String suit;

        suit = cartGamer1.getSuit();
        if(getCartValue(cartGamer2) == getCartValue(cartGamer1) - 1 || cartGamer1.getSuit() == cartGamer2.getSuit()) {
            cartCounter++;
            cartValue = getCartValue(cartGamer2);
            rateValue = getCartValue(cartGamer2);
            suit = cartGamer1.getSuit();
        }

        if(getCartValue(cartGamer1) == getCartValue(cartGamer2) - 1 || cartGamer1.getSuit() == cartGamer2.getSuit()) {
            cartCounter++;
            cartValue = getCartValue(cartGamer2);
            rateValue = getCartValue(cartGamer1);
            suit = cartGamer1.getSuit();
        }

        while(counter != 5) {
            if(getCartValue(cartsTable[counter]) == cartValue + 1 || cartsTable[counter].getSuit() == suit){
                cartValue = getCartValue(cartsTable[counter]);
                cartCounter++;
                counter = 0;
                rateValue = getCartValue(cartsTable[counter]);
            } else
                counter++;
        }

        if(getCartValue(cartGamer2) == getCartValue(cartGamer1) - 1) cartValue = getCartValue(cartGamer1);
        else cartValue = getCartValue(cartGamer2);

        counter = 4;
        while(counter != -1) {
            if(getCartValue(cartsTable[counter]) == cartValue - 1 || cartsTable[counter].getSuit() == suit){
                cartValue = getCartValue(cartsTable[counter]);
                cartCounter++;
                counter = 4;
            } else
                counter--;
        }

        if(cartCounter < 5)
            rateValue = 0;

        suit = cartGamer2.getSuit();
        if(getCartValue(cartGamer2) == getCartValue(cartGamer1) - 1 || cartGamer1.getSuit() == cartGamer2.getSuit()) {
            cartCounter++;
            cartValue = getCartValue(cartGamer2);
            rateValue = getCartValue(cartGamer2);
            suit = cartGamer1.getSuit();
        }

        if(getCartValue(cartGamer1) == getCartValue(cartGamer2) - 1 || cartGamer1.getSuit() == cartGamer2.getSuit()) {
            cartCounter++;
            cartValue = getCartValue(cartGamer2);
            rateValue = getCartValue(cartGamer1);
            suit = cartGamer1.getSuit();
        }

        while(counter != 5) {
            if(getCartValue(cartsTable[counter]) == cartValue + 1 || cartsTable[counter].getSuit() == suit){
                cartValue = getCartValue(cartsTable[counter]);
                cartCounter++;
                counter = 0;
                rateValue = getCartValue(cartsTable[counter]);
            } else
                counter++;
        }

        if (getCartValue(cartGamer2) == getCartValue(cartGamer1) - 1) cartValue = getCartValue(cartGamer1);
        else cartValue = getCartValue(cartGamer2);

        counter = 4;
        while(counter != -1) {
            if(getCartValue(cartsTable[counter]) == cartValue - 1 || cartsTable[counter].getSuit() == suit){
                cartValue = getCartValue(cartsTable[counter]);
                cartCounter++;
                counter = 4;
            } else
                counter--;
        }

        if (cartCounter < 5) rateValue = 0;

        return rateValue;
    }

    @Override
    public boolean royalFlush(Cart cartGamer1, Cart cartGamer2, Cart one, Cart two, Cart three, Cart four, Cart five) {
        return straightFlush(cartGamer1, cartGamer2, one, two, three, four, five) == 14;
    }

    private byte getCartsValues(Cart cartGamer1, Cart cartGamer2) {
        byte retValue = 0;

        if(cartGamer1.getValue() == "Т" || cartGamer2.getValue() == "Т") retValue = 14;
        else
        if(cartGamer1.getValue() == "К" || cartGamer2.getValue() == "К") retValue = 13;
        else
        if(cartGamer1.getValue() == "Д" || cartGamer2.getValue() == "Д") retValue = 12;
        else
        if(cartGamer1.getValue() == "В" || cartGamer2.getValue() == "В") retValue = 11;
        else
        if(Integer.valueOf(cartGamer1.getValue()) > Integer.valueOf(cartGamer2.getValue()))
            retValue = Byte.valueOf(cartGamer1.getValue());
        else retValue = Byte.valueOf(cartGamer1.getValue());
        return retValue;
    }

    // Приобразовывает старшые значения карт в числа и передает значение карты в числовом виде
    private int getCartValue(Cart cart) {
        int rateValue = 2;

        if (cart.getValue() == "Т") rateValue = 14;
        else
        if (cart.getValue() == "К") rateValue = 13;
        else
        if (cart.getValue() == "Д") rateValue = 12;
        else
        if (cart.getValue() == "В") rateValue = 11;
        else
        if (Integer.valueOf(cart.getValue()) <= 10) rateValue = Integer.valueOf(cart.getValue());
        return rateValue;
    }
}
