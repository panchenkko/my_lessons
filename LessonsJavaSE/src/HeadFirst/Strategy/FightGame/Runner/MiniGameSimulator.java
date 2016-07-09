package HeadFirst.Strategy.FightGame.Runner;

import HeadFirst.Strategy.FightGame.Character;
import HeadFirst.Strategy.FightGame.Heroes.Knight;
import HeadFirst.Strategy.FightGame.Heroes.Queen;
import HeadFirst.Strategy.FightGame.TypeWeapon.AxeBehavior;
import HeadFirst.Strategy.FightGame.TypeWeapon.KnifeBehavior;

public class MiniGameSimulator {
    public static void main(String[] args) {
        Character knight = new Knight();
        knight.fight();
        knight.setWeaponBehavior(new AxeBehavior());
        knight.fight();

        Character queen = new Queen();
        queen.fight();
        queen.setWeaponBehavior(new KnifeBehavior());
        queen.fight();
    }
}
