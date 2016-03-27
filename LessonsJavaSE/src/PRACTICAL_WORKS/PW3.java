package PRACTICAL_WORKS;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

class ArrayLines {

    public static int s = 0, ss = 0;

    public static String[] arrayLine;

    public static int[][] arrayNum;

    public static void inputSum() {
        Scanner sc = new Scanner(System.in);
        int sum = 0;

        try {
            System.out.print("Введите количество строк: ");
            sum = sc.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("\nВведите целочисленный тип!");
            System.out.println();
            inputSum();
        }
        show(sum);
    }

    public static void input(int sum) {
        Scanner sc = new Scanner(System.in);
        arrayLine = new String[sum];
        arrayNum = new int[2][sum];

        System.out.println("Введите строки: ");
        for (int i = 0; i < sum; i++) {
            arrayLine[i] = sc.next();
            arrayNum[0][i] = i;
            arrayNum[1][i] = 0;
        }
        passageIndex(sum);
        passageArray(sum);
    }

    public static void passageIndex(int sum) {
        for (int i = 0; i < sum; i++) {
            if (Objects.equals(arrayLine[ss], arrayLine[i])) {
                if (ss < i) {
                    for (int r = 0; r <= ss; r++) {
                        if (arrayNum[0][i] != arrayNum[0][ss]) {
                            arrayNum[0][i] = ss;
                        }
                    }
                }
            }
        }
        if(ss != sum - 1) {
            ss++;
            passageIndex(sum);
        }
    }

    public static void passageArray(int sum) {
        for (int i = 0; i < sum; i++) {
            if (Objects.equals(arrayLine[s], arrayLine[i])) {
                arrayNum[1][s] += 1;
            }
        }
        if(s != sum - 1) {
            s++;
            passageArray(sum);
        }
    }

    public static void show(int sum) {
        input(sum);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < sum; j++)
                System.out.print(arrayNum[i][j] + " ");
            System.out.println();
        }
    }
}

/*
На основі масиву рядочків створити двовимірний масив чисел:
ПЕРШИЙ елемент дорівнює номеру елементу у списку,
а ДРУГИЙ - кількості повторень заданого елементу у початковому масиві.
Масив чисел відсортувати за зменьшенням кількості повторень елементів.
*/

public class PW3 {
    public static void main(String[] args) {
        ArrayLines.inputSum();
    }
}