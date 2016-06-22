package Equals_Hashcode;

public class Equals_Hashcode {
    public static void main(String[] args) {
        User user1 = new User("Mike");
        User user2 = new User("Mike");
        User user3 = new User("Alex");
        System.out.println(user1.equals(user2));
        System.out.println(user1.equals(user3));
    }
}
