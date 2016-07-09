package HeadFirst.Strategy.FightGame.Heroes;

import HeadFirst.Strategy.FightGame.Character;
import HeadFirst.Strategy.FightGame.TypeWeapon.AxeBehavior;

public class Troll extends Character {

    public Troll() {
        weaponBehavior = new AxeBehavior();
    }

    @Override
    public void fight() {
        weaponBehavior.useWeapon();
    }
}
