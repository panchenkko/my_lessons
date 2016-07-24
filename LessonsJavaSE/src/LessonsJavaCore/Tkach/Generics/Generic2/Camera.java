package LessonsJavaCore.Tkach.Generics.Generic2;

public class Camera extends Product {

    private double pixel;

    public Camera() {
    }

    public Camera(double pixel) {
        this.pixel = pixel;
    }

    public Camera(String name, double price, double pixel) {
        super(name, price);
        this.pixel = pixel;
    }

    public double getPixel() {
        return pixel;
    }
}
