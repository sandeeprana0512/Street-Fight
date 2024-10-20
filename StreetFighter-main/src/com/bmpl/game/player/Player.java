package com.bmpl.game.player;

import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.bmpl.game.utils.Constants;

abstract class Player extends JPanel implements Constants {
    protected BufferedImage playerImg;

    public BufferedImage getPlayerImg() {
        return playerImg;
    }

    public void setPlayerImg(BufferedImage playerImg) {
        this.playerImg = playerImg;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    protected int x;
    protected int y;
    protected int w;
    protected int h;
    protected int speed;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    protected int health =  400;

    public boolean outOfScreen(char Direction,int width){
        if(Direction == 'L'){
            return this.getX() + this.getSpeed()<width;
        }
        else if(Direction == 'R'){
            return this.getX() + this.getSpeed() + this.getW()>width;
        }
        return false;
    }

    public void move() {
        x += speed;
    }

}
