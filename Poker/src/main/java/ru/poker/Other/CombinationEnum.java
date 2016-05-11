package ru.poker.Other;

public enum CombinationEnum {

    HighCards(1),
    OnePair(2),
    TwoPairs(3),
    ThreeOfAKind(4),
    Straight(5),
    Flush(6),
    FullHouse(7),
    FourOfAKind(8),
    StraightFlush(9),
    RoyalFlush(10);

    private int value;

    CombinationEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
