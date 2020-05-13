package board;

import java.awt.Graphics;

public abstract class GameObject {
	int x = 0, y = 0;

	abstract void render(Graphics graphics);

	abstract void update();

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
