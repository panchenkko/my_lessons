package PRACTICAL_WORKS;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
Розробити бібліотеку функцій для роботи з матрицями цілих чисел
(ініцілазізація, виведення на екран, додаванная рядка до рядка,
віднімання рядка від рядка, множення або ділення матриці на число)
*/

public class PW5 {

    public static double[][] matrix = new double[3][3];
    public static boolean green = false, yellow = false, blue = false, darkGreen = false;

    public static void bigDecimalLine(double res, int two, int i) {
        BigDecimal x = new BigDecimal(res);
        x = x.setScale(1, BigDecimal.ROUND_HALF_UP); /* 1 - количество знаков после запятой */
        matrix[two - 1][i] = x.doubleValue();
    }

    public static void bigDecimalColumn(double res, int two, int i) {
        BigDecimal x = new BigDecimal(res);
        x = x.setScale(1, BigDecimal.ROUND_HALF_UP); /* 1 - количество знаков после запятой */
        matrix[i][two - 1] = x.doubleValue();
    }

    public static void inputMatrix() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введите матрицу: ");
        try {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    matrix[i][j] = sc.nextInt();
                }
            }
        } catch (InputMismatchException e) {
            System.err.println("\nВы ввели неверный символ! Попробуйте заново!");
            System.out.println();
            inputMatrix();
        }
        show(darkGreen, green, blue, yellow);
    }

    public static void show(boolean darkGreen, boolean green, boolean blue, boolean yellow) {
        if (darkGreen)
            System.out.print("\033[36m");
        else if (green)
            System.out.print("\033[32m");
        else if (blue)
            System.out.print("\033[34m");
        else if (yellow)
            System.out.print("\033[33m");

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.print("\033[0m");
        menu();
    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        String answer;

        System.out.println(
                "\nВозможные действия над матрицей: "
                        + "\n     \033[36m1. Добавление \033[37m(строка к строке)\033[0m"
                        + "\n     \033[32m2. Вычитание \033[37m(строка от строки)\033[0m"
                        + "\n     \033[34m3. Умножение \033[37m(на число какой либо строки)\033[0m"
                        + "\n     \033[33m4. Деление \033[37m(на число какой либо строки)\033[0m");
        System.out.print("\n\033[30mОтвет: \033[0m");
        answer = sc.next();

        switch(answer) {
            case "1":
                addition(true);
                break;
            case "2":
                subtraction(true);
                break;
            case "3":
                multiplication(true);
                break;
            case "4":
                division(true);
                break;
            default:
                System.err.println("Вы не выбрали действие! Выберите что-нибудь: ");
                menu();
                break;
        }
    }

    public static void addition(boolean darkGreen) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\033[1;36m1. Строки" + "\n2. Столбики \033[0m");
        String three = sc.next();
        int one, two;
        switch (three) {
            case "1":
                System.out.print("\n\033[1;36mВведите что хотите прибавить (через пробел): \033[0m");
                one = sc.nextInt();
                two = sc.nextInt();

                for (int i = 0; i < matrix.length; i++) {
                    double res = matrix[one - 1][i] + matrix[two - 1][i];
                    bigDecimalLine(res, two, i);
                }
                break;
            case "2":
                System.out.print("\n\033[1;36mВведите что хотите прибавить (через пробел): \033[0m");
                one = sc.nextInt();
                two = sc.nextInt();

                for (int i = 0; i < matrix.length; i++) {
                    double res = matrix[i][one - 1] + matrix[i][two - 1];
                    bigDecimalColumn(res, two, i);
                }
                break;
        }
        show(darkGreen, green, blue, yellow);
        menu();
    }

    public static void subtraction(boolean green) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\033[1;32m1. Строки" + "\n2. Столбики \033[0m");
        String three = sc.next();
        int one, two;
        switch (three) {
            case "1":
                System.out.print("\n\033[1;32mВведите что хотите отнять (через пробел): \033[0m");
                one = sc.nextInt();
                two = sc.nextInt();

                for (int i = 0; i < matrix.length; i++) {
                    double res = matrix[one - 1][i] - matrix[two - 1][i];
                    bigDecimalLine(res, two, i);
                }
                break;
            case "2":
                System.out.print("\n\033[1;32mВведите что хотите отнять (через пробел): \033[0m");
                one = sc.nextInt();
                two = sc.nextInt();

                for (int i = 0; i < matrix.length; i++) {
                    double res = matrix[i][one - 1] - matrix[i][two - 1];
                    bigDecimalColumn(res, two, i);
                }
                break;
        }
        show(darkGreen, green, blue, yellow);
        menu();
    }

    public static void multiplication(boolean blue) {
        Scanner sc = new Scanner(System.in);
        System.out.print("\n\033[1;34mВведите какую строку хотите умножить: \033[0m");
        int one = sc.nextInt();
        System.out.print("\033[1;34mНа сколько: \033[0m");
        int two = sc.nextInt();

        for (int i = 0; i < matrix.length; i++) {
            double res = matrix[one - 1][i] * two;
            bigDecimalLine(res, two, i);
        }
        show(darkGreen, green, blue, yellow);
        menu();
    }

    public static void division(boolean yellow) {
        Scanner sc = new Scanner(System.in);
        System.out.print("\n\033[1;33mВведите какую строку хотите поделить: \033[0m");
        int one = sc.nextInt();
        System.out.print("\033[1;33mНа сколько: \033[0m");
        int two = sc.nextInt();

        for (int i = 0; i < matrix.length; i++) {
            double res = matrix[one - 1][i] / two;
            bigDecimalLine(res, two, i);
        }
        show(darkGreen, green, blue, yellow);
        menu();
    }

    /*************            *************/
    public static void main(String[] args) {
        inputMatrix();
    }
}