
public interface ICombination {
    boolean highCards(Cart[] cartsGamer, Cart[] cartsTable);
    boolean onePair(Cart[] cartsGamer, Cart[] cartsTable);
    boolean twoPairs(Cart[] cartsGamer, Cart[] cartsTable);
    boolean threeOfAKind(Cart[] cartsGamer, Cart[] cartsTable);
    boolean straight(Cart[] cartsGamer, Cart[] cartsTable);
    boolean flush(Cart[] cartsGamer, Cart[] cartsTable);
    boolean fullHouse(Cart[] cartsGamer, Cart[] cartsTable);
    boolean fourOfAKind(Cart[] cartsGamer, Cart[] cartsTable);
    boolean straightFlush(Cart[] cartsGamer, Cart[] cartsTable);
    boolean royalFlush(Cart[] cartsGamer, Cart[] cartsTable);
}
