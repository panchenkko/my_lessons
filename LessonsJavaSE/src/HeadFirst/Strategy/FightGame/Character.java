package HeadFirst.Strategy.FightGame;

import HeadFirst.Strategy.FightGame.TypeWeapon.WeaponBehavior;

public abstract class Character {
    public WeaponBehavior weaponBehavior;

    public void setWeaponBehavior(WeaponBehavior weaponBehavior) {
        this.weaponBehavior = weaponBehavior;
    }

    public abstract void fight();
}
