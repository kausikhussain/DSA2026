package TCS_NQT;

import java.util.*;

public class ProductOfThreeLargestSorted {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        if(!sc.hasNextInt()) return;

        int n = sc.nextInt();
        if(n < 3) return; // Need at least 3 elements

        int[] arr = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        long ans = 1L * arr[n-1] * arr[n-2] * arr[n-3];

        System.out.println(ans);
        sc.close();
    }
}
