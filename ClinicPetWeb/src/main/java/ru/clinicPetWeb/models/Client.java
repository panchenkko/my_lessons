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

    @Override
    public String toString() {
        return "Client{" + id + ". " +
                "name='" + name + '\'' +
                ", pet=" + pet +
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
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        if (name != null ? !name.equals(client.name) : client.name != null) return false;
        return !(pet != null ? !pet.equals(client.pet) : client.pet != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (pet != null ? pet.hashCode() : 0);
        return result;
    }
}
