package Generics.Generic3;

public class Camera extends Product<Camera> {

    private double pixel;

    public Camera() {
    }

    @Override
    int subCompare(Camera p) {
        return Double.compare(this.pixel, p.getPixel());
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
