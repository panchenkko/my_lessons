package Books.HeadFirst.Strategy_1.FightGame.TypeWeapon;

public class SwordBehavior implements WeaponBehavior {
    @Override
    public void useWeapon() {
        System.out.println("У меня есть меч!");
    }
}
