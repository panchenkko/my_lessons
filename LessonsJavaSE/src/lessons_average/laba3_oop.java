package lessons_average;

class Workers {
    private String name_person;
    private String name_position;
    private int position;

    public Workers(String person, String name, int position) {
        this.name_person = person;
        this.name_position = name;
        this.position = position;
    }

    public String getName_person() {
        return name_person;
    }

    public String getName_position() {
        return name_position;
    }

    public int getPosition() {
        return position;
    }
}

class Director extends Workers {

    public Director(String person, String name, int position) {
        super(person, name, position);
    }
}

class Manager extends Workers {

    public Manager(String person, String name, int position) {
        super(person, name, position);
    }
}

class Personnel extends Workers {

    public Personnel(String person, String name, int position) {
        super(person, name, position);
    }
}

public class laba3_oop {
    public static void main(String[] args) {

        Director director = new Director("Директоров Директор Директорович", "Директор", 1);
        Manager manager = new Manager("Менеджеренко Менеджер Менеджерович", "Менеджер", 2);
        Personnel personnel = new Personnel ("Персонян Персонал Персоналович", "Персонал", 3);

        System.out.println("Действующие должности: \n"
                + director.getName_position() + "\n"
                + manager.getName_position() + "\n"
                + personnel.getName_position() + "\n");

        System.out.println("Иерархия должностей: \n");

        System.out.println(
                "Должность: " + director.getName_position() + ". \n"
                        + "ФИО: " + director.getName_person() + ". \n"
                        + "В учреждении этот человек №" + director.getPosition() + ". \n"
        );

        System.out.println(
                "Должность: " + manager.getName_position() + ". \n"
                        + "ФИО: " + manager.getName_person() + ". \n"
                        + "В учреждении этот человек №" + manager.getPosition() + ". \n"
        );

        System.out.println(
                "Должность: " + personnel.getName_position() + ". \n"
                        + "ФИО: " + personnel.getName_person() + ". \n"
                        + "В учреждении этот человек №" + personnel.getPosition() + ". "
        );
    }
}
