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
	private BufferedImage image;

	public Hex(int type) {
		this.type = type;
		ImageHandler imageHandle = new ImageHandler();
		this.image = imageHandle.loadImage("/images/hex.png");
	}

	@Override
	void render(Graphics graphics) {
		// graphics.drawImage(this.image, this.x, this.y, null);
		graphics.drawImage(image, x, y, image.getWidth() / 4, image.getHeight() / 4, null);
	}

	@Override
	void update() {

	}

	public String toString() {
		return "T: " + this.type;
	}

	public BufferedImage getImage() {
		return this.image;
	}

	public void setImage(BufferedImage img) {
		this.image = img;
	}

}
