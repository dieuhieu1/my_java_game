package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entity.Player;
import static utilz.Constants.Directions.*;

public class KeyHandler implements KeyListener {
    private Player player;

    public KeyHandler(Player player) {
        this.player = player;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W:
                player.setDirection(UP);
                player.setMoving(true);
                break;
            case KeyEvent.VK_S:
                player.setDirection(DOWN);
                player.setMoving(true);
                break;
            case KeyEvent.VK_A:
                player.setDirection(LEFT);
                player.setMoving(true);
                break;
            case KeyEvent.VK_D:
                player.setDirection(RIGHT);
                player.setMoving(true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_S ||
            code == KeyEvent.VK_A || code == KeyEvent.VK_D) {
            player.setMoving(false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Không sử dụng
    }
}
