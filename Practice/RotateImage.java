package Practice;

import java.util.Arrays;

public class RotateImage {
    /**
     * LeetCode 48: Rotate Image
     * 
     * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
     * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. 
     * DO NOT allocate another 2D matrix and do the rotation.
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        
        // Step 1: Transpose the matrix (swap matrix[i][j] with matrix[j][i])
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        
        // Step 2: Reverse each row of the transposed matrix
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n - 1;
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }

    public static void main(String[] args) {
        RotateImage solution = new RotateImage();
        
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        solution.rotate(matrix);
        
        System.out.println("Rotated Matrix:");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        // Expected Output:
        // [7, 4, 1]
        // [8, 5, 2]
        // [9, 6, 3]
    }
}
