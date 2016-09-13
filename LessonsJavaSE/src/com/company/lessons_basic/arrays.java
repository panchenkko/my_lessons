package com.company.lessons_basic;

public class arrays {
    public static void main(String[] args) {
        String[] names = {"Mike", "John", "Alex"};
        int[][] codes = {{35356, 78957, 24532}, {676735, 123456789, 1231287, 123456}, {9876234, 123456789}};
        int number = 123456789;

        root: for(int i = 0; i < names.length; i++){ // root - это пометка, для вывода только первого победителя (в случае, если их может быть несколько)
            String name = names[i];
            int[] userCodes = codes[i];

            for(int j = 0; j < userCodes.length; j++) {
                if (userCodes[j] == number) {
                    System.out.println(name);
                    break root; // Остановка пометки, для прекращения вывода остальных победителей.
                }
            }
        }
    }
}
