package Equals_Hashcode;

/**
 * - Методы equals() м hashcode() обязательно переопределяются когда объект используется в коллекции
 * - Если не переопределить метод equals() - все создаваемые объекты при сравнении будут выдавать false
 */
public class User {

    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        // Если объекты изначально равны возвращаем сразу же true
        if (this == o) return true;
        // Если переданный объект не равен объекту User возвращаем false
        if (!(o instanceof User)) return false;

        User user = (User) o;

        // Сравниваем и возвращаем результат boolean
        return name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
