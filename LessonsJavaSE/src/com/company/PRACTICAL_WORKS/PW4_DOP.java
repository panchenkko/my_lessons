package com.company.PRACTICAL_WORKS;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
1. Вводим количество чисел
2. Вводим числа
3. С помощью рекурсии суммируем все числа в отдельную переменную
4. Присваиваем переменной = сумму всех чисел деленную на количество чисел
5. Выводим среднее арифметическое число
*/

public class PW4_DOP {

    public static double[] array;
    public static int p = 0;
    public static double j = 0, k = 0;

    public static void inputSum() {
        Scanner sc = new Scanner(System.in);
        int sum = 0;

        try {
            System.out.print("Введите количество чисел: ");
            sum = sc.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("\nВведите целочисленный тип!");
            System.out.println();
            inputSum();
        }
        input(sum);
    }

    public static void input(int sum) {
        Scanner sc = new Scanner(System.in);
        array = new double[sum];
        for (int i = 0; i < sum; i++) {
            array[i] = sc.nextDouble();
        }
        passage(sum);

        j = k / sum;
        System.out.println(j);
    }

    public static void passage(int sum) {
        k += array[p];
        if(p != sum - 1) {
            p++;
            passage(sum);
        }
    }

    public static void main(String[] args) {
        inputSum();
    }
}
