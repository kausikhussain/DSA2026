package Practice;

import java.util.Scanner;

public class StringValidation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();

        boolean valid = true;

        // Length should be 6
        if (str.length() != 6) {
            valid = false;
        }
        // First character should be uppercase letter
        else if (!Character.isUpperCase(str.charAt(0))) {
            valid = false;
        }
        // Remaining 5 characters should be digits
        else {
            for (int i = 1; i < 6; i++) {
                if (!Character.isDigit(str.charAt(i))) {
                    valid = false;
                    break;
                }
            }
        }

        if (valid) {
            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
        }

        sc.close();
    }
}
