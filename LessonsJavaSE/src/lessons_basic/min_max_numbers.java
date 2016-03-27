package lessons_basic;

public class min_max_numbers {
    public static void main(String[] args) {
        int[] arr = {5, 10, -34, 666, 99, 2, -32, -1};
        int max = arr[0];
        int min = arr[0];

        for(int i=0; i < arr.length; i++){
            if(max < arr[i]){
                max = arr[i];
            }
            if(min > arr[i]){
                min = arr[i];
            }
        }
        System.out.println("Min: " + min);
        System.out.println("Max: " + max);
    }
}
