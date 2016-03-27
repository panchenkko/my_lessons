package lessons_basic;

public class srednee_arif {

    public static void main(String[] args) {
        double[] arr = {5, -6, 15, -30, 4.55, -1, -3.99, 60.5, -3.33};
        double sum = 0;
        double average;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        average = sum / arr.length;
        System.out.println(average);
    }
}