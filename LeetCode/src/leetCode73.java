/*
 * LeetCode 73. Set Matrix Zeroes
 * https://leetcode.com/problems/set-matrix-zeroes/
 */

public class leetCode73 {

	public static void main(String[] args) {
		int[][] matrix = new int[][] {{1,1,1},{1,0,1},{1,1,1}};
		System.out.println(matrix.toString());
	}

	public static void setZeroes(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		
		boolean colZero = false;
		boolean rowZero = false;
        
        for(int i = 0; i < m ; i++) {
        	if(matrix[i][0] == 0) {
        		colZero = true;
        		break;
        	}
        }
        for(int i = 0; i < n ; i++) {
        	if(matrix[0][i] == 0) {
        		rowZero = true;
        		break;
        	}
        }

        for(int i = 1; i < m; i++) {
        	for(int j = 1; j < n; j++) {
        		if(matrix[i][j] == 0) {
        			matrix[i][0] = 0;
        			matrix[0][j] = 0;
        		}
        	}
        }
        
        for(int i = 1; i < m; i++) {
        	for(int j = 1; j < n; j++) {
        		if(matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;
        	}
        }
        
        if(colZero) {
        	for(int i = 0; i < m; i++) matrix[i][0] = 0;
        }
        
        if(rowZero) {
        	for(int i = 0; i < n; i++) matrix[0][i] = 0;
        }
    }
}
