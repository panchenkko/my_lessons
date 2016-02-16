package ru.clinicLesson.clinic._not_used_;

import ru.clinicLesson.clinic.Pet;

public class Dog implements Pets {

    private final Pet dog;

    public Dog(Pet dog) {
        this.dog = dog;
    }

    @Override
    public String getName() {
        return this.dog.getName();
    }

    @Override
    public String getAge() {
        return this.dog.getAge();
    }

    @Override
    public void setName(String name) {
        this.dog.setName(name);
    }

    @Override
    public void setAge(String age) {
        this.dog.setAge(age);
    }

    @Override
    public String getPetType() {
        return "dog";
    }
}
