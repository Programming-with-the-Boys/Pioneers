package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import player.Player;

public class GUI {
	BufferedImage area;
	private int x, y, width, height;
	private Color backgroundColor;
	private PlayerInfo info;
	private DiceInfo diceInfo;

	// Constructor
	public GUI(int x, int y, int width, int height) {
		area = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.backgroundColor = Color.WHITE;
	}

	// Obligatory Render
	public void render(Graphics g) {
		// Grab graphics of image
		Graphics imgGraphics = this.area.getGraphics();

		// Fill in background color
		imgGraphics.setColor(this.backgroundColor);
		imgGraphics.fillRect(0, 0, this.area.getWidth(), this.area.getHeight());

		// Render info
		this.info.render(imgGraphics);

		// Render last dice
		this.diceInfo.render(imgGraphics);

		// Draw modified image
		g.drawImage(this.area, this.x, this.y, null);
	}

	// Getters and Setters
	public void setPlayer(Player player) {
		this.info.setPlayer(player);
	}

	public void setInfo(PlayerInfo info) {
		this.info = info;
	}

	public void setDiceInfo(DiceInfo diceInfo) {
		this.diceInfo = diceInfo;
	}

	public DiceInfo getDiceInfo() {
		return this.diceInfo;
	}

	public Graphics getGraphics() {
		return this.area.getGraphics();
	}

	public void setBackgroundColor(Color c) {
		this.backgroundColor = c;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
		this.area = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
		this.area = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
