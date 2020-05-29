package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import player.Player;

public class PlayerInfo {
	int x, y, width, height;
	Player currentPlayer;

	public PlayerInfo(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void render(Graphics g) {
		if (this.currentPlayer != null) {
			g.setColor(Color.BLACK);
			g.setFont(new Font("Corbel", Font.PLAIN, 40));
			g.drawString(this.currentPlayer.getName(), this.width / 2 - this.currentPlayer.getName().length() * 15, 30);
			g.drawString("VPs: " + this.currentPlayer.getVictoryPoints(), 10, 100);
			g.drawString("Resources", 10, 200);

			g.drawString("Bricks", x + 200, 150);
			g.drawString("" + this.currentPlayer.getResources()[0], x + 200, 200);

			g.drawString("Ore", x + 350, 150);
			g.drawString("" + this.currentPlayer.getResources()[1], x + 350, 200);

			g.drawString("Wool", x + 450, 150);
			g.drawString("" + this.currentPlayer.getResources()[2], x + 450, 200);

			g.drawString("Timber", x + 570, 150);
			g.drawString("" + this.currentPlayer.getResources()[3], x + 570, 200);

			g.drawString("Wheat", x + 720, 150);
			g.drawString("" + this.currentPlayer.getResources()[4], x + 720, 200);
		}

	}

	// Getters and Setters
	public void setPlayer(Player player) {
		this.currentPlayer = player;
	}

}
