package main;
import java.awt.*;
public class CutsceneManager {
    GamePanel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase;
    int counter = 0;
    float alpha = 0f;
    int y;
    String endCredit;
    //Scene Number
    public final int NA = 0;
    public final int ending = 2;
    public CutsceneManager(GamePanel gp)
    {
        this.gp = gp;
        endCredit =  "----------------\n"
                + "Developed by\n"
                + "Vu Tien Manh B22DCPT163\n"
                + "Dieu Chinh Hieu B22DCPT\n"
                + "----------------"
                + "\n\n\n\n\n\n\n\n\n\n\n"
                + "Bai Tap Lon\n\n"
                + "Mon Hoc : Ngon ngu lap trinh JAVA\n"
                + "\n\n\n\n\n\n\n"
                + "Thank you for playing!";
    }
    public void draw(Graphics2D g2)
    {
        this.g2 = g2;
        scene_ending();
    }
    public void scene_ending()
    {
//        if(scenePhase == 0)
//        {
//            gp.stopMusic();
//            gp.ui.npc = new OBJ_BlueHeart(gp);
//            scenePhase++;
//        }
//        if(scenePhase == 1)
//        {
//            //Display dialogues
//            gp.ui.drawDialogueScreen();
//        }
//        if(scenePhase == 2)
//        {
//            //Play the fanfare
//            gp.playSE(4);
//            scenePhase++;
//        }
        if(scenePhase == 0)
        {
            //Wait until the sound effect ends
            if(counterReached(100) == true) // 5 sec delay
            {
                scenePhase++;
            }
        }
        if(scenePhase == 1)
        {
            //The screen gets darker
            alpha += 0.05f;
            if (alpha > 1f){
                alpha = 1f;
            }
//            alpha = graduallyAlpha(alpha, 0.005f);
            drawBlackBackground(alpha);
            if(alpha == 1f)
            {
                alpha = 0;
                scenePhase++;
            }
        }
        if(scenePhase == 2)
        {
            drawBlackBackground(1f);
            //alpha = graduallyAlpha(alpha, 0.005f);
            alpha += 0.05f;
            if (alpha > 1f){
                alpha = 1f;
            }
            String text = "Chúc mừng bạn đã hoàn thành trò chơi\n";
            drawString(alpha, 38f, 200, text, 70);
<<<<<<< HEAD
            if(counterReached(400) == true)
=======

            if(counterReached(600) == true )
>>>>>>> 9fb9ac6192a28df7ff204919df925930979dcd49
            {
                alpha = 0;
                scenePhase++;
            }
        }
        if(scenePhase == 3)
        {
            drawBlackBackground(1f);
            drawString(1f,38f, gp.screenHeight/2, "Duck Collector", 40);
<<<<<<< HEAD
            System.out.println(alpha);
//            if(counterReached(480) == true && alpha == 1f)
//            {
//                scenePhase++;
//                alpha = 0;
//            }
            if(counterReached(480) == true) {
            	scenePhase++;
=======

            if(counterReached(480) == true)
            {
                scenePhase++;
                alpha = 0;
>>>>>>> 9fb9ac6192a28df7ff204919df925930979dcd49
            }
        }
        if(scenePhase == 4)
        {
            //First Credits
            drawBlackBackground(1f);
<<<<<<< HEAD
            alpha = graduallyAlpha(alpha, 0.01f);
=======

//            alpha = graduallyAlpha(alpha, 0.01f);

>>>>>>> 9fb9ac6192a28df7ff204919df925930979dcd49
            y = gp.screenHeight/2;
            drawString(alpha, 38f,  y, endCredit, 40);
<<<<<<< HEAD
            if(counterReached(240) == true && alpha == 1f)
=======

            if(counterReached(240) == true )
>>>>>>> 9fb9ac6192a28df7ff204919df925930979dcd49
            {
                scenePhase++;
//                alpha = 0;
            }
        }
        if(scenePhase == 5)
        {
            drawBlackBackground(1f);
            //Scrolling the credit
            y--;
            drawString(1f, 38f,  y, endCredit, 40);
            if(counterReached(1320) == true)
            {
                //Reset
                sceneNum = NA;
                scenePhase = 0;
                //Transition to game again
                gp.gameState = gp.titleState;
                gp.resetGame(true);
            }
        }
    }
    public boolean counterReached(int target)
    {
        boolean counterReached = false;
        counter++;
        if(counter > target)
        {
            counterReached = true;
            counter = 0;
        }
        return counterReached;
    }
    public void drawBlackBackground(float alpha)
    {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.black);
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
    public void drawString(float alpha, float fontSize, int y, String text, int lineHeight)
    {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(fontSize));
        for(String line: text.split("\n"))
        {
            int x = gp.ui.getXforCenteredText(line);
            g2.drawString(line, x, y);
            y += lineHeight;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
    public float graduallyAlpha(float alpha, float grade)
    {
        alpha += grade;  // after 200 frames alpha becomes 1
        if(alpha > 1f)
        {
            alpha = 1f;
        }
        return alpha;
    }
}