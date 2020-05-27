package board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import main.MouseEventListener;

public class PlacePoint extends GameObject {
	private int size;
	private boolean isVisible;
	private boolean enabled;
	private Rectangle bounds;

	public PlacePoint(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.enabled = true;
		this.isVisible = false;
		this.bounds = new Rectangle(size, size);
	}

	@Override
	void render(Graphics graphics) {
		graphics.setColor(Color.RED);
		if (this.isVisible) {
			graphics.fillOval(x - size + 15, y - size + 15, size, size);
		}
	}

	@Override
	void update() {

	}

	// Methods
	public boolean onObject(int x, int y) {
		int correctedX = this.x - size + 15;
		int correctedY = this.y - size + 15;
		if (x >= correctedX && x <= correctedX + size && y >= correctedY && y <= correctedY + size) {
			return true;
		}
		return false;
	}

	public String toString() {
		return this.x + ", " + this.y;
	}

	// Getters and Setters
	public void setEnabled(boolean foo) {
		this.enabled = foo;
	}
	
	public boolean isEnabled() {
		return this.enabled;
	}
	
	public int getSize() {
		return this.size;
	}

	public Rectangle getBounds() {
		return this.bounds;
	}

	public void setVisible(boolean b) {
		this.isVisible = b;
	}

	public boolean getVisible() {
		return this.isVisible;
	}

}
