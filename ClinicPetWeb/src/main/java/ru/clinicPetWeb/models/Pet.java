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

    @Override
    public String toString() {
        return "Pet{" + id + ". " +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    /**
     *
     * Методы equals() и hashcode() необходимы в тестах, для сравнения экземпляров класса.
     *
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet)) return false;

        Pet pet = (Pet) o;

        if (type != null ? !type.equals(pet.type) : pet.type != null) return false;
        if (name != null ? !name.equals(pet.name) : pet.name != null) return false;
        if (sex != null ? !sex.equals(pet.sex) : pet.sex != null) return false;
        if (age != null ? !age.equals(pet.age) : pet.age != null) return false;
        return !(client != null ? !client.equals(pet.client) : pet.client != null);

    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        return result;
    }
}
