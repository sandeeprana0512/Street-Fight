package com.bmpl.game.player;

        import java.awt.Graphics;
        import java.awt.event.KeyEvent;
        import java.awt.event.KeyListener;
        import java.awt.image.BufferedImage;
        import java.io.IOException;

        import javax.imageio.ImageIO;
        import javax.swing.JPanel;

        import com.bmpl.game.board.GameScreen;
        import com.bmpl.game.utils.Constants;

public class Ken extends Player {
    private BufferedImage idleImages[] = new BufferedImage[5];
    private BufferedImage walkImages[] = new BufferedImage[5];
    private BufferedImage punchImages[] = new BufferedImage[5];
    private int imageIndex;
    public int currentMove;
    public Ken() throws IOException {
        x = SCREENWIDTH-300;
        w = 123;
        h = 400;
        y = GROUND - h;
        speed = SPEED;
        imageIndex = 0;
        currentMove = IDLE;	// player in IDLE state
        setPlayerImg(ImageIO.read(Ken.class.getResource("ken_flip.png")));
        loadIdleImages();
        loadWalkImages();
        loadPunchImages();
    }


    private void loadIdleImages() {
        idleImages[0] = playerImg.getSubimage(668, 246, 120, 243);
        idleImages[1] = playerImg.getSubimage(887, 244, 120, 243);
        idleImages[2] = playerImg.getSubimage(1114, 241, 120, 243);
        idleImages[3] = playerImg.getSubimage(887, 489, 120, 243);
    }

    public BufferedImage showIdle() {
        if(imageIndex > 3) {
            imageIndex = 0;
        }
        BufferedImage img = idleImages[imageIndex];
        imageIndex++;
        return img;
    }

    private void loadWalkImages() {
        walkImages[0] = playerImg.getSubimage(675, 734, 123, 242);
        walkImages[1] = playerImg.getSubimage(1118, 731, 123, 242);
        walkImages[2] = playerImg.getSubimage(1342, 735, 123, 242);
    }

    public BufferedImage showWalk() {
        if(imageIndex > 2) {
            imageIndex = 0;
            currentMove = IDLE;
        }
        BufferedImage img = walkImages[imageIndex];
        imageIndex++;
        return img;
    }

    private void loadPunchImages(){
        punchImages[0] = playerImg.getSubimage(886,490,124,242);
        punchImages[1] = playerImg.getSubimage(1086,16,170,218);
        punchImages[2] = playerImg.getSubimage(1063,489,174,237);
        punchImages[3] = playerImg.getSubimage(1063,489,174,237);
        punchImages[4] = playerImg.getSubimage(886,490,124,242);
    }

    public BufferedImage showPunch() {
        if(imageIndex > 4) {
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
