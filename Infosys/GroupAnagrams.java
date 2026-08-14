package Infosys;

import java.util.*;

public class GroupAnagrams {
    /**
     * LeetCode 49: Group Anagrams (Infosys)
     * 
     * Given an array of strings strs, group the anagrams together. 
     * You can return the answer in any order.
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        
        // We use a Map where the Key is the sorted version of the string,
        // and the Value is a List containing all original strings that match that sorted key.
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            // Convert string to character array, sort it, and convert back to string
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedKey = String.valueOf(chars);
            
            // If the key is not in the map, create a new list for it
            if (!map.containsKey(sortedKey)) {
                map.put(sortedKey, new ArrayList<>());
            }
            
            // Add the original string to the corresponding list
            map.get(sortedKey).add(str);
        }
        
        // Return a list of all the values (grouped anagrams) in the map
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        GroupAnagrams solution = new GroupAnagrams();
        
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println("Grouped Anagrams: " + solution.groupAnagrams(strs));
        // Expected groups: [["bat"],["nat","tan"],["ate","eat","tea"]]
    }
}
