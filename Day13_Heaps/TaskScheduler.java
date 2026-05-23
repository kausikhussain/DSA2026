package Day13_Heaps;

import java.util.Collections;
import java.util.PriorityQueue;

public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        // Count frequencies of each task
        int[] frequencies = new int[26];
        for (char task : tasks) {
            frequencies[task - 'A']++;
        }
        
        // A Max-Heap to always schedule the task with the highest remaining frequency first
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int freq : frequencies) {
            if (freq > 0) {
                maxHeap.add(freq);
            }
        }
        
        int time = 0;
        
        // Simulate the task scheduling
        while (!maxHeap.isEmpty()) {
            java.util.List<Integer> waitList = new java.util.ArrayList<>();
            
            // Try to schedule tasks for the current cycle (which has length n + 1)
            int cycle = n + 1;
            while (cycle > 0 && !maxHeap.isEmpty()) {
                int maxFreq = maxHeap.poll(); // Schedule the most frequent task
                maxFreq--; // Task executed, decrease frequency
                
                if (maxFreq > 0) {
                    waitList.add(maxFreq); // Need to run this task again later
                }
                
                time++;
                cycle--;
            }
            
            // Add the tasks back to the heap for the next cycle
            maxHeap.addAll(waitList);
            
            // If the heap is empty, we are done. 
            // If not empty, we might have had idle time to finish the cycle, so add 'cycle' to time.
            if (!maxHeap.isEmpty()) {
                time += cycle;
            }
        }
        
        return time;
    }
}
