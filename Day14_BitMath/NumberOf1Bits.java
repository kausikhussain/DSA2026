package Day14_BitMath;

public class NumberOf1Bits {
    public int hammingWeight(int n) {
        int count = 0;
        
        while (n != 0) {
            // n & (n - 1) flips the least significant 1-bit to 0.
            // We count how many times we can do this until the number becomes 0.
            n = n & (n - 1);
            count++;
        }
        
        return count;
    }
}
