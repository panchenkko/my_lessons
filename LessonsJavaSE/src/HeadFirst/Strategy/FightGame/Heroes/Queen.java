package HeadFirst.Strategy.FightGame.Heroes;

import HeadFirst.Strategy.FightGame.Character;
import HeadFirst.Strategy.FightGame.TypeWeapon.BowAndArrowBehavior;

public class Queen extends Character {

    public Queen() {
        weaponBehavior = new BowAndArrowBehavior();
    }

    @Override
    public void fight() {
        weaponBehavior.useWeapon();
    }
}
