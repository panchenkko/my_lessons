package ru.lesson.lessons;

public class Calculator {

    private double result;

    // Добавление
    public void addition(double a, double b) {
        this.result = a + b;
    }

    // Вычитание
    public void subtraction(double a, double b) {
        this.result = a - b;
    }

    // Умножение
    public void multiplication(double... args) {
        this.result = 1;
        for (double param : args)
            this.result *= param;
    }

    /**
     * Деление
     * @param args входящие аргументы
     * @throws UserException Если аргументов нет, выкидываем исключение
     */
    public void division(double... args) throws UserException {
        if (args.length > 0) {
            for (double params : args) {
                if (params == 0) {
                    throw new IllegalArgumentException("Деление на 0 невозможно! Попробуйте ещё раз.");
                }
                this.result = args[0] / args[1];
            }
        } else {
            throw new UserException("Вы должны ввести аргументы!");
        }
    }

    // Возведение в степень
    public void pow(double a, double b) {
        this.result = Math.pow(a, b);
    }

    // Возврат результата
    public double getResult() {
        return this.result;
    }

    // Очистка результата
    public void cleanResult() {
        this.result = 0;
    }
}
