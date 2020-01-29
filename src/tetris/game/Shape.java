/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.game;

/**
 *
 * @author Skulsinlapakit
 */
public interface Shape {
    
    //public final int[][] tetroMatrix;

    public static final int[][] SHAPE_T = {
        {0, 1, 0, 0},
        {1, 1, 1, 0},
        {0, 0, 0, 0},
        {0, 0, 0, 0}  
    };
    
    public static final int[][] SHAPE_I = {
        {0, 2, 0, 0},
        {0, 2, 0, 0},
        {0, 2, 0, 0},
        {0, 2, 0, 0}  
    };
    
    public static final int[][] SHAPE_O = {
        {0, 0, 0, 0},
        {0, 3, 3, 0},
        {0, 3, 3, 0},
        {0, 0, 0, 0}  
    };
    
    public static final int[][] SHAPE_J = {
        {0, 4, 4, 0},
        {0, 4, 0, 0},
        {0, 4, 0, 0},
        {0, 0, 0, 0}  
    };
    
     public static final int[][] SHAPE_L = {
        {0, 5, 5, 0},
        {0, 0, 5, 0},
        {0, 0, 5, 0},
        {0, 0, 0, 0}  
    };
    
    public static final int[][] SHAPE_S = {
        {0, 6, 0, 0},
        {0, 6, 6, 0},
        {0, 0, 6, 0},
        {0, 0, 0, 0}  
    };
    
    public static final int[][] SHAPE_Z = {
        {0, 0, 7, 0},
        {0, 7, 7, 0},
        {0, 7, 0, 0},
        {0, 0, 0, 0}  
    };
    
    public static final int[][][] SHAPE_LIST = {SHAPE_T, SHAPE_I, SHAPE_O, SHAPE_J, SHAPE_L, SHAPE_S, SHAPE_Z};         //add each shape array to an array.
    
    //public abstract void drawShape(java.awt.Graphics g);
    
    
    
}
