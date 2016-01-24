package ru.clinicLesson.clinic;

import java.util.Scanner;

public class ClinicRunner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final Clinic clinic = new Clinic();
        clinic.menu(sc);
        sc.close();
    }
}
