package ru.clinicWeb_jsp_jstl.models;

public class Client {

    private int id;
    private String name;
    private Pet pet;

    public Client(int id, String name, Pet pet) {
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

    public void setName(String name) {
        this.name = name;
    }

    public Pet getPet() {
        return pet;
    }
}
