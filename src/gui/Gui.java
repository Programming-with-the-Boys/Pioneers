package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Gui {
	BufferedImage area;
	private int width, height;
	private int x, y;

	public Gui(int width, int height, int x, int y) {
		area = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}

	public Graphics getGraphics() {
		return this.area.getGraphics();
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
