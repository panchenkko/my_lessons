package com.company.lessons_basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class zapic_v_array {
    public static void main(String[] args) throws IOException {
        int[] arr;
        System.out.println("Введите кол-во чисел: ");
        int number = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        arr = new int[number];

        for(int i = 0; i < arr.length; i++){
            arr[i] = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        }
        System.out.println(Arrays.toString(arr));
    }
}