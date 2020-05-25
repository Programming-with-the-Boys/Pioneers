package board;

import java.awt.Color;
import java.awt.Graphics;

public class PlacePoint extends GameObject {

	public PlacePoint(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	void render(Graphics graphics) {
		graphics.setColor(Color.RED);
		graphics.fillRect(x, y, 8, 8);
	}

	@Override
	void update() {

	}

}
