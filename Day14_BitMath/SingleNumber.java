package Day14_BitMath;

public class SingleNumber {
    public int singleNumber(int[] nums) {
        int result = 0;
        
        // XOR-ing a number with itself gives 0 (e.g., 5 ^ 5 = 0).
        // XOR-ing a number with 0 gives the number itself (e.g., 5 ^ 0 = 5).
        // Since every number appears twice except one, XOR-ing all elements 
        // will cancel out the duplicates, leaving only the single number.
        for (int num : nums) {
            result ^= num;
        }
        
        return result;
    }
}
