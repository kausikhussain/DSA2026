package Practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 981: Time Based Key-Value Store
 * Difficulty: Medium
 * 
 * Design a time-based key-value data structure that can store multiple values 
 * for the same key at different time stamps and retrieve the key's value at a certain timestamp.
 * 
 * Implement the TimeMap class:
 * - TimeMap() Initializes the object of the data structure.
 * - void set(String key, String value, int timestamp) Stores the key with the 
 *   value at the given time timestamp.
 * - String get(String key, int timestamp) Returns a value such that set was called 
 *   previously, with timestamp_prev <= timestamp. If there are multiple such values, 
 *   it returns the value associated with the largest timestamp_prev. If there are 
 *   no values, it returns "".
 * 
 * Approach:
 * - HashMap + Binary Search.
 * - Use a HashMap mapping each String key to a List of TimeEntry objects (timestamp, value).
 * - Since timestamps are strictly increasing in set operations, the List is sorted by timestamp.
 * - For get operations, perform binary search to find the largest timestamp <= target.
 * 
 * Complexity:
 * - Time:
 *   - set(): O(1) amortized insertion to array list.
 *   - get(): O(log M) binary search, where M is the number of timestamps for the key.
 * - Space: O(N), where N is total number of key-value pairs stored across all keys.
 */
public class TimeMap {

    private static class TimeEntry {
        final int timestamp;
        final String value;

        TimeEntry(int timestamp, String value) {
            this.timestamp = timestamp;
            this.value = value;
        }
    }

    private final Map<String, List<TimeEntry>> map;

    public TimeMap() {
        this.map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(new TimeEntry(timestamp, value));
    }

    public String get(String key, int timestamp) {
        List<TimeEntry> entries = map.get(key);
        if (entries == null || entries.isEmpty()) {
            return "";
        }

        // Binary search for largest timestamp <= target timestamp
        int left = 0;
        int right = entries.size() - 1;
        String result = "";

        while (left <= right) {
            int mid = left + (right - left) / 2;
            TimeEntry current = entries.get(mid);

            if (current.timestamp <= timestamp) {
                result = current.value;
                left = mid + 1; // Try to find a larger valid timestamp to the right
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        TimeMap timeMap = new TimeMap();

        timeMap.set("foo", "bar", 1);
        System.out.println("get('foo', 1): " + timeMap.get("foo", 1)); // Expected: "bar"
        System.out.println("get('foo', 3): " + timeMap.get("foo", 3)); // Expected: "bar" (prev timestamp 1)

        timeMap.set("foo", "bar2", 4);
        System.out.println("get('foo', 4): " + timeMap.get("foo", 4)); // Expected: "bar2"
        System.out.println("get('foo', 5): " + timeMap.get("foo", 5)); // Expected: "bar2" (prev timestamp 4)

        System.out.println("get('foo', 0): \"" + timeMap.get("foo", 0) + "\""); // Expected: "" (no timestamp <= 0)
        System.out.println("get('unknown', 1): \"" + timeMap.get("unknown", 1) + "\""); // Expected: ""
    }
}
