package ru.clinicLesson.clinic;

public class Pet {

    private String petType;
    private String name;
    private String age;

    public Pet(String petType, String name, String age) {
        this.petType = petType;
        this.name = name;
        this.age = age;
    }

    public String getPetType() {
        return petType;
    }

    public String getName() {
        return this.name;
    }

    public String getAge() {
        return this.age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }
}
