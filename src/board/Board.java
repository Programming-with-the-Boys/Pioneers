package board;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import main.ImageHandler;
import utils.Utils;

public class Board extends GameObject {
	// Setup storage for things
	private ArrayList<Hex> hexes;
	private ArrayList<PlacePoint> points;
	private ArrayList<RoadPoint> roadPoints;
	private ArrayList<Settlement> settlements;
	private ArrayList<City> cities;

	// Declare static variables
	// Positions of Hexes on the board
	private final double[][] hexPositions = { { 1.26, 0.00 }, { 0.63, 0.36 }, { 1.26, 0.71 }, { 1.89, 0.36 },
			{ 0.00, 0.71 }, { 0.63, 1.07 }, { 1.26, 1.42 }, { 1.89, 1.07 }, { 2.52, 0.71 }, { 0.00, 1.42 },
			{ 0.63, 1.78 }, { 1.26, 2.13 }, { 1.89, 1.78 }, { 2.52, 1.42 }, { 0.00, 2.13 }, { 0.63, 2.49 },
			{ 1.26, 2.84 }, { 1.89, 2.49 }, { 2.52, 2.13 } };
	// Positions of PlacePoints on the board
	private final double[][] pointPositions = { { 1.19, 0.29 }, { 2.00, 0.29 }, { 0.56, 0.65 }, { 1.40, 0.65 },
			{ 1.80, 0.65 }, { 2.66, 0.65 }, { 0.75, 1.01 }, { 1.19, 1.01 }, { 2.00, 1.01 }, { 2.44, 1.01 },
			{ 0.14, 1.36 }, { 0.56, 1.36 }, { 1.40, 1.36 }, { 1.80, 1.36 }, { 2.66, 1.36 }, { 3.05, 1.36 },
			{ 0.75, 1.70 }, { 1.19, 1.70 }, { 2.00, 1.70 }, { 2.44, 1.70 }, { 0.14, 2.06 }, { 0.56, 2.06 },
			{ 1.40, 2.06 }, { 1.80, 2.06 }, { 2.66, 2.06 }, { 3.05, 2.06 }, { 0.75, 2.42 }, { 1.19, 2.42 },
			{ 2.00, 2.42 }, { 2.44, 2.42 }, { 0.56, 2.77 }, { 1.40, 2.77 }, { 1.80, 2.77 }, { 2.66, 2.77 },
			{ 1.19, 3.13 }, { 2.00, 3.13 } };
	// Point correlation with Hexes on board
	private final int[][] pointCorrelations = { { 0, 1 }, { 0, 3 }, { 1, 4 }, { 1, 2, 0 }, { 2, 3, 0 }, { 3, 8 },
			{ 4, 5, 1 }, { 5, 2, 1 }, { 2, 7, 3 }, { 7, 8, 3 }, { 4, 9 }, { 9, 5, 4 }, { 5, 6, 2 }, { 6, 7, 2 },
			{ 7, 13, 8 }, { 8, 13 }, { 9, 10, 5 }, { 10, 6, 5 }, { 6, 12, 7 }, { 12, 13, 7 }, { 14, 9 }, { 14, 10, 9 },
			{ 10, 11, 6 }, { 11, 12, 6 }, { 12, 18, 13 }, { 13, 18 }, { 14, 15, 10 }, { 15, 11, 10 }, { 11, 17, 12 },
			{ 17, 18, 12 }, { 14, 15 }, { 15, 16, 11 }, { 16, 17, 11 }, { 17, 18 }, { 15, 16 }, { 16, 17 } };
	// Point correlation with surrounding points
	private final int[][] pointSurroundings = { { 3 }, { 4 }, { 6 }, { 0, 7, 4 }, { 1, 3, 8 }, { 9 }, { 2, 11, 7 },
			{ 3, 6, 12 }, { 4, 13, 9 }, { 5, 8, 14 }, { 11 }, { 6, 10, 16 }, { 7, 17, 13 }, { 8, 12, 18 },
			{ 9, 19, 15 }, { 14 }, { 11, 21, 17 }, { 12, 16, 22 }, { 13, 23, 19 }, { 14, 18, 24 }, { 21 },
			{ 16, 20, 26 }, { 17, 27, 23 }, { 18, 22, 28 }, { 19, 29, 25 }, { 24 }, { 21, 30, 27 }, { 22, 26, 31 },
			{ 23, 32, 29 }, { 24, 28, 33 }, { 26 }, { 27, 34, 32 }, { 31, 28, 35 }, { 29 }, { 31 }, { 32 } };

	public Board() {
		this.hexes = new ArrayList<Hex>();
		this.points = new ArrayList<PlacePoint>();
		this.roadPoints = new ArrayList<RoadPoint>();
		this.settlements = new ArrayList<Settlement>();
		this.cities = new ArrayList<City>();

		this.initializeBoard();
	}

	public void initializeBoard() {
		// Random for future use
		Random ran = new Random();

		// Setup the Hexes
		double scale = 164;
		int[] typeAmounts = { 0, 3, 3, 4, 4, 4 };
		ArrayList<Integer> numbers = Utils
				.intArraytoArrayList(new int[] { 2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12 });
		String[] fileNameKey = { "desert", "bricks", "ore", "sheep", "timber", "wheat" };

		for (int i = 0; i < this.hexPositions.length; i++) {
			if (i != 6) {
				// Get X and Y Coordinates
				int x = (int) (hexPositions[i][0] * scale) + 200;
				int y = (int) (hexPositions[i][1] * scale) + 50;

				// Get type
				int type = 0;
				int amount = typeAmounts[type];
				while (amount <= 0) {
					type = ran.nextInt(typeAmounts.length);
					amount = typeAmounts[type];
				}

				// Get Number
				Integer number = numbers.get(ran.nextInt(numbers.size()));

				// Get Image
				BufferedImage hexImage = ImageHandler.loadImage("/images/hex" + fileNameKey[type] + ".png");

				// Modify image with number
				Graphics imageGraphics = hexImage.getGraphics();

				imageGraphics.setColor(Color.ORANGE);
				imageGraphics.setFont(new Font("Carlito", Font.BOLD, 120));
				String drawString = "" + number;
				imageGraphics.drawString(drawString, hexImage.getWidth() / 2 - drawString.length() * 30,
						hexImage.getHeight() / 2 + 30);

				// Add the object
				hexes.add(new Hex(x, y, type, number, hexImage));

				// Clean up
				numbers.remove(number);
				typeAmounts[type]--;
			} else {
				// Get X and Y Coordinates
				int x = (int) (hexPositions[i][0] * scale) + 200;
				int y = (int) (hexPositions[i][1] * scale) + 50;

				// Set Image
				BufferedImage hexImage = ImageHandler.loadImage("/images/hex" + fileNameKey[0] + ".png");

				hexes.add(new Hex(x, y, 0, 0, hexImage));
			}
		}

		// Setup PlacePoints

		for (int a = 0; a < this.pointPositions.length; a++) {
			// Set X and Y
			int x = (int) (pointPositions[a][0] * scale) + 5 + 200;
			int y = (int) (pointPositions[a][1] * scale) + 5 + 50;

			// Compile ArrayList of surrounding Hexes
			ArrayList<Hex> surroundingHexes = new ArrayList<Hex>();
			int[] hexIDs = this.pointCorrelations[a];

			for (int b = 0; b < hexIDs.length; b++) {
				surroundingHexes.add(this.hexes.get(hexIDs[b]));
			}

			// Add new Point
			this.points.add(new PlacePoint(x, y, 20, surroundingHexes));
		}

		// For Testing: RoadPoints init
		double[][] roadOffsets = { { 1.801, 0 }, { 3.151, 0.780 }, { 3.151, 2.339 }, { 1.801, 3.119 }, { 0.450, 2.339 },
				{ 0.450, 0.780 } };
		for (Hex hex : this.hexes) {
			int hexX = hex.getX();
			int hexY = hex.getY();

			int adjustment = 38;
			for (int i = 0; i < roadOffsets.length; i++) {
				int offsetX = (int) (roadOffsets[i][0] * adjustment);
				int offsetY = (int) (roadOffsets[i][1] * adjustment);

				this.roadPoints.add(new RoadPoint(hexX + offsetX - 8, hexY + offsetY - 8, 15));
			}
		}

	}

	// Utility methods for the GameHandler to use

	// Show points if they are enabled
	public void showPoints() {
		// Only show points if enabled
		for (PlacePoint point : this.points) {
			if (point.isEnabled()) {
				point.setVisible(true);
			}
		}
	}

	// Sets the visibility of all points to invisible
	public void hidePoints() {
		for (PlacePoint point : this.points) {
			point.setVisible(false);
		}
	}

	// Disable the surrounding points based on the found position of the input point
	public void disableSurroundingPoints(PlacePoint point) {
		int pointIndex = this.points.indexOf(point);
		for (int i = 0; i < this.pointSurroundings[pointIndex].length; i++) {
			this.points.get(this.pointSurroundings[pointIndex][i]).setEnabled(false);
		}
	}

	// Disable points based on the index of the point in the Array
	public void disableSurroundingPoints(int pointIndex) {
		for (int i = 0; i < this.pointSurroundings[pointIndex].length; i++) {
			this.points.get(this.pointSurroundings[pointIndex][i]).setEnabled(false);
		}
	}

	// Basic rendering and updating methods
	@Override
	public void render(Graphics graphics) {
		// Render all of the hexes
		for (Hex value : hexes) {
			value.render(graphics);
		}
		// Render PlacePoints
		for (PlacePoint value : points) {
			value.render(graphics);
		}
		// Render RoadPoints
		for (RoadPoint value : roadPoints) {
			value.render(graphics);
		}
		// Render Settlements
		for (Settlement value : settlements) {
			value.render(graphics);
		}
	}

	@Override
	public void update() {

	}

	// Getters and Setters
	public ArrayList<Hex> getHexes() {
		return hexes;
	}

	public void setHexes(ArrayList<Hex> hexes) {
		this.hexes = hexes;
	}

	public ArrayList<Settlement> getSettlements() {
		return settlements;
	}

	public void setSettlements(ArrayList<Settlement> settlements) {
		this.settlements = settlements;
	}

	public ArrayList<City> getCities() {
		return this.cities;
	}

	public void setCities(ArrayList<City> cities) {
		this.cities = cities;
	}

	public ArrayList<PlacePoint> getPoints() {
		return this.points;
	}

	public void setPoints(ArrayList<PlacePoint> points) {
		this.points = points;
	}

}
