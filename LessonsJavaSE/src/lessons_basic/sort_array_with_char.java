package lessons_basic;

import java.util.Scanner;

public class sort_array_with_char {

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String a[] = new String[3];
        System.out.println("Введите слово: ");
        a[0] = sc.nextLine();
        System.out.println("Введите слово: ");
        a[1] = sc.nextLine();
        System.out.println("Введите слово: ");
        a[2] = sc.nextLine();
        sc.close();
        for (int j=0; j<a.length; j++)
        {
            for (int i=j+1; i<a.length; i++)
            {
                if(a[i].compareTo(a[j]) < 0) {
                    String helper = a[j];
                    a[j] = a[i];
                    a[i] = helper;
                }
            }
            System.out.println(a[j]);
        }
    }
}