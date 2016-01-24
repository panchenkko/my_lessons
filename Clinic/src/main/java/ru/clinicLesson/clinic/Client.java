package ru.clinicLesson.clinic;

public class Client {

    private String name;
    private Pet pet;

    public Client(String name, Pet pet) {
        this.name = name;
        this.pet = pet;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public Pet getPet() {
        return pet;
    }
}
