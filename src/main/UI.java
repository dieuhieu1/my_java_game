package main;

import object.obj_duck;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp;
    Font arial_40, arial_80;
    BufferedImage image;

    public boolean gameFinnished = false;


    public  UI (GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN,40);
        arial_80 = new Font("Arial", Font.PLAIN,80);
        obj_duck duck = new obj_duck();
        image = duck.duckAni[0];
    }

    public void draw (Graphics2D g2) {

        if(gameFinnished == true) {

            String text ;
            int textLength;
            int x;
            int y;

            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);
            text = "đã nhặt đủ số vịt";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize)*2;
            g2.drawString(text, x, y);

            g2.setFont(arial_80);
            g2.setColor(Color.YELLOW);
            text = "Chúc Mừng";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize)*2;
            g2.drawString(text, x, y);

            gp.gameThread = null;
        }

        //hien thi so vit nhat dc
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        g2.drawImage(image, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
        g2.drawString( gp.player.numberKey + "/4", 90, 75);
    }
 }
