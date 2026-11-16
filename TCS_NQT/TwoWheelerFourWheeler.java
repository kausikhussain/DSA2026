package TCS_NQT;

import java.util.Scanner;

public class TwoWheelerFourWheeler {
    /**
     * TCS NQT Coding Question: Vehicles & Wheels Problem
     * 
     * Problem Statement:
     * An automobile company manufactures both two-wheelers (TW) and four-wheelers (FW).
     * Given:
     * - V = Total number of vehicles
     * - W = Total number of wheels
     * 
     * Task:
     * Determine the number of two-wheelers (TW) and four-wheelers (FW) produced.
     * If the input is invalid (e.g. W is odd, W < 2, V >= W, or computed values are negative),
     * output "INVALID INPUT".
     * 
     * Mathematical Derivation:
     * Let TW = number of 2-wheelers, FW = number of 4-wheelers.
     * (1) TW + FW = V       ==> TW = V - FW
     * (2) 2*TW + 4*FW = W
     * 
     * Substitute (1) into (2):
     * 2*(V - FW) + 4*FW = W
     * 2*V - 2*FW + 4*FW = W
     * 2*FW = W - 2*V
     * FW = (W - 2*V) / 2
     * TW = V - FW
     * 
     * Valid Input Conditions:
     * 1. W >= 2 and W is even (W % 2 == 0)
     * 2. V < W
     * 3. (W - 2*V) >= 0 and (W - 2*V) % 2 == 0
     * 4. FW >= 0 and TW >= 0
     * 
     * Complexity:
     * - Time Complexity: O(1)
     * - Space Complexity: O(1)
     */

    public static int[] calculateVehicles(int v, int w) {
        // Validation checks
        if (w < 2 || w % 2 != 0 || v >= w) {
            return null; // Invalid
        }

        int fourWheelers = (w - (2 * v)) / 2;
        int twoWheelers = v - fourWheelers;

        if (fourWheelers < 0 || twoWheelers < 0) {
            return null; // Invalid
        }

        return new int[]{twoWheelers, fourWheelers};
    }

    public static void printResult(int v, int w) {
        int[] result = calculateVehicles(v, w);
        if (result == null) {
            System.out.println("V = " + v + ", W = " + w + " -> INVALID INPUT");
        } else {
            System.out.println("V = " + v + ", W = " + w + " -> TW = " + result[0] + ", FW = " + result[1]);
        }
    }

    public static void main(String[] args) {
        // Automated Unit Tests
        System.out.println("=== Automated Test Cases ===");
        printResult(200, 540); // Expected: TW = 130, FW = 70
        printResult(100, 300); // Expected: TW = 50, FW = 50
        printResult(10, 20);   // Expected: TW = 10, FW = 0
        printResult(10, 40);   // Expected: TW = 0, FW = 10
        printResult(20, 10);   // Expected: INVALID INPUT (V >= W)
        printResult(100, 255); // Expected: INVALID INPUT (W is odd)

        // Interactive Scanner Input for Competitive Platforms
        if (args.length > 0 && args[0].equals("--interactive")) {
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                int v = sc.nextInt();
                int w = sc.nextInt();
                int[] res = calculateVehicles(v, w);
                if (res == null) {
                    System.out.println("INVALID INPUT");
                } else {
                    System.out.println("TW = " + res[0] + " FW = " + res[1]);
                }
            }
            sc.close();
        }
    }
}
