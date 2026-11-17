package TCS_NQT;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SundaysCount {
    /**
     * TCS NQT Coding Question: Count Sundays in N Days
     * 
     * Problem Statement:
     * Given:
     * - A string representing the starting day of a period (e.g., "mon", "tue", "wed", "thu", "fri", "sat", "sun").
     * - An integer N representing the total number of days in the period.
     * 
     * Task:
     * Calculate and return the total number of Sundays that fall within the given N days.
     * 
     * Approach:
     * - Determine the 1-based day index of the first Sunday in the cycle:
     *   - "sun" -> Day 1
     *   - "sat" -> Day 2
     *   - "fri" -> Day 3
     *   - "thu" -> Day 4
     *   - "wed" -> Day 5
     *   - "tue" -> Day 6
     *   - "mon" -> Day 7
     * - If total days N < firstSundayDay:
     *   - There are 0 Sundays in the period.
     * - Otherwise:
     *   - The first Sunday counts as 1.
     *   - The remaining Sundays occur every 7 days: 1 + (N - firstSundayDay) / 7.
     * 
     * Complexity:
     * - Time Complexity: O(1) mathematical calculation.
     * - Space Complexity: O(1) auxiliary space.
     */

    private static final Map<String, Integer> FIRST_SUNDAY_MAP = new HashMap<>();

    static {
        FIRST_SUNDAY_MAP.put("sun", 1);
        FIRST_SUNDAY_MAP.put("sat", 2);
        FIRST_SUNDAY_MAP.put("fri", 3);
        FIRST_SUNDAY_MAP.put("thu", 4);
        FIRST_SUNDAY_MAP.put("wed", 5);
        FIRST_SUNDAY_MAP.put("tue", 6);
        FIRST_SUNDAY_MAP.put("mon", 7);
    }

    public static int countSundays(String startDay, int n) {
        if (startDay == null || n <= 0) {
            return 0;
        }

        String normalized = startDay.trim().toLowerCase();
        if (normalized.length() >= 3) {
            normalized = normalized.substring(0, 3);
        }

        Integer firstSundayDay = FIRST_SUNDAY_MAP.get(normalized);
        if (firstSundayDay == null) {
            return 0; // Invalid day name
        }

        if (n < firstSundayDay) {
            return 0;
        }

        return 1 + (n - firstSundayDay) / 7;
    }

    public static void main(String[] args) {
        // Automated Unit Tests
        System.out.println("=== Automated Test Cases ===");

        System.out.println("Test 1 (\"mon\", 13): " + countSundays("mon", 13)); 
        // Expected: 1 (Sunday on day 7)

        System.out.println("Test 2 (\"sun\", 8): " + countSundays("sun", 8)); 
        // Expected: 2 (Sundays on day 1 and day 8)

        System.out.println("Test 3 (\"wed\", 31): " + countSundays("wed", 31)); 
        // Expected: 4 (Sundays on day 5, 12, 19, 26)

        System.out.println("Test 4 (\"wed\", 4): " + countSundays("wed", 4)); 
        // Expected: 0 (first Sunday is day 5)

        System.out.println("Test 5 (\"Saturday\", 14): " + countSundays("Saturday", 14)); 
        // Expected: 2 (Sundays on day 2 and day 9)

        // Interactive Scanner Input for Competitive Platforms
        if (args.length > 0 && args[0].equals("--interactive")) {
            Scanner sc = new Scanner(System.in);
            if (sc.hasNext()) {
                String day = sc.next();
                int days = sc.nextInt();
                System.out.println(countSundays(day, days));
            }
            sc.close();
        }
    }
}
