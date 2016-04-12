package ru.clinicWeb_jsp_jstl.models;

public class Pet {

    private final String petType;
    private final String name;
    private final String age;

    public Pet(final String petType, final String name, final String age) {
        this.petType = petType;
        this.name = name;
        this.age = age;
    }

    public String getPetType() {
        return this.petType;
    }

    public String getName() {
        return this.name;
    }

    public String getAge() {
        return this.age;
    }
}
