package lessons_basic;

public class matrix {
    public static void main(String[] args) {
        int res;

        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9} };

        int[][] matrix2 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9} };

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix2.length; j++) {
                res = matrix[i][j] * matrix2[i][j];
                System.out.print(res + " ");
            }
            System.out.println();
        }
    }
}
