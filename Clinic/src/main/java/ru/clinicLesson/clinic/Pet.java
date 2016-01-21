package ru.clinicLesson.clinic;

public class Pet {

    private String name;
    private int age;

    public Pet(String petName, int petAge) {
        this.name = petName;
        this.age = petAge;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
