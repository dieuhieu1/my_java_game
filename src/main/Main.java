package main;

import javax.swing.*;


public class Main {

	public static JFrame window;
	public static void main(String[] args) {

		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Duck Collector");

		new Main().setIcon();

		GamePanel gamePanel = new GamePanel();

		window.add(gamePanel);
		window.pack();

		window.setLocationRelativeTo(null);
		window.setVisible(true);

		gamePanel.setUpGame();
		gamePanel.startGameThread();
	}

	public void setIcon()
	{
		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("icon/rubber-duck.png"));
		window.setIconImage(icon.getImage());
	}
	 
}
