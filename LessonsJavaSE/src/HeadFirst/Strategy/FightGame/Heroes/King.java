package HeadFirst.Strategy.FightGame.Heroes;

import HeadFirst.Strategy.FightGame.Character;
import HeadFirst.Strategy.FightGame.TypeWeapon.KnifeBehavior;

public class King extends Character {

    public King() {
        weaponBehavior = new KnifeBehavior();
    }

    @Override
    public void fight() {
        weaponBehavior.useWeapon();
    }
}
