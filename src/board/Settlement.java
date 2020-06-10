package board;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.ImageHandler;
import player.Player;

public class Settlement extends GameObject {
	private Player owner;
	private Rectangle bounds;
	private BufferedImage img;
	private PlacePoint originPoint;

	// Constructors
	public Settlement(int x, int y, Player owner, PlacePoint originPoint) {
		this.x = x;
		this.y = y;
		this.owner = owner;
		this.originPoint = originPoint;
		this.bounds = new Rectangle(10, 10);
		this.img = ImageHandler.loadImage("/images/settlement.png");
	}

	// Built-In
	@Override
	void render(Graphics graphics) {
		graphics.drawImage(this.img, x - 10, y - 15, this.img.getWidth() / 12, this.img.getHeight() / 12, null);
	}

	@Override
	void update() {

	}

	// Methods
	public String toString() {
		return "P(Name): " + this.owner.getName() + " X: " + this.x + " Y: " + this.y;
	}

	// Getters and Setters
	public PlacePoint getOriginPoint() {
		return this.originPoint;
	}
	
	public Player getOwner() {
		return this.owner;
	}

	public ArrayList<Hex> getSurroundingHexes() {
		return this.originPoint.getSurroundingHexes();
	}

	public BufferedImage getImage() {
		return this.img;
	}

}
