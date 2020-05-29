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
			g.setColor(Color.GREEN);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
			g.drawString("Name: " + this.currentPlayer.getName(), 10, 50);
			g.drawString("Victory Points: " + this.currentPlayer.getVictoryPoints(), 10, 100);
		}

	}

	// Getters and Setters
	public void setPlayer(Player player) {
		this.currentPlayer = player;
	}

}
