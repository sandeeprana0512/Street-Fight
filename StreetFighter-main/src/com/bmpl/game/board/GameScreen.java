package com.bmpl.game.board;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.bmpl.game.player.Ken;
import com.bmpl.game.player.PlayerHealth;
import com.bmpl.game.player.Ryu;
import com.bmpl.game.utils.Constants;

public class GameScreen extends JPanel implements Constants {
    BufferedImage imageBg;
    Ryu ryu;
    Ken ken;
    PlayerHealth pHealth;
    Timer timer;
    public GameScreen() throws IOException {
        ryu = new Ryu();
        ken = new Ken();
        pHealth = new PlayerHealth();
        setFocusable(true);
        bindEvents();
        loadBackground();
        gameLoop();
    }

    // in every 200 millisecond repaint the screen
    private void gameLoop() {
        timer = new Timer(200, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.start();
    }

//    private boolean isCollide(int s1,int s2,int j1,int j2){
//        double xCo = (ryu.getX() + s1) - (ken.getX() + s2);
//        double yCo = (ryu.getY() + j1) - (ken.getY() + j2);
//        double dist = Math.sqrt(Math.pow(xCo,2)+Math.pow(yCo,2));
//        return dist == 0;
//    }
    private boolean  isCollide(){
        int xCo = Math.abs(ryu.getX()-ken.getX());
        int yCo = Math.abs(ryu.getY()- ken.getY());
        int minH = Math.min(ryu.getH(), ken.getH());
        int minW = Math.min(ryu.getW(), ken.getW());
        return xCo<=minW && yCo<=minH;
    }
    void bindEvents() {
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub
//				System.out.println("Key Typed : " + e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub
//				System.out.println("Key Released : " + e.getKeyCode());
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub
//				System.out.println("Key Pressed : " + e.getKeyCode());
                if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    ryu.setSpeed(SPEED);
//                    int moveCoordinate = ryu.getSpeed() + ryu.getW();
                    if((isCollide() && ryu.getX()<ken.getX()) || ryu.outOfScreen('R',getWidth())){
                        return;
                    }
                    ryu.move();
                    ryu.currentMove = WALK;
//					repaint();
                }
                else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                    ryu.setSpeed(-SPEED);
//                    int moveCoordinate = ryu.getSpeed();
                    if((isCollide() && ryu.getX()>ken.getX()) || ryu.outOfScreen('L',0)){
                        return;
                    }
                    ryu.move();
                    ryu.currentMove = WALK;
//					repaint();
                }
                else if(e.getKeyCode() == KeyEvent.VK_A) {
                    ken.setSpeed(-SPEED);
//                    int moveCoordinate = ken.getSpeed();
                    if((isCollide() && ken.getX()>ryu.getX()) || ken.outOfScreen('L',0)){
                        return;
                    }
                    ken.move();
                    ken.currentMove = WALK;
//					repaint();
                }
                else if(e.getKeyCode() == KeyEvent.VK_D) {
                    ken.setSpeed(SPEED);
//                    int moveCoordinate  = ken.getSpeed() + ken.getW();
                    if((isCollide() && ken.getX()<ryu.getX()) || ken.outOfScreen('R',getWidth())){
                        return;
                    }
                    ken.move();
                    ken.currentMove = WALK;
//					repaint();
                }
                else if(e.getKeyCode() == KeyEvent.VK_UP){
                    ryu.currentMove = PUNCH;
                    if(isCollide() && ryu.getX()<ken.getX()){
                        ken.setHealth(ken.getHealth()-10);
                    }
                    repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_W) {
                    ken.currentMove = PUNCH;
                    if(isCollide() && ryu.getX()<ken.getX()){
                        ryu.setHealth(ryu.getHealth()-10);
                    }
                    repaint();
                }
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        showBackground(g);
        ryu.paintPlayer(g);
        ken.paintPlayer(g);
        pHealth.printHealth(g,0,"Ryu",ryu.getHealth());
        pHealth.printHealth(g,SCREENWIDTH-400,"Ken",ken.getHealth());
    }

    private void showBackground(Graphics pen) {
        // x,y,w,h
        pen.drawImage(imageBg, 0, 0, SCREENWIDTH, SCREENHEIGHT, null);
    }
    private void loadBackground() {
        try {
            imageBg = ImageIO.read(GameScreen.class.getResource(BACKGROUND_IMAGE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
