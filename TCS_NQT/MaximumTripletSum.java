package TCS_NQT;

import java.util.*;

public class MaximumTripletSum {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        if(!sc.hasNextInt()) return;

        int n = sc.nextInt();
        if(n < 3) {
            System.out.println("Array must have at least 3 elements");
            return; 
        }

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        // Traverse the array once to find the top 3 maximum numbers.
        // This takes O(N) time and O(1) space, avoiding an O(N log N) sort.
        for(int i = 0; i < n; i++) {
            int num = sc.nextInt();

            if(num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if(num > max2) {
                max3 = max2;
                max2 = num;
            } else if(num > max3) {
                max3 = num;
            }
        }

        // The maximum sum of any three numbers is simply the sum of the three largest numbers.
        // Note: Unlike the "Maximum Product" problem, negative numbers do NOT flip to positive when added. 
        // Two negatives added together just make a smaller negative.
        // Therefore, we do NOT need to track the smallest numbers for the sum.
        long maxSum = 1L * max1 + max2 + max3;

        System.out.println(maxSum);
        sc.close();
    }
}
