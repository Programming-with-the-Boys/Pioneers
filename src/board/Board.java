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
	private ArrayList<Road> roads;
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
	// RoadPoint correlation with center of Hex
	private final double[][] roadPointOffsets = { { 1.801, 0 }, { 3.151, 0.780 }, { 3.151, 2.339 }, { 1.801, 3.119 },
			{ 0.450, 2.339 }, { 0.450, 0.780 } };
	// RoadPoint correlation to RoadPoints
	private final int[][] roadPointToPoint = { { 5, 1 }, { 0, 15, 2 }, { 1, 15, 3, 11 }, { 4, 7, 2, 11 },
			{ 5, 6, 7, 3 }, { 0, 6, 4 }, { 5, 10, 4 }, { 4, 3, 8, 14 }, { 9, 20, 7, 14 }, { 10, 19, 20, 8 },
			{ 6, 19, 9 }, { 2, 3, 12, 18 }, { 11, 18, 13, 27 }, { 14, 24, 27, 12 }, { 7, 8, 13, 24 }, { 1, 2, 16 },
			{ 15, 17, 34 }, { 16, 34, 17 }, { 11, 12, 17, 31 }, { 10, 9, 23 }, { 9, 8, 22, 26 }, { 22, 41, 20, 26 },
			{ 23, 21 }, { 19, 22 }, { 14, 13, 25, 30 }, { 26, 38, 24, 30 }, { 20, 21, 38, 25 }, { 13, 12, 28, 33 },
			{ 27, 33, 29, 45 }, { 30, 42, 28, 45 }, { 34, 25, 42, 29 }, { 18, 17, 32, 37 }, { 31, 37, 33, 39 },
			{ 27, 28, 32, 49 }, { 16, 17, 35 }, { 34, 36 }, { 35, 37, 52 }, { 31, 32, 36, 52 }, { 36, 25, 39, 44 },
			{ 40, 53, 38, 44 }, { 41, 39, 58 }, { 22, 21, 49 }, { 30, 29, 43, 48 }, { 44, 55, 42, 48 },
			{ 48, 39, 43, 55 }, { 28, 29, 46, 51 }, { 45, 51, 47, 62 }, { 48, 59, 46, 62 }, { 42, 43, 47, 59 },
			{ 33, 32, 54, 50 }, { 49, 54, 51, 66 }, { 45, 46, 66, 50 }, { 37, 36, 53 }, { 52, 54, 69 },
			{ 49, 50, 53, 69 }, { 44, 43, 56, 61 }, { 57, 55, 61 }, { 58, 56 }, { 40, 39, 57 }, { 48, 47, 60, 65 },
			{ 61, 59 }, { 56, 55, 60 }, { 47, 46, 63, 68 }, { 62, 68, 64 }, { 65, 63 }, { 60, 59, 64 },
			{ 51, 50, 67, 71 }, { 66, 71, 68 }, { 62, 63, 67 }, { 54, 53, 70 }, { 69, 71 }, { 66, 67, 70 } };
	// RoadPoint correlation to PlacePoints
	private final int[][] roadPointToPlacePoint = { {}, { 1 }, { 1, 4 }, { 4, 3, 7 }, { 0, 3 }, { 0 }, { 0 }, { 3, 7 },
			{ 6, 7 }, { 2, 6 }, { 2 }, { 4, 8 }, { 8, 13 }, { 12, 13 }, { 7, 12 }, { 1 }, { 5 }, { 5, 9 }, { 8, 9 },
			{ 2 }, { 6, 11 }, { 10, 11 }, { 10 }, {}, { 12, 17 }, { 16, 17 }, { 11, 16 }, { 13, 18 }, { 18, 23 },
			{ 22, 23 }, { 17, 22 }, { 9, 14 }, { 14, 19 }, { 18, 19 }, { 5 }, {}, { 15 }, { 14, 15 }, { 16, 21 },
			{ 20, 21 }, { 20 }, { 10 }, { 22, 27 }, { 26, 27 }, { 21, 26 }, { 23, 28 }, { 28, 32 }, { 31, 32 },
			{ 27, 31 }, { 19, 24 }, { 24, 29 }, { 28, 29 }, { 15 }, { 25 }, { 24, 25 }, { 26, 30 }, { 30 }, {}, { 20 },
			{ 31, 34 }, { 34 }, { 30 }, { 32, 35 }, { 35 }, {}, { 34 }, { 29, 33 }, { 33 }, { 35 }, { 25 }, {},
			{ 33 } };

	public Board() {
		this.hexes = new ArrayList<Hex>();
		this.points = new ArrayList<PlacePoint>();
		this.roadPoints = new ArrayList<RoadPoint>();
		this.settlements = new ArrayList<Settlement>();
		this.cities = new ArrayList<City>();
		this.roads = new ArrayList<Road>();

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

		// Setup RoadPoint Positions
		for (Hex hex : this.hexes) {
			int hexX = hex.getX();
			int hexY = hex.getY();

			// Setup new scale variable
			int adjustment = 37;

			// Go through all of the point offsets
			for (int i = 0; i < roadPointOffsets.length; i++) {
				// Get offset from top left of Hex
				int offsetX = (int) (roadPointOffsets[i][0] * adjustment);
				int offsetY = (int) (roadPointOffsets[i][1] * adjustment);
				// Declare random (?) variable to subtract by
				int arbOffset = 8;

				// Check if another RoadPoint already has this positions
				boolean onAnother = false;

				// Go through the points
				for (int a = 0; a < this.roadPoints.size(); a++) {
					if (this.roadPoints.get(a).onObject(hexX + offsetX - arbOffset, hexY + offsetY - arbOffset)) {
						onAnother = true;
					}
				}

				// If not on another one add
				if (!onAnother) {
					this.roadPoints.add(new RoadPoint(hexX + offsetX - 8, hexY + offsetY - 8, 15));
				}
			}
		}

		// Setup RoadPoint Surrounding RoadPoints and PlacePoints
		for (int i = 0; i < this.roadPointToPoint.length; i++) {
			// Grab the point to be worked on
			RoadPoint point = this.roadPoints.get(i);

			// Grab surrounding RoadPoints and place into an ArrayList
			ArrayList<RoadPoint> surroundingPoints = new ArrayList<RoadPoint>();

			for (int j = 0; j < this.roadPointToPoint[i].length; j++) {
				surroundingPoints.add(this.roadPoints.get(this.roadPointToPoint[i][j]));
			}

			// Set the surrounding RoadPoints to the gathered RoadPoint
			point.setSurroundingRoadPoints(surroundingPoints);

			// Grab surrounding PlacePoints and place into an ArrayList
			ArrayList<PlacePoint> surroundingPlacePoints = new ArrayList<PlacePoint>();

			for (int k = 0; k < this.roadPointToPlacePoint[i].length; k++) {
				surroundingPlacePoints.add(this.points.get(this.roadPointToPlacePoint[i][k]));
			}

			// Set Surrounding PlacePoints
			point.setSurroundingPlacePoints(surroundingPlacePoints);

			// Set bounds
			point.arbOffset = 15;
		}

	}

	// Utility methods for the GameHandler

	// Show points if they are enabled
	public void showPlacePoints() {
		// Only show points if enabled
		for (PlacePoint point : this.points) {
			if (point.isEnabled()) {
				point.setVisible(true);
			}
		}
	}

	// Sets the visibility of all points to invisible
	public void hidePlacePoints() {
		for (PlacePoint point : this.points) {
			point.setVisible(false);
		}
	}

	// Shows road points if they are enabled
	public void showRoadPoints() {
		// Only show points if enabled
		for (RoadPoint point : this.roadPoints) {
			if (point.isEnabled()) {
				point.setVisible(true);
			}
		}
	}

	// Sets visibility of all RoadPoints to invisible
	public void hideRoadPoints() {
		for (RoadPoint point : this.roadPoints) {
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
		// Render Roads
		for (Road value : roads) {
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

	public ArrayList<Settlement> getSettlements() {
		return settlements;
	}

	public ArrayList<City> getCities() {
		return this.cities;
	}

	public ArrayList<PlacePoint> getPoints() {
		return this.points;
	}

	public ArrayList<RoadPoint> getRoadPoints() {
		return this.roadPoints;
	}

	public ArrayList<Road> getRoads() {
		return this.roads;
	}

}
