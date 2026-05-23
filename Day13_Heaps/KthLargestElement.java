package Day13_Heaps;

import java.util.PriorityQueue;

public class KthLargestElement {
    public int findKthLargest(int[] nums, int k) {
        // A Min-Heap to keep track of the 'k' largest elements seen so far
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int num : nums) {
            minHeap.add(num);
            
            // If the heap grows larger than 'k', pop the smallest element.
            // This ensures the heap only ever contains the 'k' largest elements.
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        // The root of the Min-Heap is the kth largest element overall
        return minHeap.peek();
    }
}
