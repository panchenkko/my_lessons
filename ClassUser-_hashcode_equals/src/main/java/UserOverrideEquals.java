import Interfaces.UserInterface;

public class UserOverrideEquals implements UserInterface {

    int id;
    String name;
    UserType type;

    UserOverrideEquals(int id, String name, UserType type){
        this.id = id;
        this.name = name;
        this.type = type;
    }

    UserOverrideEquals(int id, String name, String type){
        this.id = id;
        this.name = name;
        this.type = UserType.valueOf(type);
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
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!this.getClass().equals(obj.getClass()))
            return false;

        UserOverrideEquals us = (UserOverrideEquals) obj;
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
