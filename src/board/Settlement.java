package board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import player.Player;

public class Settlement extends GameObject {
	private Player owner;
	private Rectangle bounds;
	private BufferedImage img;
	private PlacePoint originPoint;

	public Settlement(int x, int y, Player owner, PlacePoint originPoint) {
		this.x = x;
		this.y = y;
		this.owner = owner;
		this.originPoint = originPoint;
		this.bounds = new Rectangle(10, 10);
	}

	@Override
	void render(Graphics graphics) {
		graphics.setColor(Color.CYAN);
		graphics.fillRect(x - 10 + 15, y - 10 + 15, 10, 10);
	}

	@Override
	void update() {

	}

}
