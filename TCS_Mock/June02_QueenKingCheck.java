package TCS_Mock;

public class June02_QueenKingCheck {

    public static int getSafeSquares(String[] positions) {
        // Safely parse "(x1,y1)" and "(x2,y2)"
        String[] qCoords = positions[0].replaceAll("[^0-9,]", "").split(",");
        String[] kCoords = positions[1].replaceAll("[^0-9,]", "").split(",");
        
        int qx = Integer.parseInt(qCoords[0]);
        int qy = Integer.parseInt(qCoords[1]);
        
        int kx = Integer.parseInt(kCoords[0]);
        int ky = Integer.parseInt(kCoords[1]);
        
        // 1. Check if King is currently in check
        boolean inCheck = isUnderAttack(kx, ky, qx, qy);
        
        if (!inCheck) {
            return -1;
        }
        
        // 2. Count safe squares for the King
        int safeCount = 0;
        
        // 8 possible directions for the King
        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1},  {1, 0},  {1, 1}
        };
        
        for (int[] dir : directions) {
            int nkx = kx + dir[0];
            int nky = ky + dir[1];
            
            // Check if the new square is within the 8x8 board boundaries
            if (nkx >= 1 && nkx <= 8 && nky >= 1 && nky <= 8) {
                // If King captures the Queen, it is a safe move (since no other pieces exist)
                if (nkx == qx && nky == qy) {
                    safeCount++;
                } 
                // Otherwise, the square must not be under attack
                else if (!isUnderAttack(nkx, nky, qx, qy)) {
                    safeCount++;
                }
            }
        }
        
        return safeCount;
    }
    
    // Helper method to determine if a target square is attacked by the Queen
    private static boolean isUnderAttack(int targetX, int targetY, int qx, int qy) {
        return (targetX == qx) || // Same column
               (targetY == qy) || // Same row
               (Math.abs(targetX - qx) == Math.abs(targetY - qy)); // Same diagonal
    }

    public static void main(String[] args) {
        // Example 1
        String[] input1 = {"(1,1)", "(1,4)"};
        System.out.println(getSafeSquares(input1)); // Expected Output: 3
        
        // Example 2
        String[] input2 = {"(3,1)", "(4,4)"};
        System.out.println(getSafeSquares(input2)); // Expected Output: -1
    }
}
