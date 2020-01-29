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
public class Tetromino extends Game implements Shape{
    
    private int tetPosX, tetPosY;                       //store position of tetromino that count in block
    private int tetCoordX, tetCoordY;                   //store position of tetromino in coordinate
    private int currentTet, nextTet = -1;
    private final int player;
    private int rotationAngle;
    
    private int[][] blockToCheck;
    private int[][] matrixTet;
    private int[][] hudTet;

    
    public Tetromino(int boardCoordX, int boardCoordY, int player) {
        this.player = player;
        score = new int[2];
        initTetromino(boardCoordX, boardCoordY);
    }
    
    
    private void initTetromino(int boardCoordX, int boardCoordY) {
        this.boardCoordX = boardCoordX;
        this.boardCoordY = boardCoordY;
        tetPosX = 6;
        tetPosY = 0;
        hudTet = new int[4][4];
       // currentTet = (new java.util.Random()).nextInt(SHAPE_LIST.length);
        
       
        if(nextTet == -1) {
            currentTet = (new java.util.Random()).nextInt(SHAPE_LIST.length);
            nextTet = (new java.util.Random()).nextInt(SHAPE_LIST.length);
        }
        else {
            currentTet = nextTet;
            nextTet = (new java.util.Random()).nextInt(SHAPE_LIST.length);
        }
        hudTet = SHAPE_LIST[nextTet];
        matrixTet = SHAPE_LIST[currentTet];        
        
        rotationAngle = 0;
        blockToCheck = tetris.engine.Engine.setBlockToCheck(matrixTet);
    }
    
    public void draw(java.awt.Graphics g) {
        tetCoordX = ( (tetPosX) * BLOCK_SIZE ) + boardCoordX;
        tetCoordY = ( (tetPosY) * BLOCK_SIZE ) + boardCoordY;
        tetris.engine.Render.drawBlock(g, matrixTet, tetCoordX, tetCoordY, BLOCK_SIZE, BLOCK_SIZE, 1); 
        tetris.engine.Render.drawScore(g, score[player], scoreCoordX[player], scoreCoordY[player]);
        tetris.engine.Render.drawBlock(g, hudTet, hudCoordX[player], hudCoordY[player], HUDBLOCK_SIZE, HUDBLOCK_SIZE, 1); 
      //  Render.drawBlock(g, miniBlock, miniCoordX[player], miniCoordY[player], MINIBLOCK_SIZE, MINIBLOCK_SIZE, 1); 
    }
    
    public void moveLeft() {
        if(tetris.engine.Engine.isMovableLeft(matrixOfBoard[player], matrixTet, tetPosX, tetPosY, blockToCheck)) {
            tetPosX--;
        }
    }
    
    public void moveRight() {
        if(tetris.engine.Engine.isMovableRight(matrixOfBoard[player], matrixTet, tetPosX, tetPosY, blockToCheck)) {
            tetPosX++;
        }
    }
    
    public void rotate() {
        
        blockToCheck = new int[3][4]; 
        
        switch(rotationAngle) {
            case 0 :
                matrixTet = SHAPE_LIST[currentTet]; 
                matrixTet = tetris.engine.MatrixOperator.rotateClockWise90(matrixTet);
                rotationAngle++;
                break;
            case 1 :
                matrixTet = SHAPE_LIST[currentTet]; 
                matrixTet = tetris.engine.MatrixOperator.rotateClockWise180(matrixTet);
                rotationAngle++;
                break;
            case 2 :
                matrixTet = SHAPE_LIST[currentTet]; 
                matrixTet = tetris.engine.MatrixOperator.rotateClockWise270(matrixTet);
                rotationAngle++;
                break;
            case 3 :
                matrixTet = SHAPE_LIST[currentTet]; 
                rotationAngle = 0;
        }
        
        
        blockToCheck = tetris.engine.Engine.setBlockToCheck(matrixTet);
        
    }
    
    public void fall() {
         if(tetris.engine.Engine.isAbleToFall(matrixOfBoard[player], matrixTet, tetPosX, tetPosY, blockToCheck)) {
            tetPosY++;
        }
         else {
            tetris.engine.Engine.landed(matrixOfBoard[player], matrixTet, tetPosX, tetPosY, blockToCheck);
            score[player] = tetris.engine.Engine.clear(matrixOfBoard[player], matrixTet, tetPosX, tetPosY, score[player]);
            initTetromino(boardCoordX, boardCoordY);
        }  
    }
    
    
    
    
}
