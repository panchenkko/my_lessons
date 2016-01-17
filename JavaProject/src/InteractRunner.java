import java.util.Objects;
import java.util.Scanner;

public class InteractRunner {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Calculator calc = new Calculator();
            String exit = "n";
            while (!Objects.equals(exit, "3")) {
                System.out.println("Введите через пробел простое выражение: ");
                if (calc.getResult() != 0)
                    System.out.print(calc.getResult() + " ");
                int a = sc.nextInt();
                String b = sc.next();
                int c = sc.nextInt();

                switch (b) {
                    case "+":
                        calc.addition(a, c);
                        break;

                    case "-":
                        calc.subtraction(a, c);
                        break;

                    case "/":
                        calc.division(a, c);
                        break;

                    case "*":
                        calc.multiplication(a, c);
                        break;
                    default:
                        System.out.println("Такой операции нет!");
                }

                System.out.println("Ответ: " + calc.getResult());

                System.out.print(
                        "\n1. Продолжить вычислять " +
                                "\n2. Обнулнить и продолжить вычислять" +
                                "\n3. Выйти");
                System.out.println();
                exit = sc.next();
                if (Objects.equals(exit, "2"))
                    calc.cleanResult();
            }
        }
    }
}
