package com.company.lessons_basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class sort_vuborom {
    public static void main(String[] args) throws IOException {
        // Для считывания воспользуемся классом Scanner
        // Для вывода - классом PrintWriter
        Scanner scanner = new Scanner(System.in);
        PrintWriter printWriter = new PrintWriter(System.out);

        int[] a;
        System.out.println("Введите кол-во чисел: ");
        // Считываем размер массива,
        // который необходимо отсортировать
        int size = scanner.nextInt();

        // Создаем массив размера size
        // для хранения чисел
        a = new int[size];

        for(int i = 0; i < a.length; i++){
            a[i] = scanner.nextInt();
        }

        // Считываем массив
        for (int i = 0; i < size; i++) {
            // Найдем минимальный элемент на
            // промежутке индексов [i; size];
            // изначально его индекс равен i
            int minValueIndex = i;

            // Переберем оставшиеся элементы промежутка
            for (int j = i + 1; j < size; j++) {
                // Если элемент в позиции j меньше
                // элемента в позиции minValueIndex, то
                // необходимо обновить значение индекса
                if (a[j] < a[minValueIndex]) {
                    minValueIndex = j;
                }
            }

            // Меняем текущий элемент с минимальным
            int temp = a[i];
            a[i] = a[minValueIndex];
            a[minValueIndex] = temp;
        }

        // Выводим отсортированный массив
        for (int i = 0; i < size; i++){
            printWriter.print(a[i] + " ");
        }

        // После выполнения программы необходимо закрыть
        // потоки ввода и вывода
        scanner.close();
        printWriter.close();
    }
}
