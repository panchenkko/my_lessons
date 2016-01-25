package ru.clinicLesson.clinic._not_used_;

import ru.clinicLesson.clinic.Pet;

public class Cat implements Pets {

    private final Pet cat;

    public Cat(Pet cat) {
        this.cat = cat;
    }

    @Override
    public String getName() {
        return this.cat.getName();
    }

    @Override
    public String getAge() {
        return this.cat.getAge();
    }

    @Override
    public void setName(String name) {
        this.cat.setName(name);
    }

    @Override
    public void setAge(String age) {
        this.cat.setAge(age);
    }

    @Override
    public String getPetType() {
        return "cat";
    }
}
