package ru.clinicPetWeb.models;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class Client extends Base {

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    // Обязательно нужен пустой конструктор для Hibernate.
    // Он изначально создает нулевой объект, а после уже начинает дергать геттеры и сеттеры
    public Client() {
    }

    public Client(final int id, final String name, final Pet pet) {
        this.id = id;
        this.name = name;
        this.pet = pet;
    }

    public String getName() {
        return this.name;
    }

    public Pet getPet() {
        return this.pet;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
