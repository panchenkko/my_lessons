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

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
