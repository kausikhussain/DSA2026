package Practice;

import java.util.Scanner;

public class EmployeeIdValidation {

    // Approach 1: Using Loops and Character Functions
    public static boolean isValidLoop(String id) {
        if (id == null || id.length() != 6) {
            return false;
        }

        // Check if first character is an uppercase letter
        if (!Character.isUpperCase(id.charAt(0))) {
            return false;
        }

        // Check if the remaining 5 characters are digits
        for (int i = 1; i < 6; i++) {
            if (!Character.isDigit(id.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    // Approach 2: Using Regular Expressions (Regex)
    public static boolean isValidRegex(String id) {
        if (id == null) return false;
        
        // Regex Explanation:
        // ^        -> Start of string
        // [A-Z]    -> Exactly one uppercase English letter
        // \\d{5}   -> Exactly 5 digits (equivalent to [0-9]{5})
        // $        -> End of string
        return id.matches("^[A-Z]\\d{5}$");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        if (sc.hasNextLine()) {
            String input = sc.nextLine().trim();

            // We can test either approach. Let's use the Regex approach for this run.
            boolean valid = isValidRegex(input);
            
            // To test the loop approach instead:
            // boolean valid = isValidLoop(input);

            if (valid) {
                System.out.println("Valid Employee ID");
            } else {
                System.out.println("Invalid Employee ID");
            }
        }
        
        sc.close();
    }
}
