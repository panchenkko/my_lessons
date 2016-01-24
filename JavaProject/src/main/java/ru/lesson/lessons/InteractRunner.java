package ru.lesson.lessons;

import java.util.Objects;
import java.util.Scanner;

public class InteractRunner {

    public void doCalculation(Scanner sc, Calculator calc, boolean usePrevResult) throws UserException {

        double first;

        if (!usePrevResult) {
            System.out.print("Введите первый аргумент: ");
            first = sc.nextDouble();
        } else {
            first = calc.getResult();
        }

        System.out.print("Введите операцию: ");
        String operation = sc.next();

        System.out.print("Введите второй аргумент: ");
        double second = sc.nextDouble();

        switch (operation) {
            case "+": calc.addition(first, second);
                      break;
            case "-": calc.subtraction(first, second);
                      break;
            case "*": calc.multiplication(first, second);
                      break;
            case "/": calc.division(first, second);
                      break;
            case "pow": calc.pow(first, second);
                        break;
        }
    }

    public static void main(String[] args) throws UserException {

        boolean usePrevResult = false;

        try (Scanner sc = new Scanner(System.in)) {
            Calculator calc = new Calculator();
            InteractRunner runner = new InteractRunner();
            String exit = "нет";
            while (!exit.equals("да")) {
                if (usePrevResult)
                    System.out.println("Результат: " + calc.getResult());
                runner.doCalculation(sc, calc,usePrevResult);

                System.out.println("Результат: " + calc.getResult());
                System.out.println("Выйти: (да/нет)? ");
                exit = sc.next();

                if (Objects.equals(exit, "нет")) {
                    System.out.print("\nПродолжить операции с предыдущим результатом (да/нет)? ");
                    String answer = sc.next();
                    if (Objects.equals(answer, "да"))
                        usePrevResult = true;
                    else
                        calc.cleanResult();
                }

                System.out.println();
            }
        }
    }
}
