/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.engine;

/**
 *
 * @author Skulsinlapakit
 */
public abstract class MatrixOperator {
    
    private static int[][] transpose(int[][] matrix) {
        
        int[][] result = new int[matrix.length][matrix[0].length];
 
        for(int row = 0; row < matrix.length; row++) {
            for(int col = 0; col < matrix[0].length; col++) {
                result[col][row] = matrix[row][col];
            }
        }
        
        return result;
    }
    
    private static int[][] horizontalReflect(int [][] matrix) {
        
        int[][] result = new int[matrix.length][matrix[0].length];

        for(int row = 0; row < matrix.length; row++) {
            for(int col = 0; col < matrix[0].length; col++) {

                result[row][col] = matrix[matrix.length-1-row][col];
                        
                        
            }
        }
        
        return result;
        
    }
    
    private static int[][] verticalReflect(int [][] matrix) {
        
        int[][] result = new int[matrix.length][matrix[0].length];

        for(int row = 0; row < matrix.length; row++) {
            for(int col = 0; col < matrix[0].length; col++) {
            
                result[row][col] = matrix[row][matrix[0].length-col-1];
            }
        }
        
        return result;
        
    }
    
    public static int[][] rotateClockWise90(int[][] matrix) {
        
        int[][] result;
        
        result = transpose(matrix);
        result = horizontalReflect(result);
        
        return result;
    }
    
    public static int[][] rotateClockWise180(int[][] matrix) {
        
        int[][] result;
        
        result = horizontalReflect(matrix);
        result = verticalReflect(result);
        
        return result;
    }
    
    public static int[][] rotateClockWise270(int[][] matrix) {
        
        int[][] result;
        
        result = transpose(matrix);
        result = verticalReflect(result);
        
        return result;
    }
    
}
