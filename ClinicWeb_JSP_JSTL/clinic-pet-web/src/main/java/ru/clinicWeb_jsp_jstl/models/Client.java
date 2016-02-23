package ru.clinicWeb_jsp_jstl.models;

public class Client {

    private final int id;
    private final String name;
    private final Pet pet;

    public Client(final int id, final String name, final Pet pet) {
        this.id = id;
        this.name = name;
        this.pet = pet;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Pet getPet() {
        return pet;
    }
}
