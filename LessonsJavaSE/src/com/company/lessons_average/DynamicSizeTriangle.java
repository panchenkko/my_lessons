package com.company.lessons_average;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DynamicSizeTriangle {

    public void inputSize() {
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
        sc.close();
        conclusion(size);
    }

    public void conclusion(int size) {
        int k = size;
        for (int i = 0; i < size; i++) {
            for (int q = 0; q <= k; q++)
                System.out.print(" ");
            for (int j = 0; j <= i; j++) {
                if (j == 0 || i == 0 || j == i || i == size - 1) {
                    System.out.print("\033[33m\033[1m1 \033[0m");
                }
                else
                    System.out.print("\033[34m0 \033[0m");
            }
            k--;
            System.out.println();
        }
    }

    public static void main(String[] args) {
        final DynamicSizeTriangle triangle = new DynamicSizeTriangle();
        triangle.inputSize();
    }
}
