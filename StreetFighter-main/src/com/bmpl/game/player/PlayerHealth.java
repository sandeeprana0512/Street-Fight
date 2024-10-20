package com.bmpl.game.player;

import javax.swing.*;
import java.awt.*;

public class PlayerHealth extends JPanel {
     public void printHealth(Graphics p,int x,String name,int health){
        p.drawRect(x,0,400,40);
        p.setColor(Color.RED);
        p.fillRect(x,0,400,40);
        p.setColor(Color.GREEN);
        p.fillRect(x,0,health,40);
        p.drawString(name,x,60);
        p.setColor(Color.BLACK);
    }
}
