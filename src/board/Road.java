package board;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import main.ImageHandler;
import player.Player;

public class Road extends GameObject {
	private BufferedImage img;
	private RoadPoint originPoint;
	private Player owner;

	public Road(RoadPoint roadPoint, Player owner) {
		this.originPoint = roadPoint;
		this.owner = owner;

		this.x = roadPoint.getX();
		this.y = roadPoint.getY();
		this.img = ImageHandler.loadImage("/images/road.png");
	}

	public Road(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	void render(Graphics graphics) {
		Graphics2D g2 = (Graphics2D) graphics;

		g2.setColor(Color.RED);

		g2.setStroke(new BasicStroke(5));

		g2.draw(new Line2D.Float(x - 8, y - 15, x + 22, y + 35));
	}

	@Override
	void update() {

	}

	// Getters and Setters
	public BufferedImage getImage() {
		return this.img;
	}

	public void setImage(BufferedImage image) {
		this.img = image;
	}

}
