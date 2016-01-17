
public class Calculator {

    int result;

    public int getResult() {
        return result;
    }

    public void cleanResult() {
        this.result = 0;
    }

    public void addition(int... params) {
        for (Integer param : params)
            this.result += param;
    }

    public void subtraction(int... params) {
        for (Integer param : params)
            this.result -= param;
    }

    public void multiplication(int... params) {
        for (Integer param : params)
            this.result *= param;
    }

    public void division(int... params) {
        for (Integer param : params)
            this.result /= param;
    }
}
