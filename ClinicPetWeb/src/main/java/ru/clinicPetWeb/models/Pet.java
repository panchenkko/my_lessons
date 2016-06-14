package ru.clinicPetWeb.models;

import javax.persistence.*;

@Entity
@Table(name = "pet")
public class Pet extends Base {

    @Column(name = "type")
    private String petType;

    @Column(name = "petname")
    private String name;

    @Column(name = "sex")
    private String petSex;

    private String age;

    public Pet() {
    }

    public Pet(final int id, final String petType, final String name, final String petSex, final String age) {
        this.id = id;
        this.petType = petType;
        this.name = name;
        this.petSex = petSex;
        this.age = age;
    }

    public String getPetType() {
        return this.petType;
    }

    public String getName() {
        return this.name;
    }

    public String getPetSex() {
        return petSex;
    }

    public String getAge() {
        return this.age;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPetSex(String petSex) {
        this.petSex = petSex;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @OneToOne(mappedBy = "pet")
    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
