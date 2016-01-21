package main.java.ru.lesson.lessons;

public class Calculator {

    private double result;

    public double getResult() {
        return result;
    }

    public void cleanResult() {
        this.result = 0;
    }

    public void addition(double... params) {
        for (double param : params)
            this.result += param;
    }

    public void subtraction(double... params) {
        for (double param : params)
            this.result -= param;
    }

    public void multiplication(double... params) {
        for (double param : params)
            this.result *= param;
    }

    public void division(double... params) {
        for (double param : params)
            this.result /= param;
    }

    public void pow(double a, double b) {
        this.result = Math.pow(a, b);
    }
}
