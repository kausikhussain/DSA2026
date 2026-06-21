package Practice;

public class MajorityElement {
    /**
     * Practice Question: Majority Element
     * 
     * Given an array nums of size n, return the majority element.
     * The majority element is the element that appears more than ⌊n / 2⌋ times.
     * You may assume that the majority element always exists in the array.
     * 
     * Follow-up: Could you solve the problem in linear time and in O(1) space?
     */
    public int majorityElement(int[] nums) {
        // We use the Boyer-Moore Voting Algorithm to achieve O(N) time and O(1) space.
        int candidate = 0;
        int count = 0;

        for (int num : nums) {
            // If the count drops to 0, we pick a new candidate
            if (count == 0) {
                candidate = num;
            }
            
            // If the current number is the candidate, we increment its "votes", otherwise we decrement
            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        // Since the problem guarantees a majority element always exists, 
        // the remaining candidate MUST be the majority element.
        return candidate;
    }

    public static void main(String[] args) {
        MajorityElement solution = new MajorityElement();
        
        int[] test1 = {3, 2, 3};
        System.out.println("Test 1 [3,2,3]: " + solution.majorityElement(test1)); // Expected: 3
        
        int[] test2 = {2, 2, 1, 1, 1, 2, 2};
        System.out.println("Test 2 [2,2,1,1,1,2,2]: " + solution.majorityElement(test2)); // Expected: 2
    }
}
