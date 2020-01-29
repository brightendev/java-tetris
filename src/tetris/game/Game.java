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
public abstract class Game {
    
    protected final int BLOCK_SIZE = 22;                        //size of each block
    protected final int HUDBLOCK_SIZE = 17;
    
    protected int boardCoordX, boardCoordY;
    
    protected static int[][][] matrixOfBoard;
    
    
    protected int[] score = new int[2];
    protected final int[] hudCoordX = {354, 380};
    protected final int[] hudCoordY = {115, 352};
    protected final int[] scoreCoordX = {390, 410};
    protected final int[] scoreCoordY = {237, 475};
    
    

    
}
