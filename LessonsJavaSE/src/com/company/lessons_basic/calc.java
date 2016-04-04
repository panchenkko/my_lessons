package com.company.lessons_basic;

import java.io.IOException;
import java.util.Scanner;
import java.lang.String;

public class calc {

//    public static int com.company.lessons_basic.calc(int x, int y){
//        return x * y;
//    }

//    public static int com.company.lessons_basic.calc(int x, int y, int z){
//        return x * y * z;
//    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int res = 0;
        System.out.println("Введите первоe число: ");
        int num1 = sc.nextInt();
        System.out.println("Введите выражение: ");
        String op = sc.next();
        System.out.println("Введите второе число: ");
        int num2 = sc.nextInt();
        sc.close();

        switch (op) {
            case "+":
                res = num1 + num2;
                break;
            case "-":
                res = num1 - num2;
                break;
            case "*":
                res = num1 * num2;
                break;
            case "/":
                res = num1 / num2;
                break;
        }
        System.out.println(num1 + op + num2 + "=" + res);
    }
}
