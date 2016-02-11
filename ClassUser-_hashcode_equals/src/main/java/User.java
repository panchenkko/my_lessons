import Interfaces.UserInterface;

//User of online-shop
public class User implements UserInterface {
    int id; // id in system
    String name;// user's name
    UserType type;// type of user (ADMIN/BUYER/SELLER)

    User( int id, String name, UserType type){
        this.id = id;
        this.name = name;
        this.type = type;
    }

    User( int id, String name, String type ){
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
