package Books.HeadFirst.Strategy_1.FightGame.Heroes;

import Books.HeadFirst.Strategy_1.FightGame.Character;
import Books.HeadFirst.Strategy_1.FightGame.TypeWeapon.KnifeBehavior;

public class King extends Character {

    public King() {
        weaponBehavior = new KnifeBehavior();
    }

    @Override
    public void fight() {
        weaponBehavior.useWeapon();
    }
}
