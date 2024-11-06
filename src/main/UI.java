package main;

import object.obj_duck;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GamePanel gp;
    Font arial_40, arial_80;
    public Font maruMonica;
    public int commandNum = 0;
    public int titleScreenState = 0; // 0 : Main Menu, 1 : the second screen
    BufferedImage image;
    Graphics2D g2;

    public boolean gameFinnished = false;


    public  UI (GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN,40);
        arial_80 = new Font("Arial", Font.PLAIN,80);
        try
        {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
        }
        catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        obj_duck duck = new obj_duck();
        image = duck.duckAni[0];
    }

    public void draw (Graphics2D g2) {

        this.g2 = g2;
        g2.setFont(maruMonica);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);  // Anti Aliasing // Smoothes the text
        g2.setColor(Color.white);

        //TITLE STATE
        if(gp.gameState == gp.titleState)
        {
            drawTitleScreen();
        }
        else {

            //hien thi so vit nhat dc
            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);
            g2.drawImage(image, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString(gp.player.numberKey + "/4", 90, 75);
        }
    }

    public int getXforCenteredText(String text)
    {
        int textLenght;
        textLenght = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth(); // Gets width of text.
        int x = gp.screenWidth / 2 - textLenght/2;
        return x;
    }

    public void drawTitleScreen()
    {
        g2.setColor(new Color(0,0,0));             // FILL BACKGROUND BLACK
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
        //MAIN MENU
        if(titleScreenState == 0)
        {

            //TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String text = "Duck Collector\n";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;
            //SHADOW
            g2.setColor(Color.gray);
            g2.drawString(text,x+5,y+5);
            //MAIN COLOR
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            // IMAGE
            x = gp.screenWidth/2 - (gp.tileSize * 2) / 2;
            y += gp.tileSize*2;
            g2.drawImage(image,x,y,gp.tileSize*2,gp.tileSize*2,null);

            //MENU
//            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
            g2.setFont(arial_40);

            text = "GAME MỚI";
            x = getXforCenteredText(text);
            y += gp.tileSize * 3.5;
            g2.drawString(text,x,y);
            if(commandNum == 0)
            {
                g2.drawString(">",x - gp.tileSize, y);
            }


            text = "THOÁT";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text,x,y);
            if(commandNum == 1)
            {
                g2.drawString(">",x - gp.tileSize, y);
            }
        }
        //SECOND SCREEN
        else if(titleScreenState == 1)
        {

            g2.setColor(Color.white);
            g2.setFont(arial_40);

            String text = "nhiệm vụ của bạn là tìm kiếm đủ số vịt vàng !";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;
            g2.drawString(text,x,y);


            text = "ENTER";
            x = getXforCenteredText(text);
            y += gp.tileSize * 2;
            g2.drawString(text,x,y);
            if(commandNum == 0)
            {
                g2.drawString(">",x-gp.tileSize,y);
            }
        }
    }
 }
