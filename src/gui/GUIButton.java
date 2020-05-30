package gui;

import java.awt.image.BufferedImage;

public class GUIButton {
	private int x, y, width, height;
	private BufferedImage image;

	// Constructors
	public GUIButton(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public GUIButton(int x, int y, BufferedImage image) {
		this.x = x;
		this.y = y;
		this.image = image;
		this.width = image.getWidth();
		this.height = image.getHeight();
	}

	// Methods
	public boolean onObject(int x, int y) {
		int correctedX = this.x - image.getWidth() + 15;
		int correctedY = this.y - image.getHeight() + 15;
		if (x >= correctedX && x <= correctedX + image.getWidth() && y >= correctedY
				&& y <= correctedY + image.getHeight()) {
			return true;
		}
		return false;
	}

	// Getters and Setters
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

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
		this.height = image.getHeight();
		this.width = image.getWidth();
	}

}
