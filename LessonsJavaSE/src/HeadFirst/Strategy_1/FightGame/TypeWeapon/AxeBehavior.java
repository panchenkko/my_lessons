package HeadFirst.Strategy_1.FightGame.TypeWeapon;

public class AxeBehavior implements WeaponBehavior {
    @Override
    public void useWeapon() {
        System.out.println("У меня есть топор!");
    }
}
