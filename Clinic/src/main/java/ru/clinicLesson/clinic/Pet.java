package ru.clinicLesson.clinic;

public class Pet {

    private String name;
    private String age;

    public Pet(String petName, String petAge) {
        this.name = petName;
        this.age = petAge;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setAge(String newAge) {
        this.age = newAge;
    }
}
