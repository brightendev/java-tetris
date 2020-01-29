/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.engine;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Skulsinlapakit
 */
public abstract class Render {
    
    private static java.awt.image.BufferedImage blockTexture;        //store Buffered Image of block's texture
    private static java.awt.image.BufferedImage blockRendered;       //store
    private static final int SIZE_OF_BLOCK_TEXTURE = 75;
    

    private static void mapTexture() {
        
        try {
            blockTexture = javax.imageio.ImageIO.read(Render.class.getResource("/textures/block-linear.png"));
        } catch (IOException ex) {
            Logger.getLogger(Render.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public static void drawBlock(java.awt.Graphics g, int[][] block, int coordX, int coordY, 
            int width, int height, int valueToDraw) {
        
        mapTexture();
        
        for(int row = 0; row < block.length; row++) {              
            for(int col = 0; col < block[row].length; col++) {      

                if(block[row][col] >= valueToDraw) {
                    
                    blockRendered = blockTexture.getSubimage(block[row][col]*SIZE_OF_BLOCK_TEXTURE, 
                            0, SIZE_OF_BLOCK_TEXTURE, SIZE_OF_BLOCK_TEXTURE);
                    g.drawImage(blockRendered, coordX+col*width, coordY+row*height, width, height, null);
                }
            }
        }
    }
    
    
    public static void drawScore(java.awt.Graphics g, int score, int coordX, int coordY) {

        g.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 22));
     
        g.setColor(java.awt.Color.WHITE);
    
        int width = 5;
        int stringLen = (int)g.getFontMetrics().getStringBounds(score+"", g).getWidth();
        int start = width/2 - stringLen/2;
        
        g.drawString(score+"", start+coordX, coordY);
        
    }
    

}
