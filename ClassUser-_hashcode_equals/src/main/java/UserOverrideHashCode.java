import Interfaces.UserInterface;

public class UserOverrideHashCode implements UserInterface {

    int id;
    String name;
    UserType type;
    int hashId;
    private static int objectCounter = 0;

    UserOverrideHashCode(int id, String name, UserType type) {
            this.id = id;
            this.name = name;
            this.type = type;
            hashId = objectCounter;
            objectCounter++;
    }

    UserOverrideHashCode(int id, String name, String type) {
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
        return this.id + this.name.hashCode() +
                this.type.hashCode() + hashId;
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
