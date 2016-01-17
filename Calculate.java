import java.util.Scanner;

public class Calculate {

    public static void input() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите число, операцию, число: ");
        int a = sc.nextInt();
        String b = sc.next();
        int c = sc.nextInt();
        sum(a, c, b);
    }



    public static void sum(int a, int c, String b) {
        switch (b) {
            case "+": System.out.println("Ответ: " + (a + c));
                break;

            case "-": System.out.println("Ответ: " + (a - c));
                break;

            case "/": System.out.println("Ответ: " + (a / c));
                break;

            case "*": System.out.println("Ответ: " + (a * c));
                break;

            default: System.out.println("Такой операции нет!");
        }
    }

    public static void main(String[] args) {
        input();
    }
}