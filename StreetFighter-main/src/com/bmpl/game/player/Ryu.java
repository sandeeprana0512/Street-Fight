package com.bmpl.game.player;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
public class Ryu extends Player {
    private BufferedImage idleImages[] = new BufferedImage[5];
    private BufferedImage walkImages[] = new BufferedImage[5];
    private BufferedImage punchImages[] = new BufferedImage[5];

    private int imageIndex;
    public int currentMove;
    public Ryu() throws IOException {
        x = 300;
        w = 150;
        h = 400;
        y = GROUND - h;
        speed = SPEED;
        imageIndex = 0;
        currentMove = IDLE;	// player in IDLE state
        playerImg = ImageIO.read(Ryu.class.getResource("ryu_player.png"));
        loadIdleImages();
        loadWalkImages();
        loadPunchImages();
    }

    private void loadIdleImages() {
        idleImages[0] = playerImg.getSubimage(5, 10, 64, 96);
        idleImages[1] = playerImg.getSubimage(73, 12, 61, 91);
        idleImages[2] = playerImg.getSubimage(141, 12, 61, 91);
        idleImages[3] = playerImg.getSubimage(210, 8, 56, 95);
        idleImages[4] = playerImg.getSubimage(276, 8, 60, 95);
    }

    public BufferedImage showIdle() {
        if(imageIndex > 4) {
            imageIndex = 0;
        }
        BufferedImage img = idleImages[imageIndex];
        imageIndex++;
        return img;
    }

    private void loadWalkImages() {
        walkImages[0] = playerImg.getSubimage(76, 128, 63, 92);
        walkImages[1] = playerImg.getSubimage(151, 128, 67, 92);
        walkImages[2] = playerImg.getSubimage(228, 128, 65, 92);
        walkImages[3] = playerImg.getSubimage(303, 127, 59, 93);
        walkImages[4] = playerImg.getSubimage(368, 127, 56, 93);
    }

    public BufferedImage showWalk() {
        if(imageIndex > 4) {
            imageIndex = 0;
            currentMove = IDLE;
        }
        BufferedImage img = walkImages[imageIndex];
        imageIndex++;
        return img;
    }

    public void loadPunchImages(){
        punchImages[0] = playerImg.getSubimage(5,466,61,95);
        punchImages[1] = playerImg.getSubimage(84,465,76,94);
        punchImages[2] = playerImg.getSubimage(375,464,58,94);
        punchImages[3] = playerImg.getSubimage(446,464,87,94);
        punchImages[4] = playerImg.getSubimage(174,466,110,94);

    }

    public BufferedImage showPunch(){
        if(imageIndex > 4){
            imageIndex = 0;
            currentMove = IDLE;
        }
        BufferedImage img = punchImages[imageIndex];
        imageIndex++;
        return img;
    }

    public void paintPlayer(Graphics pen) {
        pen.drawImage(defaultImage(), x, y, w, h, null);
    }

    public BufferedImage defaultImage() {
        if(currentMove == WALK) {
            return showWalk();
        } else if (currentMove == PUNCH) {
            return showPunch();
        } else {
            return showIdle();
        }

    }

}
