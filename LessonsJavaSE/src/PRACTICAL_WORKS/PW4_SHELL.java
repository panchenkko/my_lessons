package PRACTICAL_WORKS;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PW4_SHELL {

    public static void inputSize() {
        Scanner sc = new Scanner(System.in);
        int size = 0;

        try {
            System.out.print("Введите количество чисел: ");
            size = sc.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("\nВведите целочисленный тип!");
            System.out.println();
            inputSize();
        }

        int[] a = new int[size];
        inputNumbers(size, a);
        sort(size, a);
        show(size, a);
    }

    public static void inputNumbers(int size, int[] a) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ввод чисел: ");
        try {
            for (int i = 0; i < size; i++) {
                a[i] = sc.nextInt();
            }
        } catch (InputMismatchException e) {
            System.err.println("\nВы ввели неверный символ! Попробуйте заново!");
            System.out.println();
            inputNumbers(size, a);
        }
    }

    public static void sort(int size, int[] a) {
        int step = size / 2;
        while (step > 0) {
            for (int i = 0; i < (size - step); i++) {
                passage(step, a, i);
            }
            // уменьшаем шаг
            step = step / 2;
        }
    }

    public static void passage(int step, int[] a, int i) {
        if(i >= 0 && a[i] > a[i + step]){
            int temp = a[i];
            a[i] = a[i + step];
            a[i + step] = temp;
            i--;
            passage(step, a, i);
        }
    }

    public static void show(int size, int[] a) {
        for (int i = 0; i < size; i++) {
            System.out.print(a[i] + " ");
        }
    }

    /*************            *************/
    public static void main(String[] args) {
        inputSize();
    }
}

/******* СОРТИРОВКА ШЕЛЛА БЕЗ ИСПОЛЬЗОВАНИЯ МЕТОДОВ И РЕКУРСИИ *******/
//    Scanner sc = new Scanner(System.in);
//
//        System.out.print("Введите количество чисел: ");
//        int size = sc.nextInt();
//
//        int[] a = new int[size];
//
//        for(int i = 0; i < size; i++) {
//            a[i] = sc.nextInt();
//        }
//
//        int step = size / 2;
//        while (step > 0) {
//            for (int i = 0; i < (size - step); i++) {
//                int j = i;
//                // Пока не пришли к началу массива и пока рассматриваемый элемент больше,
//                // чем элемент находящийся на расстоянии шага
//                while (j >= 0 && a[j] > a[j + step]) {
//                    // меняем их местами
//                    int temp = a[j];
//                    a[j] = a[j + step];
//                    a[j + step] = temp;
//                    j--;
//                }
//            }
//            // уменьшаем шаг
//            step = step / 2;
//        }
//
//        for (int i = 0; i < size; i++) {
//            System.out.print(a[i] + " ");
//        }

