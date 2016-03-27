package lessons_average;

import java.util.Scanner;

public class VivodParnihChisel {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введите количество чисел первого массива: ");
        int one = sc.nextInt();

        System.out.print("Введите количество чисел второго массива: ");
        int two = sc.nextInt();

        int[] x = new int[one];
        int[] y = new int[two];

        System.out.print("\nВведите числа первого массива: ");
        for (int i = 0; i < one; i++) {
            x[i] = sc.nextInt();
        }
        System.out.print("Введите числа второго массива: ");
        for (int i = 0; i < two; i++) {
            y[i] = sc.nextInt();
        }

        int[] c = new int[one * two];
        int k = 0;
        for (int i = 0; i < x.length; i++)
            for (int j = 0; j < y.length; j++)
                if(x[i] == y[j]) {
                    c[k] = x[i];
                    k++;
                }

        System.out.print("\nПовторяемые числа: ");
        for (int i = 0; i < k; i++) {
            System.out.print(c[i] + " ");
        }
    }
}
