package com.company.PRACTICAL_WORKS2.Weather;

public class Runner {
    public static void main(String[] args) {
        Snow snow = new Snow(-10, 768, 1, 100);
        System.out.println("Снег: \n" + snow);
        System.out.println();
        Rain rain = new Rain(0, 780, 110, "Ливень");
        System.out.println("Дождь: \n" + rain);
        System.out.println();
        Blizzard blizzard = new Blizzard(-20, 800, 5, 150, "Штормовой");
        System.out.println("Снежная буря: \n" + blizzard);
    }
}
