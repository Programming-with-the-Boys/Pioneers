package board;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class RoadPoint extends GameObject {
	private int size;
	private Rectangle bounds;
	
	private Color color = Color.GREEN;
	private boolean enabled;
	private boolean isVisible;

	private ArrayList<RoadPoint> surroundingRoadPoints;
	private ArrayList<PlacePoint> surroundingPlacePoints;
	
	// Temporary Workaround
	public int arbOffset = 3;

	public RoadPoint(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.enabled = true;
		this.isVisible = false;
		this.bounds = new Rectangle(size, size);
		this.surroundingRoadPoints = new ArrayList<RoadPoint>();
		this.surroundingPlacePoints = new ArrayList<PlacePoint>();
	}

	@Override
	void render(Graphics graphics) {
		graphics.setColor(this.color);
		if (this.isVisible) {
			graphics.fillOval(x - size + 15, y - size + 15, size, size);
		}
	}

	@Override
	void update() {

	}

	// Methods
	public boolean onObject(int x, int y) {
		int correctedX = this.x - size + arbOffset;
		int correctedY = this.y - size + arbOffset;
		if (x >= correctedX && x <= correctedX + size && y >= correctedY && y <= correctedY + size) {
			return true;
		}
		return false;
	}

	// Getters and Setters
	public void setSurroundingRoadPoints(ArrayList<RoadPoint> surroundingRoadPoints) {
		this.surroundingRoadPoints = surroundingRoadPoints;
	}

	public ArrayList<RoadPoint> getSurroundingRoadPoints() {
		return surroundingRoadPoints;
	}

	public void setSurroundingPlacePoints(ArrayList<PlacePoint> surroundingPlacePoints) {
		this.surroundingPlacePoints = surroundingPlacePoints;
	}

	public ArrayList<PlacePoint> getSurroundingPlacePoints() {
		return surroundingPlacePoints;
	}

	public void setEnabled(boolean foo) {
		this.enabled = foo;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public int getSize() {
		return this.size;
	}

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
