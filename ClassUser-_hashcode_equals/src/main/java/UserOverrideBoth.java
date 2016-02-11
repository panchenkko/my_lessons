import Interfaces.UserInterface;

public class UserOverrideBoth implements UserInterface {

    int id;
    String name;
    UserType type;
    int hashId;
    private static int objectCounter = 0;

    UserOverrideBoth(int id, String name, UserType type) {
            this.id = id;
            this.name = name;
            this.type = type;
            hashId = objectCounter;
            objectCounter++;
    }

    UserOverrideBoth(int id, String name, String type) {
            this.id = id;
            this.name = name;
            this.type = UserType.valueOf(type);
            hashId = objectCounter;
            objectCounter++;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }

    @Override
    public int hashCode() {
        return this.id + this.name.hashCode()
                + this.type.hashCode() + hashId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!this.getClass().equals(obj.getClass()))
            return false;

        UserOverrideBoth us = (UserOverrideBoth) obj;
        if (this == us)
            return true;
        else if (this.id == us.id &&
                this.type.toString().equals(us.type.toString()) &&
                this.name.equals(us.name)
            //  && this.hashCode() == us.hashCode()
                )
            return true;
        return false;
    }

    protected enum UserType{
        ADMIN("Admin"),
        BUYER("Buyer"),
        SELLER("Seller");

        private String shortName;

        private UserType(String shortName){
            this.shortName = shortName;
        }

        public String getType(){
            return shortName;
        }
    }
}
