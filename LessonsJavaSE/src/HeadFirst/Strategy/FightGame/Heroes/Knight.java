package HeadFirst.Strategy.FightGame.Heroes;

import HeadFirst.Strategy.FightGame.Character;
import HeadFirst.Strategy.FightGame.TypeWeapon.SwordBehavior;

public class Knight extends Character {

    public Knight() {
        weaponBehavior = new SwordBehavior();
    }

    @Override
    public void fight() {
        weaponBehavior.useWeapon();
    }
}
