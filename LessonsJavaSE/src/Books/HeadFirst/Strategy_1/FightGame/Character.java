package Books.HeadFirst.Strategy_1.FightGame;

import Books.HeadFirst.Strategy_1.FightGame.TypeWeapon.WeaponBehavior;

public abstract class Character {
    public WeaponBehavior weaponBehavior;

    public void setWeaponBehavior(WeaponBehavior weaponBehavior) {
        this.weaponBehavior = weaponBehavior;
    }

    public abstract void fight();
}
