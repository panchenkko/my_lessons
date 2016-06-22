package com.company.lessons_average;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class diapazon_num_sortViborom {

    // Метод на рандомные числа
    public static int number(int x, int y){
        if (x > y) {
            int c = (int) (Math.random() * (x - y + 1)) + y;
            return c;
        } else {
            int c = (int) (Math.random() * (y - x + 1)) + x;
            return c;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Введите начало диапазона чисел: ");
        int num1 = sc.nextInt();

        System.out.println("Введите конец диапазона чисел: ");
        int num2 = sc.nextInt();

        sc.close();
        int[] arr = new int[10];

        for(int i = 0; i <= 5; i++) { // Цикл на 5 массивов

            for (int j = 0; j < arr.length; j++) {
                arr[j] = number(num1, num2);
            }
            // Сортировка методом выбора
            for (int w = 0; w < 10; w++) {
                int minValueIndex = w;

                for (int q = w + 1; q < 10; q++) {
                    if (arr[q] < arr[minValueIndex]) {
                        minValueIndex = q;
                    }
                }
                int temp = arr[w];
                arr[w] = arr[minValueIndex];
                arr[minValueIndex] = temp;
            }
            // Вывод на консоль
            System.out.println(Arrays.toString(arr) + " ");
        }
    }
}