package ru.clinicPetWeb.models;

import javax.persistence.*;

@Entity
@Table(name = "pet")
public class Pet extends Base {

    @Column(name = "type")
    private String type;

    @Column(name = "petname")
    private String name;

    @Column(name = "sex")
    private String sex;

    private String age;

    public Pet() {
    }

    public Pet(final int id, final String type, final String name, final String sex, final String age) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String getSex() {
        return sex;
    }

    public String getAge() {
        return this.age;
    }

    public void setType(String petType) {
        this.type = petType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String petSex) {
        this.sex = petSex;
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
