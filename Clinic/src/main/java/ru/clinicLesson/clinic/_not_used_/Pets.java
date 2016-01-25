package ru.clinicLesson.clinic._not_used_;

public interface Pets {

    String getName();

    String getAge();

    void setName(String name);

    void setAge(String age);

    default String getPetType() {
        return null;
    }
}
