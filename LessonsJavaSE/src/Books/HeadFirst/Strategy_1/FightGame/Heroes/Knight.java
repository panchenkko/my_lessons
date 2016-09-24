package Books.HeadFirst.Strategy_1.FightGame.Heroes;

import Books.HeadFirst.Strategy_1.FightGame.Character;
import Books.HeadFirst.Strategy_1.FightGame.TypeWeapon.SwordBehavior;

public class Knight extends Character {

    public Knight() {
        weaponBehavior = new SwordBehavior();
    }

    @Override
    public void fight() {
        weaponBehavior.useWeapon();
    }
}
