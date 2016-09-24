package Books.HeadFirst.Strategy_1.FightGame.Heroes;

import Books.HeadFirst.Strategy_1.FightGame.Character;
import Books.HeadFirst.Strategy_1.FightGame.TypeWeapon.BowAndArrowBehavior;

public class Queen extends Character {

    public Queen() {
        weaponBehavior = new BowAndArrowBehavior();
    }

    @Override
    public void fight() {
        weaponBehavior.useWeapon();
    }
}
