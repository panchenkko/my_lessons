package ru.clinicLesson.clinic;

import java.util.Scanner;

/**
 * Данная программа содержит информацию о клиентах клиники домашних животных.
 * Нужно внести данные о имени клиента и его питомце.
 * После ввода данных, выпадает меню и можно варьировать введенными данными о клиентах,
 * выбрав что-нибудь из списка меню.
 */

public class ClinicRunner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            Clinic clinic = new Clinic();
            System.out.println();
            clinic.directory();
            System.out.println("\n\033[30m<--- Внесите данные о клиентах клиники и их питомцах --->\033[0m");
            clinic.addClient(sc);
            clinic.menu(sc);
        } finally {
            sc.close();
        }
    }
}
