/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.engine;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Skulsinlapakit
 */
public abstract class Engine{
    
    
    public static int[][] setBlockToCheck(int[][] matrixTet) {
        
        int[][] blockToCheck = new int[3][matrixTet.length];
        
      //  blockToCheck = new int[3][matrixOfBlock[0].length]; 
        
        for(int col = 0; col < matrixTet[0].length; col++) {

            for(int row = 0; row < matrixTet.length; row++) {
                
                if(matrixTet[row][col] > 0) {
                    blockToCheck[0][col] = row+1;
                    
                   // blockToCheck[1][row] = -1;
                    blockToCheck[1][row] = col+1;
                }
                if(matrixTet[row][matrixTet[0].length-1 - col] > 0) {
                    //blockToCheck[2][row] = matrixOfBlock[0].length-1 - col -1;
                    blockToCheck[2][row] = Math.abs(0 - col-1);
                }
                

            }
        }
        
        for(int i = 0; i < 4 ; i++) { System.out.print(blockToCheck[2][i]+" "); }
        System.out.println();
        
        return blockToCheck;
    }
    
    public static boolean isAbleToFall(int[][] matrixBoard, int[][] matrixTet, int tetPosX, int tetPosY, int[][] blockToCheck) {
        
        boolean isAbleToFall = true;
        
        for(int col = 0; col < matrixTet[0].length; col++) {
            
            
            if(blockToCheck[0][col] > 0) {
                if(tetPosY+blockToCheck[0][col] < matrixBoard.length)  {      //prevent array out of bound 
                    
                    if(matrixBoard[tetPosY+blockToCheck[0][col]][Math.max(0, Math.min(matrixBoard[0].length-1, tetPosX+col))] == 0) {
                        isAbleToFall &= true;
                    }
                    else {
                        isAbleToFall &= false;
                    }
                }
                else {
                    isAbleToFall &= false;
                }
            }
        }

        return isAbleToFall;
    }
    
    public static boolean isMovableRight(int[][] matrixBoard, int[][] matrixTet, int tetPosX, int tetPosY, int[][] blockToCheck) {
        
        boolean isMovableRight = true;
        
        for(int row = 0; row < matrixTet.length; row++) {
  
            if(blockToCheck[1][row] > 0) {
                if(tetPosX+blockToCheck[1][row] < matrixBoard[0].length) {           //prevent array out of bound

                    if(matrixBoard[Math.min(matrixBoard.length-1, tetPosY+row)][tetPosX+blockToCheck[1][row]] == 0) {
                        isMovableRight &= true;
                    }
                    else isMovableRight &= false;
                }
                else {
                    isMovableRight &= false;
                }
            }
            //else isAbleToFall &= false; 
        }
        return isMovableRight;
    }
    
    public static boolean isMovableLeft(int[][] matrixBoard, int[][] matrixTet, int tetPosX, int tetPosY, int[][] blockToCheck) {
        boolean isMovableLeft = true;
        
        for(int row = 0; row < matrixTet.length; row++) {
            
            if(blockToCheck[2][row] > 0) {

                if(tetPosX+matrixTet[0].length - blockToCheck[2][row] > 0) {        //prevent array out of bound
                   
                    if(matrixBoard[Math.max(0, tetPosY+row)][Math.max(0, tetPosX+matrixTet[0].length-1 - blockToCheck[2][row])] == 0) {
                        isMovableLeft &= true;
                    }
                    else isMovableLeft &= false;
                }
                else {
                    isMovableLeft &= false;
                }
            }
        }
        
        return isMovableLeft;
    }
    

    
    public static void landed(int[][] matrixBoard, int[][] matrixTet, int tetPosX, int tetPosY, int[][] blockToCheck) {
       // topLeftY += 5;

        for(int row = 0; row < matrixTet.length; row++) {
            for(int col = 0; col < matrixTet[0].length; col++) {
                if( (row+tetPosY < matrixBoard.length) && (col+tetPosX < matrixBoard[0].length) && (col+tetPosX >= 0) ) {   //prevent array out of bound
                    if(matrixBoard[row+tetPosY][col+tetPosX] == 0) {
                        matrixBoard[row+tetPosY][col+tetPosX] = matrixTet[row][col];
                    }
                }
            }
        }
        
    }
    
    public static int clear(int[][] matrixBoard , int[][] matrixTet, int tetPosX, int tetPosY, int score) {
        
        boolean isClear;
        for(int row = tetPosY; row < tetPosY+matrixTet.length && row < matrixBoard.length; row++) {
            isClear = true;
            for(int col = 0; col < matrixBoard[0].length; col++) {
                if(matrixBoard[row][col] > 0) {
                    isClear &= true;
                }
                else isClear &= false;
                System.out.print(matrixBoard[row][col]);
            }
            if(isClear) {
                for(int clearCol = 0; clearCol < matrixBoard[0].length; clearCol++) {
                    
                    // slowly clear line
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    score += 5;
                    matrixBoard[row][clearCol] = 0;
                    System.out.print(score);
                }

                //shift board line
                for(int clearRow = matrixBoard.length-1; row >= 0; row--) {
                    matrixBoard[row] = matrixBoard[Math.max(0, row-1)];
                }
                
                
            }
            System.out.println();
        }
        
        return score;
        
    }


    
}
