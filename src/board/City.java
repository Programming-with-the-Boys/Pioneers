package board;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.ImageHandler;
import player.Player;

public class City extends GameObject {
	private Player owner;
	private Rectangle bounds;
	private BufferedImage img;
	private PlacePoint originPoint;

	public City(int x, int y, Player owner, PlacePoint originPoint) {
		this.x = x;
		this.y = y;
		this.owner = owner;
		this.originPoint = originPoint;
		this.bounds = new Rectangle(10, 10);
		this.img = ImageHandler.loadImage("/images/city.png");
	}

	@Override
	void render(Graphics graphics) {
		graphics.drawImage(this.img, x - 10, y - 15, this.img.getWidth() / 12, this.img.getHeight() / 12, null);
	}

	@Override
	void update() {

	}
}
