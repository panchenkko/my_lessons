package PRACTICAL_WORKS;

import java.util.Scanner;

/*
Создаем таблицу 5 на 2. Пять рядков, два столба. После ввода чисел, во втором столбе отображаются их экстремумы
и нахождение максимального, минимального экстремума.
*/

public class PW1 {

    public static double[][] array;

    public static double max = 0, min = 0;

    public static void input(int a, int b) {
        Scanner sc = new Scanner(System.in);
        array = new double[a][b];

        for (int i = 0; i < a; i++) {
            array[i][0] = sc.nextInt();
            array[i][1] = array[i][0] * array[i][0] / 2;
        }
        sc.close();
    }

    public static void show(int a, int b) {
        input(a, b);
        System.out.println("\nВывод введенных чисел: ");
        max = array[0][1];
        min = array[0][1];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++)
                System.out.print(array[i][j] + " ");
            System.out.println();

            if (array[i][1] > max) {
                max = array[i][1];
            }

            if (array[i][1] < min) {
                min = array[i][1];
            }
        }
        System.out.println("\nМаксимальный экстремум: " + max);
        System.out.println("Минимальный экстремум: " + min);
    }

    public static void main(String[] args) {
        System.out.print("Введите 5 чисел: ");
        show(5, 2);
    }
}