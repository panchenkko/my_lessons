package ru.poker.Classes;

import ru.poker.Interfaces.ICombination;

public class Combination implements ICombination {

    @Override
    public int highCards(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable) {
        return getCartsValues(cartGamer1, cartGamer2);
    }

    @Override
    public int onePair(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable) {
        int retValue = 0;
        if (cartGamer1.getValue() == cartGamer2.getValue()) {
            retValue = getCartValue(cartGamer1);
        } else {
            if(
                            (getCartValue(cartGamer1) == getCartsValues(cartsTable[0], cartsTable[1]) ) ||
                            (getCartValue(cartGamer1) == getCartsValues(cartsTable[1], cartsTable[2]) ) ||
                            (getCartValue(cartGamer1) == getCartsValues(cartsTable[2], cartsTable[3]) ) ||
                            (getCartValue(cartGamer1) == getCartsValues(cartsTable[3], cartsTable[4]) ) ||
                            (getCartValue(cartGamer1) == getCartsValues(cartsTable[4], cartsTable[0]) ) ) {
                retValue = getCartValue(cartGamer1);
            }
            if(
                            (getCartValue(cartGamer2) == getCartsValues(cartsTable[0], cartsTable[1]) ) ||
                            (getCartValue(cartGamer2) == getCartsValues(cartsTable[1], cartsTable[2]) ) ||
                            (getCartValue(cartGamer2) == getCartsValues(cartsTable[2], cartsTable[3]) ) ||
                            (getCartValue(cartGamer2) == getCartsValues(cartsTable[3], cartsTable[4]) ) ||
                            (getCartValue(cartGamer2) == getCartsValues(cartsTable[4], cartsTable[0]) ) ) {
                retValue = getCartValue(cartGamer2);
            }

            if(( getCartValue(cartsTable[0]) == getCartsValues(cartsTable[1],cartsTable[2])) ||
                    getCartValue(cartsTable[0]) == getCartsValues(cartsTable[3],cartsTable[4]))
                retValue = getCartValue(cartsTable[0]);
            if(( getCartValue(cartsTable[1]) == getCartsValues(cartsTable[0],cartsTable[2])) ||
                    getCartValue(cartsTable[0]) == getCartsValues(cartsTable[3],cartsTable[4]))
                retValue = getCartValue(cartsTable[1]);
            if(( getCartValue(cartsTable[2]) == getCartsValues(cartsTable[1],cartsTable[0])) ||
                    getCartValue(cartsTable[0]) == getCartsValues(cartsTable[3],cartsTable[4]))
                retValue = getCartValue(cartsTable[2]);
            if(( getCartValue(cartsTable[3]) == getCartsValues(cartsTable[1],cartsTable[2])) ||
                    getCartValue(cartsTable[0]) == getCartsValues(cartsTable[0],cartsTable[4]))
                retValue = getCartValue(cartsTable[3]);
            if(( getCartValue(cartsTable[4]) == getCartsValues(cartsTable[1],cartsTable[2])) ||
                    getCartValue(cartsTable[0]) == getCartsValues(cartsTable[3],cartsTable[0]))
                retValue = getCartValue(cartsTable[4]);
        }
        return retValue;
    }

    @Override
    public int twoPairs(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable) {
        return 0;
    }

    @Override
    public int threeOfAKind(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable) {
        return 0;
    }

    @Override
    public int straight(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable) {
        return 0;
    }

    @Override
    public int flush(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable) {
        byte suits = 1;
        int retValue = 0;

        retValue = getCartValue(cartGamer1);
        if (cartGamer1.getSuit() == cartGamer2.getSuit())
            suits = 2;

        if (cartsTable[0].getSuit() == cartGamer1.getSuit()) {
            suits++;
            if (retValue < getCartValue(cartsTable[0]))
                retValue = getCartValue(cartsTable[0]);
            }
        if (cartsTable[1].getSuit() == cartGamer1.getSuit()) {
            suits++;
            if (retValue < getCartValue(cartsTable[1]))
                retValue = getCartValue(cartsTable[1]);
        }
        if (cartsTable[2].getSuit() == cartGamer1.getSuit()) {
            suits++;
            if (retValue < getCartValue(cartsTable[2]))
                retValue = getCartValue(cartsTable[2]);
        }
        if (cartsTable[3].getSuit() == cartGamer1.getSuit()) {
            suits++;
            if (retValue < getCartValue(cartsTable[3]))
                retValue = getCartValue(cartsTable[3]);
        }
        if (cartsTable[4].getSuit() == cartGamer1.getSuit()) {
            suits++;
            if (retValue < getCartValue(cartsTable[4]))
                retValue = getCartValue(cartsTable[4]);
        }

        if(suits < 5) {
            if (cartsTable[0].getSuit() == cartGamer2.getSuit()) {
                suits++;
                if (retValue < getCartValue(cartsTable[0]))
                    retValue = getCartValue(cartsTable[0]);
            }
            if (cartsTable[1].getSuit() == cartGamer2.getSuit()) {
                suits++;
                if (retValue < getCartValue(cartsTable[1]))
                    retValue = getCartValue(cartsTable[1]);
            }
            if (cartsTable[2].getSuit() == cartGamer2.getSuit()) {
                suits++;
                if (retValue < getCartValue(cartsTable[2]))
                    retValue = getCartValue(cartsTable[2]);
            }
            if (cartsTable[3].getSuit() == cartGamer2.getSuit()) {
                suits++;
                if (retValue < getCartValue(cartsTable[3]))
                    retValue = getCartValue(cartsTable[3]);
            }
            if (cartsTable[4].getSuit() == cartGamer2.getSuit()) {
                suits++;
                if (retValue < getCartValue(cartsTable[4]))
                    retValue = getCartValue(cartsTable[4]);
            }
        }
        if(retValue < 5) {
            retValue = 0;
        }

        return retValue;
    }

    @Override
    public int fullHouse(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable) {
        return 0;
    }

    @Override
    public int fourOfAKind(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable) {
        return 0;
    }

    @Override
    public int straightFlush(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable) {
        return 0;
    }

    @Override
    public int royalFlush(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable) {
        return 0;
    }

    private byte getCartsValues(Cart cartGamer1, Cart cartGamer2) {

        byte retValue = 0;
        if(Integer.valueOf(cartGamer1.getValue()) < 10 || Integer.valueOf(cartGamer2.getValue()) < 10) {
            if(Integer.valueOf(cartGamer1.getValue()) > Integer.valueOf(cartGamer2.getValue())) {
                retValue = Byte.valueOf(cartGamer1.getValue());
            } else {
                retValue = Byte.valueOf(cartGamer1.getValue());
            }
        }

        if(cartGamer1.getValue() == "���" || cartGamer2.getValue() == "���") {
            retValue = 14;
        }
        if(cartGamer1.getValue() == "������" || cartGamer2.getValue() == "������") {
            retValue = 13;
        }
        if(cartGamer1.getValue() == "����" || cartGamer2.getValue() == "����") {
            retValue = 12;
        }
        if(cartGamer1.getValue() == "�����" || cartGamer2.getValue() == "�����") {
            retValue = 11;
        }
        return retValue;
    }

    private byte getCartValue(Cart cartGamer1) {

        byte retValue = 2;
        if (Integer.valueOf(cartGamer1.getValue()) < 10) {
            retValue = Byte.valueOf(cartGamer1.getValue());
        }

        if (cartGamer1.getValue() == "���") {
            retValue = 14;
        }
        if (cartGamer1.getValue() == "������") {
            retValue = 13;
        }
        if (cartGamer1.getValue() == "����") {
            retValue = 12;
        }
        if (cartGamer1.getValue() == "�����") {
            retValue = 11;
        }
        return retValue;
    }
}
