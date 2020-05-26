package board;

import java.awt.Color;
import java.awt.Graphics;

public class PlacePoint extends GameObject {
	private int size;
	private boolean isVisible;

	public PlacePoint(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.isVisible = false;
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

	// Getters and Setters
	public void setVisible(boolean b) {
		this.isVisible = b;
	}

	public boolean getVisible() {
		return this.isVisible;
	}

}
