/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.display;

import java.awt.Graphics;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Skulsinlapakit
 */
public class Background extends javax.swing.JPanel {
    private final int SCREEN_WIDTH = 800;
    private final int SCREEN_HEIGHT = 600;
    private java.awt.image.BufferedImage bgTexture;

    public Background() {

        try {
            bgTexture = javax.imageio.ImageIO.read(Background.class.getResource("/textures/background.png"));
        } catch (IOException ex) {
            Logger.getLogger(Background.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        initComponents();

    }
    
    private void initComponents() {
        java.awt.Dimension resolution = new java.awt.Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
        setPreferredSize(resolution);       
            
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(bgTexture, 0, 0, 800, 600, null);
    }

    
}
