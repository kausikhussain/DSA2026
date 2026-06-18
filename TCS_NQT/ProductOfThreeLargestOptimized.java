package TCS_NQT;

import java.util.*;

public class ProductOfThreeLargestOptimized {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        if(!sc.hasNextInt()) return;

        int n = sc.nextInt();
        if(n < 3) return; // Need at least 3 elements

        int[] arr = new int[n];

        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        int third = Integer.MIN_VALUE;

        for(int i = 0; i < n; i++) {

            arr[i] = sc.nextInt();

            if(arr[i] > first){
                third = second;
                second = first;
                first = arr[i];
            }
            else if(arr[i] > second){
                third = second;
                second = arr[i];
            }
            else if(arr[i] > third){
                third = arr[i];
            }
        }

        long product = 1L * first * second * third;

        System.out.println(product);
        sc.close();
    }
}
