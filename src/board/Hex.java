package board;

import java.awt.Graphics;

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

	public Hex(int type) {
		this.type = type;
	}

	@Override
	void render(Graphics graphics) {

	}

	@Override
	void update() {

	}

	public String toString() {
		return "T: " + this.type;
	}

}
