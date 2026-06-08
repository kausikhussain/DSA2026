package Practice;

import java.util.Scanner;

public class StringChallenge {

    public static String stringChallenge(String str) {
        // If the string is smaller than 5 characters, 
        // it's impossible to have 3 characters between 'a' and 'b'.
        if (str == null || str.length() < 5) {
            return "false";
        }
        
        // Loop through the string up to length - 4 
        // because we are checking indices i and i + 4
        for (int i = 0; i < str.length() - 4; i++) {
            char c1 = str.charAt(i);
            char c2 = str.charAt(i + 4);
            
            // Check if the characters are 'a' and 'b' in either order
            if ((c1 == 'a' && c2 == 'b') || (c1 == 'b' && c2 == 'a')) {
                return "true";
            }
        }
        
        return "false";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextLine()) {
            String input = sc.nextLine();
            System.out.println(stringChallenge(input));
        }
        sc.close();
    }
}
