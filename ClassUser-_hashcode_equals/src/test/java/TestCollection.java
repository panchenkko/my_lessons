import Interfaces.UserInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class TestCollection {


        UserInterface u1, u2, u3, u1Copy;
    ArrayList<UserInterface> arrayList = new ArrayList<UserInterface>();
    HashSet<UserInterface> hashSet = new HashSet<UserInterface>();
    HashMap<UserInterface, Integer> hashMap = new HashMap<UserInterface, Integer>();


    public void setupUser() {
        u1 = new User(0, "u1", "ADMIN");
        u2 = new User(1, "u2", "ADMIN");
        u3 = new User(10, "u3", "BUYER");
        u1Copy = new User(0, "u1", "ADMIN");
    }

    public void setupUserOverrideEquals() {
        arrayList = new ArrayList<UserInterface>();
        hashSet = new HashSet<UserInterface>();
        hashMap = new HashMap<UserInterface, Integer>();

        u1 = new UserOverrideEquals(0, "u1", "ADMIN");
        u2 = new UserOverrideEquals(1, "u2", "ADMIN");
        u3 = new UserOverrideEquals(10, "u3", "BUYER");
        u1Copy = new UserOverrideEquals(0, "u1", "ADMIN");
    }

    public void setupUserOverrideHashCode() {
        arrayList = new ArrayList<UserInterface>();
        hashSet = new HashSet<UserInterface>();
        hashMap = new HashMap<UserInterface, Integer>();

        u1 = new UserOverrideHashCode(0, "u1", "ADMIN");
        u2 = new UserOverrideHashCode(1, "u2", "ADMIN");
        u3 = new UserOverrideHashCode(10, "u3", "BUYER");
        u1Copy = new UserOverrideHashCode(0, "u1", "ADMIN");
    }

    public void setupUserOverrideBoth() {
        arrayList = new ArrayList<UserInterface>();
        hashSet = new HashSet<UserInterface>();
        hashMap = new HashMap<UserInterface, Integer>();

        u1 = new UserOverrideBoth(0, "u1", "ADMIN");
        u2 = new UserOverrideBoth(1, "u2", "ADMIN");
        u3 = new UserOverrideBoth(10, "u3", "BUYER");
        u1Copy = new UserOverrideBoth(0, "u1", "ADMIN");
    }

    public void addToHashMap() {
        String result = "  HashMap contains all variables  : ";

        hashMap.put(u1,1);
        hashMap.put(u2,1);
        hashMap.put(u3,1);
        hashMap.put(u1Copy,2);

        if(hashMap.containsKey(u1) &&
                hashMap.containsKey(u2) &&
                hashMap.containsKey(u3) &&
                hashMap.containsKey(u1Copy))
            System.out.println(result + "True" + " (size = "+ hashMap.size() + "/4)");
        else
            System.out.println(result + "False" + " (size = "+ hashMap.size() + "/4)");
    }

    public void addToHashSet() {
        String result = "  HashSet contains all variables  : ";

        hashSet.add(u1);
        hashSet.add(u2);
        hashSet.add(u3);
        hashSet.add(u1Copy);

        if(hashSet.contains(u1) &&
                hashSet.contains(u2) &&
                hashSet.contains(u3) &&
                hashSet.contains(u1Copy))
            System.out.println(result + "True" + " (size = "+ hashSet.size() + "/4)");
        else
            System.out.println(result + "False" + " (size = "+ hashSet.size() + "/4)");
    }

    public void addToArrayList() {
        String result = "ArrayList contains all variables  : ";

        arrayList.add(u1);
        arrayList.add(u2);
        arrayList.add(u3);
        arrayList.add(u1Copy);

        if(arrayList.contains(u1) &&
                arrayList.contains(u2) &&
                arrayList.contains(u3) &&
                arrayList.contains(u1Copy))
            System.out.println(result + "True" + " (size = "+ arrayList.size() + "/4)");
        else
            System.out.println(result + "False" + " (size = "+ arrayList.size() + "/4)");
    }

    public void u1equalsU1Copy(){
        String result = "u1.equals(u1Copy) : ";
        if(u1.equals(u1Copy))
            System.out.println(result + "True");
        else
            System.out.println(result + "False");
    }

    public void u1HashcodeU2() {
        String result = "HashCode  u1 (" + u1.hashCode() + ") = u1Copy (" + u1Copy.hashCode()+") : ";
        if(u1.hashCode() == u1Copy.hashCode())
            System.out.println(result + "True");
        else
            System.out.println(result + "False");
    }

    public void testCollection() {
        addToArrayList();
        addToHashSet();
        addToHashMap();
        System.out.println("u1(" + u1 + ")     u1Copy(" + u1Copy + ")");
        u1HashcodeU2();
        u1equalsU1Copy();
        System.out.println("----------------------------------------------------");
    }

    public static void main(String[] args) {
        TestCollection test = new TestCollection();

        System.out.println("----- 1.Test without override (equals by links of objects):");
        test.setupUser();
        test.testCollection();

        System.out.println("----- 2.Test with override Equals (checking fields of objects):");
        test.setupUserOverrideEquals();
        test.testCollection();

        System.out.println("----- 3.Test with override HashCode (equals by links of objects):");
        test.setupUserOverrideHashCode();
        test.testCollection();

        System.out.println("----- 4.Test with override Equals and HashCode (checking fields of objects):");
        test.setupUserOverrideBoth();
        test.testCollection();



    }
}
