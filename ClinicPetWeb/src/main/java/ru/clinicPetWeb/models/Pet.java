package ru.clinicPetWeb.models;

public class Pet {

    private final String petType;
    private final String name;
    private final String petSex;
    private final String age;

    public Pet(final String petType, final String name, String petSex, final String age) {
        this.petType = petType;
        this.name = name;
        this.petSex = petSex;
        this.age = age;
    }

    public String getPetType() {
        return this.petType;
    }

    public String getName() {
        return this.name;
    }

    public String getPetSex() {
        return petSex;
    }

    public String getAge() {
        return this.age;
    }
}
