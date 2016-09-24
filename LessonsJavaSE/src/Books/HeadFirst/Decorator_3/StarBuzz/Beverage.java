package Books.HeadFirst.Decorator_3.StarBuzz;

public abstract class Beverage {
    public String description = "Unknown Beverage";
//    public static final int TALL = 1;
//    public static final int GRANGE = 2;
//    public static final int VENTI = 3;

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
