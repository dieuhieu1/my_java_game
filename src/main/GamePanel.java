package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import entity.Player;
import object.MainObject;

import static utilz.Constants.Directions.*;
import utilz.TileManager;

public class GamePanel extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 1L;
	private int aniTick, aniIndex, aniSpeed = 20;
	private int currentDirection = -1; 
	// 4 animations/ 1s -> speed = 120 / 4 = 30
	// 1/30s sẽ reset vẽ lại animations
	final int originalTileSize = 64; // 16x16 pixels character
	
	public final int tileSize = originalTileSize; // 64px x 64px
	
	public final int maxScreenCol = 16; // 16 pixel cột
	public final int maxScreenRow = 12; // 12 pixel hàng
	public final int screenWidth = tileSize * maxScreenCol; 
	public final int screenHeight = tileSize * maxScreenRow;  // Màn hình 1024px x 768px 
	
	// WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWitdh = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldCol;
	
	// FPS
	int FPS = 80;
	TileManager tileM = new TileManager(this);
	public CollisionChecker collisionChecker = new CollisionChecker(this);
	public Player player = new Player(this);
	KeyHandler keyH = new KeyHandler(player);
	Thread gameThread;
	public MainObject obj[]	= new MainObject[1];
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		// Focus vào cửa sổ trò chơi để có thể bắt các sự kiện nhập từ phím
		this.setFocusable(true);
		
	}
	
	public void startGameThread() {
		// Truyền vào gamePanel để khởi tại và chạy một luồng mới
		gameThread = new Thread(this);
		gameThread.start();
	}
	// Khi gameThread được start thì hàm Run được gọi tự động
	// Tạo ra một Thread (Luồng) mới và bắt đầu chạy
	@Override
	public void run() {
		// Tạo độ trễ cho việc draw lại thay vì thực hiện liên tục
		double drawInterval = 1000000000/FPS; // 0.016667 second
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
	
		// Nếu gameThread được khởi tạo
		
		while(gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			//Phép chia giúp xác định số lượng bước cập nhật logic và vẽ cần thực hiện  
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			if (delta >= 1) {
				// 1. Update logic game
	            update();
	            // 2. Vẽ lại giao diện
	            repaint();
				delta--;
				drawCount++;
			}
			if (timer >= 1000000000) {
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}
	
	private void updateAnimationTick(BufferedImage[] ani) {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;

			System.out.println(aniIndex);
			if (aniIndex >= ani.length) {
				aniIndex = 0;
			}
		}
	}

	public void update() {
		player.update();
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		tileM.draw(g2);
		
		if (player.moving) {
            // Nếu hướng đã thay đổi, reset lại chỉ số hoạt ảnh
            if (player.getDirection() != currentDirection) {
                aniIndex = 0;  // Reset lại hoạt ảnh khi đổi hướng
                currentDirection = player.getDirection(); // Cập nhật hướng hiện tại
            }

            // Vẽ các hoạt ảnh tương ứng với hướng di chuyển
            switch (player.getDirection()) {
                case UP:
                    updateAnimationTick(player.upAni);
                    player.draw(g2, player.upAni[aniIndex]);
                    break;
                case LEFT:
                    updateAnimationTick(player.leftAni);
                    player.draw(g2, player.leftAni[aniIndex]);
                    break;
                case RIGHT:
                    updateAnimationTick(player.rightAni);
                    player.draw(g2, player.rightAni[aniIndex]);
                    break;
                case DOWN:
                    updateAnimationTick(player.downAni);
                    player.draw(g2, player.downAni[aniIndex]);
                    break;
            }
        } else {
            // Khi không di chuyển, giữ nguyên khung hình đầu tiên của hoạt ảnh
            player.draw(g2, player.img.getSubimage(0 * 56, 0 * 64, 56, 64));
        }

	
		g2.dispose();
	}
}