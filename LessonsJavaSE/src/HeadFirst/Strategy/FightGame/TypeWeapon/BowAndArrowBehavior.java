package HeadFirst.Strategy.FightGame.TypeWeapon;

public class BowAndArrowBehavior implements WeaponBehavior {
    @Override
    public void useWeapon() {
        System.out.println("У меня есть лук и стрелы!");
    }
}
