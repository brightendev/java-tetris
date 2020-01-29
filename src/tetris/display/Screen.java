/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.display;

import java.awt.event.KeyEvent;

/**
 *
 * @author Skulsinlapakit
 */
public class Screen extends javax.swing.JPanel {
    
    private final int SC_WIDTH = 800;
    private final int SC_HEIGHT = 600;
    private tetris.game.Board boardP1;
    private tetris.game.Board boardP2;
    private tetris.game.Tetromino tetrominoP1;
    private tetris.game.Tetromino tetrominoP2;
    private java.awt.event.KeyListener controllerP1;
    private java.awt.event.KeyListener controllerP2;
 //   private javax.swing.Timer timerFps;
  //  private javax.swing.Timer timerFallP1;
    private java.util.Timer timerFps;
    private java.util.Timer timerFallP1;
    private java.util.Timer timerFallP2;
    private java.util.TimerTask taskFallP1;
    private java.util.TimerTask taskFallP2;
  //  private final int FPS = (int)(1000/30);
    private int fps = (int)(1000/30);
//    private final int FALL_RATE = 300;
    private int fallRate = 300;     //in milli second
    private int player;
    
    public Screen(int player) {

        this.player = player;
        initComponents();

        boardP1 = new tetris.game.Board(40, 47, 0);
        tetrominoP1 = new tetris.game.Tetromino(boardP1.getBoardCoordX(), boardP1.getBoardCoordY(), 0);
        
        timerFps = new java.util.Timer();
        timerFallP1 = new java.util.Timer();
        
        timerFps.scheduleAtFixedRate(new java.util.TimerTask() {
            @Override
            public void run() {
                revalidate();
                repaint();
            }
        }, 0, fps);
        
        timerFallP1.scheduleAtFixedRate(new java.util.TimerTask() {
            @Override
            public void run() {

                tetrominoP1.fall();
                
            }
        }, 0, fallRate);
        
        controllerP1 = initController(1);
        this.addKeyListener(controllerP1);
        
        if(player == 2) {
            boardP2 = new tetris.game.Board(453, 47, 1);
            
            tetrominoP2 = new tetris.game.Tetromino(boardP2.getBoardCoordX(), boardP2.getBoardCoordY(), 1);
            
            timerFallP2 = new java.util.Timer();
            
            timerFallP2.scheduleAtFixedRate(new java.util.TimerTask() {
                @Override
                public void run() {
                    tetrominoP2.fall();
                }
            }, 0, fallRate);
            
            controllerP2 = initController(2);
            this.addKeyListener(controllerP2);
        }

        setFocusable(true);
    }

    private void initComponents() {
        java.awt.Dimension resolution = new java.awt.Dimension(SC_WIDTH, SC_HEIGHT);
        setPreferredSize(resolution);       

    }
    
    @Override
    public void paint(java.awt.Graphics g) {
        super.paint(g);             //  for best repaint
        
        //BG.draw(g);
        boardP1.draw(g);
        
        tetrominoP1.draw(g);
        //tetrominoP1.drawNextTet(g);
        
       // tetrominoP2.drawNextTet(g);;
        
        if(player == 2) {
            boardP2.draw(g);
            tetrominoP2.draw(g);
            
        }
    }
    
    private java.awt.event.KeyListener initController(int player) {
        java.awt.event.KeyListener controller;
        
        if(player == 1) {
            //ActionListener is an abstract class cannot be instantiated
            controller = new java.awt.event.KeyListener() {
                
                boolean speedPressed = false;
                
                @Override
                public void keyTyped(java.awt.event.KeyEvent e) {
                   // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
                @Override
                public void keyPressed(java.awt.event.KeyEvent e) {
                    int keyCode = e.getKeyCode();
                    switch(keyCode) {
                        case KeyEvent.VK_D :
                            tetrominoP1.moveRight();
                            break;
                        case KeyEvent.VK_A :
                            tetrominoP1.moveLeft();
                            break;
                        case KeyEvent.VK_W :
                            tetrominoP1.rotate();
                            break;
                        case KeyEvent.VK_S :
                            speedPressed = true;
                            speed(1);
                    }
                }
                @Override
                public void keyReleased(java.awt.event.KeyEvent e) {
                   int keyCode = e.getKeyCode();
                    switch(keyCode) {
                        case KeyEvent.VK_S :
                            if(speedPressed) {
                                pause();
                                run();
                                speedPressed = false;
                            }
                    }
                }
            };
        }
        
        else {
            
            controller = new java.awt.event.KeyListener() {
                
                boolean speedPressed = false;
                
                @Override
                public void keyTyped(KeyEvent e) {
          //          throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    int keyCode = e.getKeyCode();
                    switch(keyCode) {
                        case KeyEvent.VK_RIGHT :
                            tetrominoP2.moveRight();
                            break;
                        case KeyEvent.VK_LEFT :
                            tetrominoP2.moveLeft();
                            break;
                        case KeyEvent.VK_UP :
                            tetrominoP2.rotate();
                            break;
                        case KeyEvent.VK_DOWN :
                            speedPressed = true;
                            speed(2);
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    int keyCode = e.getKeyCode();
                    switch(keyCode) {
                        case KeyEvent.VK_DOWN :
                            if(speedPressed) {
                                pause();
                                run();
                                speedPressed = false;
                            }
                    }
                }
            };
        }

        return controller;
    }
    
    public void pause() {
        timerFallP1.cancel();
        timerFps.cancel();
        if(player == 2) timerFallP2.cancel();
    }
    
    public void run() {
        
        timerFps = new java.util.Timer();
        timerFps.scheduleAtFixedRate(new java.util.TimerTask() {
            @Override
            public void run() {
                revalidate();
                repaint();
            }
        }, 0, fps);
        timerFallP1 = new java.util.Timer();
        timerFallP1.scheduleAtFixedRate(new java.util.TimerTask() {
            @Override
            public void run() {
                tetrominoP1.fall();
            }
        }, 0, fallRate);
        
        if(player == 2) {
            timerFallP2 = new java.util.Timer();
            timerFallP2.scheduleAtFixedRate(new java.util.TimerTask() {
                @Override
                public void run() {
                    tetrominoP2.fall();
                }
            }, 0, fallRate);
        }
        
    }
    
    private void speed(int player) {
            
        int speed = 20;
        
        if(player == 1) {
            timerFallP1.scheduleAtFixedRate(new java.util.TimerTask() {
                @Override
                public void run() {
                    tetrominoP1.fall();
                }
            }, 0, speed);
        }
        else {
            timerFallP2.scheduleAtFixedRate(new java.util.TimerTask() {
                @Override
                public void run() {
                    tetrominoP2.fall();
                }
            }, 0, speed);
        }
    }
    
    public void reset() {
        
    }
    
    public void setting(int fps, int fallRate) {
        this.fps = fps;
        this.fallRate = fallRate;
        
        pause();
        run();
        
    }
    
    
    
}
