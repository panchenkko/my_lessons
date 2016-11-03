package Books.HeadFirst.Strategy_1.FightGame.TypeWeapon;

public class KnifeBehavior implements WeaponBehavior {
    @Override
    public void useWeapon() {
        System.out.println("У меня есть нож!");
    }
}
