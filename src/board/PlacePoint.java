package board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import main.MouseListen;

public class PlacePoint extends GameObject {
	private int size;
	private boolean isVisible;
	private boolean isClicked;
	private Rectangle bounds;

	public PlacePoint(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
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

	// Strange methods
	public boolean removeSelf(ArrayList<PlacePoint> arrayList) {
		return arrayList.remove(this);
	}

	public void isClicked(MouseListen listen) {

	}

	// Getters and Setters
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
