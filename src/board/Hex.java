package board;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.ImageHandler;

/*
 * Hex type reference:
 * 0 - Desert
 * 1 - Bricks
 * 2 - Ore
 * 3 - Sheep
 * 4 - Timber
 * 5 - Wheat
 */

public class Hex extends GameObject {
	// Hex Type
	private int type;
	private int number;
	private BufferedImage image;

	// Some stuff for indetifying type
	public static final int DESERT = 0, BRICK = 1, ORE = 2, SHEEP = 3, TIMBER = 4, WHEAT = 5;

	public Hex(int type) {
		this.type = type;
		this.number = 0;
	}

	public Hex(int type, int number) {
		this.type = type;
		this.number = number;
	}

	public Hex(int x, int y, int type, int number) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.number = number;
		this.image = ImageHandler.loadImage("/images/hex.png");
	}

	public Hex(int x, int y, int type, int number, BufferedImage image) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.number = number;
		this.image = image;
	}

	@Override
	void render(Graphics graphics) {
		// The divide by 4 is to make it smaller
		graphics.drawImage(image, x, y, image.getWidth() / 4, image.getHeight() / 4, null);
	}

	@Override
	void update() {

	}

	// Methods
	public String toString() {
		return "T: " + this.type + " N:" + this.number;
	}

	// Getters and Setters
	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public BufferedImage getImage() {
		return this.image;
	}

	public void setImage(BufferedImage img) {
		this.image = img;
	}

}
