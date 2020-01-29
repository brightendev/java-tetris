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
public class Board extends Game {
    

   // private final int[][] boardMatrix;        //matrix of board
    private final int BOARD_WIDTH = 14;        //width of board in cell
    private final int BOARD_HEIGHT = 23;       //height of board in cell
    private static final int PLAYER_NUMBER = 2;
  //  private final int boardCoordX, boardCoordY;
    private final int player;
    private int[][] matrixBoard;
    

    public Board(int boardCoordX, int boardCoordY, int player) {

        this.player = player;
       
        matrixOfBoard = new int[PLAYER_NUMBER][BOARD_HEIGHT][BOARD_WIDTH];
        
        this.boardCoordX = boardCoordX;
        this.boardCoordY = boardCoordY;
    }

    

    public void draw(java.awt.Graphics g) {
     //   if(matrixOfBlock[row][col] == 0) {
            matrixBoard = matrixOfBoard[player];

            tetris.engine.Render.drawBlock(g, matrixBoard, boardCoordX, boardCoordY, BLOCK_SIZE, BLOCK_SIZE, 0);
       // }
    }
    
    

    public int getBoardCoordX() {
        return boardCoordX;
    }
    public int getBoardCoordY() {
        return boardCoordY;
    }
    
   

    
    
    
}
