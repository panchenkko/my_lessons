package ru.clinicLesson.clinic;

import java.util.Scanner;

public class ClinicRunner {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            final Clinic clinic = new Clinic();
            System.out.println("\033[30m<--- Внесите данные о клиентах клиники и их питомцах --->\033[0m");
            clinic.addClient(sc);
            clinic.menu(sc);
        }
    }
}
