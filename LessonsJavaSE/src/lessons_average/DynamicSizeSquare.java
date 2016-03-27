package lessons_average;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DynamicSizeSquare {

    public void inputSize() {
        Scanner sc = new Scanner(System.in);
        int size;

        try {
            System.out.print("Введите размерность квадрата: ");
            size = sc.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("\nВведите целочисленный тип!");
            System.out.println();
            inputSize();
            return;
        }
        sc.close();
        conclusion(size);
    }

    public void conclusion(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0 || i == size - 1 || j == 0 || j == size - 1)
                    System.out.print("\033[34m\033[1m1    ");
                else {
                    if (i == size / 2 && j == size / 2) {
                        // Определение четности по последнему биту.
                        // У нечетных чисел в двоичном представлении последний бит всегда 1.
                        if ((size & 1) != 0)
                            System.out.print("\033[34m\033[1m1    ");
                        else
                            System.out.print("\033[33m\033[1m0    ");
                    }
                    else
                        System.out.print("\033[33m\033[1m0    ");
                }
            }
            System.out.println();
            System.out.println();
        }
    }

    public static void main(String[] args) {
        final DynamicSizeSquare square = new DynamicSizeSquare();
        square.inputSize();
    }
}
