package Practice;

public class NumberOfProvinces {
    /**
     * LeetCode 547: Number of Provinces
     * 
     * There are n cities. A province is a group of directly or indirectly connected cities.
     * 
     * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the i-th city 
     * and the j-th city are directly connected, and isConnected[i][j] = 0 otherwise.
     * 
     * Return the total number of provinces.
     */
    public int findCircleNum(int[][] isConnected) {
        if (isConnected == null || isConnected.length == 0) {
            return 0;
        }

        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinceCount = 0;

        for (int i = 0; i < n; i++) {
            // If the city has not been visited yet, it belongs to a new province (connected component)
            if (!visited[i]) {
                dfs(isConnected, visited, i);
                provinceCount++;
            }
        }

        return provinceCount;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int city) {
        visited[city] = true;

        for (int neighbor = 0; neighbor < isConnected.length; neighbor++) {
            // Traverse all directly connected, unvisited neighbor cities
            if (isConnected[city][neighbor] == 1 && !visited[neighbor]) {
                dfs(isConnected, visited, neighbor);
            }
        }
    }

    public static void main(String[] args) {
        NumberOfProvinces solution = new NumberOfProvinces();

        int[][] isConnected1 = {
            {1, 1, 0},
            {1, 1, 0},
            {0, 0, 1}
        };
        System.out.println("Test 1: " + solution.findCircleNum(isConnected1)); 
        // Expected: 2 (City 0 and 1 form province 1, City 2 forms province 2)

        int[][] isConnected2 = {
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
        };
        System.out.println("Test 2: " + solution.findCircleNum(isConnected2)); 
        // Expected: 3 (Each city is isolated in its own province)

        int[][] isConnected3 = {
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
        };
        System.out.println("Test 3: " + solution.findCircleNum(isConnected3)); 
        // Expected: 1 (All cities fully connected)
    }
}
