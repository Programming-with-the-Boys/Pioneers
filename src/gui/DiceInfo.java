package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import player.Player;

public class DiceInfo {
	int x, y;
	Integer lastDiceRoll;

	public DiceInfo(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void render(Graphics g) {
		if (this.lastDiceRoll != null) {
			g.setColor(Color.BLACK);
			g.setFont(new Font("Corbel", Font.PLAIN, 35));
			g.drawString("Dice Roll: ", this.x, this.y);
			g.drawString("" + this.lastDiceRoll, x + 60, y + 30);
		}

	}
	
	public void setLastRoll(int num) {
		this.lastDiceRoll = num;
	}
}
