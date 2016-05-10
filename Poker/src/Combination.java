
public class Combination implements ICombination {

    private Table table;

    @Override
    public boolean highCards(Cart cartGamer1, Cart cartGamer2, Cart[] cartsTable) {
        cartsTable = table.getCart();
        return false;
    }

    @Override
    public boolean onePair(Cart[] cartsGamer, Cart[] cartsTable) {
        return false;
    }

    @Override
    public boolean twoPairs(Cart[] cartsGamer, Cart[] cartsTable) {
        return false;
    }

    @Override
    public boolean threeOfAKind(Cart[] cartsGamer, Cart[] cartsTable) {
        return false;
    }

    @Override
    public boolean straight(Cart[] cartsGamer, Cart[] cartsTable) {
        return false;
    }

    @Override
    public boolean flush(Cart[] cartsGamer, Cart[] cartsTable) {
        return false;
    }

    @Override
    public boolean fullHouse(Cart[] cartsGamer, Cart[] cartsTable) {
        return false;
    }

    @Override
    public boolean fourOfAKind(Cart[] cartsGamer, Cart[] cartsTable) {
        return false;
    }

    @Override
    public boolean straightFlush(Cart[] cartsGamer, Cart[] cartsTable) {
        return false;
    }

    @Override
    public boolean royalFlush(Cart[] cartsGamer, Cart[] cartsTable) {
        return false;
    }
}
