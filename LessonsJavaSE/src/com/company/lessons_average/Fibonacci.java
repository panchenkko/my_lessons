package com.company.lessons_average;

public class Fibonacci {

    public static void calculation(int range) {
        long a = 0, b = 1, c = 1;

        for (int i = 1; i <= range;) {
            System.out.println(i++ + ". " + a);
            a = b + c;
            System.out.println(i++ + ". " + b);
            b = a + c;
            System.out.println(i++ + ". " + c);
            c = a + b;
        }
    }

    public static void main(String[] args) {
        Fibonacci.calculation(11);
    }
}
