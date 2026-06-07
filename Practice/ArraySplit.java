package Practice;

import java.util.*;

public class ArraySplit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) return;
        String line = sc.nextLine().trim();
        
        // Remove brackets and split by comma
        line = line.replace("[", "").replace("]", "");
        if (line.isEmpty()) {
            System.out.println("-1");
            return;
        }
        
        String[] parts = line.split(",");
        int[] arr = new int[parts.length];
        long totalSum = 0;
        for (int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i].trim());
            totalSum += arr[i];
        }
        
        int n = arr.length;
        if (n % 2 != 0 || totalSum % 2 != 0) {
            System.out.println("-1");
            return;
        }
        
        long targetSum = totalSum / 2;
        int targetCount = n / 2;
        
        List<List<Integer>> validSplits = new ArrayList<>();
        
        // Use recursion to find all combinations of size n/2
        findCombinations(arr, targetCount, targetSum, 0, new ArrayList<>(), validSplits);
        
        if (validSplits.isEmpty()) {
            System.out.println("-1");
            return;
        }
        
        // Sort the list of valid splits to find the lexicographically smallest
        Collections.sort(validSplits, (a, b) -> {
            for (int i = 0; i < a.size(); i++) {
                if (!a.get(i).equals(b.get(i))) {
                    return a.get(i).compareTo(b.get(i));
                }
            }
            return 0;
        });
        
        // The first list in validSplits is now the lexicographically smallest first set
        List<Integer> set1 = validSplits.get(0);
        List<Integer> set2 = new ArrayList<>();
        
        // Find set2
        List<Integer> arrList = new ArrayList<>();
        for (int x : arr) arrList.add(x);
        
        List<Integer> tempSet1 = new ArrayList<>(set1);
        for (int x : arrList) {
            if (tempSet1.contains(x)) {
                tempSet1.remove((Integer) x);
            } else {
                set2.add(x);
            }
        }
        Collections.sort(set2);
        
        // Format output
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < set1.size(); i++) sb.append(set1.get(i)).append(",");
        for (int i = 0; i < set2.size(); i++) sb.append(set2.get(i)).append(i == set2.size() - 1 ? "" : ",");
        
        System.out.println(sb.toString());
    }
    
    private static void findCombinations(int[] arr, int k, long targetSum, int start, List<Integer> current, List<List<Integer>> results) {
        if (current.size() == k) {
            long sum = 0;
            for (int x : current) sum += x;
            if (sum == targetSum) {
                List<Integer> sorted = new ArrayList<>(current);
                Collections.sort(sorted);
                results.add(sorted);
            }
            return;
        }
        
        for (int i = start; i < arr.length; i++) {
            current.add(arr[i]);
            findCombinations(arr, k, targetSum, i + 1, current, results);
            current.remove(current.size() - 1);
        }
    }
}
