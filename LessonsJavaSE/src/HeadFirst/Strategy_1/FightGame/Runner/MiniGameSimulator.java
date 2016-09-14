package HeadFirst.Strategy_1.FightGame.Runner;

import HeadFirst.Strategy_1.FightGame.Character;
import HeadFirst.Strategy_1.FightGame.Heroes.Knight;
import HeadFirst.Strategy_1.FightGame.Heroes.Queen;
import HeadFirst.Strategy_1.FightGame.TypeWeapon.AxeBehavior;
import HeadFirst.Strategy_1.FightGame.TypeWeapon.KnifeBehavior;

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
