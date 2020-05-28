package board;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import main.ImageHandler;
import utils.Utils;

public class Board extends GameObject {
	private ArrayList<Hex> hexes;
	private ArrayList<PlacePoint> points;
	private ArrayList<Settlement> settlements;

	public Board() {
		this.hexes = new ArrayList<Hex>();
		this.points = new ArrayList<PlacePoint>();
		this.settlements = new ArrayList<Settlement>();

		this.initializeBoard();
	}

	public void initializeBoard() {
		// Declare some useful variables
		Random ran = new Random();

		// Initialize the Type of the Hexes
		int[] typeAmounts = { 3, 3, 4, 4, 4 }; // < The amount of each type of hex. Type of hex is denoted by index
		int counter = 0; // Counter to keep track of how many have been initialized
		while (counter <= 17) {
			int index = ran.nextInt(typeAmounts.length); // Find random index in bounds of the array
			if (typeAmounts[index] != 0) {
				this.hexes.add(new Hex(index + 1)); // Corrects for the index being one less because no desert
				typeAmounts[index]--;
				counter++;
			}
		}
		hexes.add(6, new Hex(0)); // Adds the desert hex to the center

		// Initialize the Amounts of the Hexes
		ArrayList<Integer> tokenAmounts = Utils
				.intArraytoArrayList(new int[] { 2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12 });
		for (int i = 0; i < hexes.size(); i++) {
			if (i != 6) {
				int index = ran.nextInt(tokenAmounts.size());
				hexes.get(i).setAmount((int) tokenAmounts.get(index));
				tokenAmounts.remove(index);
			}
		}

		// Set hex positions on the board
		double[][] hexPositions = { { 1.26, 0.00 }, { 0.63, 0.36 }, { 1.26, 0.71 }, { 1.89, 0.36 }, { 0.00, 0.71 },
				{ 0.63, 1.07 }, { 1.26, 1.42 }, { 1.89, 1.07 }, { 2.52, 0.71 }, { 0.00, 1.42 }, { 0.63, 1.78 },
				{ 1.26, 2.13 }, { 1.89, 1.78 }, { 2.52, 1.42 }, { 0.00, 2.13 }, { 0.63, 2.49 }, { 1.26, 2.84 },
				{ 1.89, 2.49 }, { 2.52, 2.13 } };
		double scale = 164;
		for (int i = hexPositions.length - 1; i >= 0; i--) {
			hexes.get(i).setX((int) (hexPositions[i][0] * scale));
			hexes.get(i).setY((int) (hexPositions[i][1] * scale));
		}

		// For Testing: Color the tiles
		String[] nameMap = { "desert", "bricks", "ore", "sheep", "timber", "wheat" };
		for (int i = 0; i < hexes.size(); i++) {
			int type = hexes.get(i).getType();
			String path = "/images/hex" + nameMap[type] + ".png";
			hexes.get(i).setImage(ImageHandler.loadImage(path));
		}

		// Setup the PlacePoints on the board
		double[][] pointPositions = { { 1.19, 0.29 }, { 2.00, 0.29 }, { 0.56, 0.65 }, { 1.40, 0.65 }, { 1.80, 0.65 },
				{ 2.66, 0.65 }, { 0.75, 1.01 }, { 1.19, 1.01 }, { 2.00, 1.01 }, { 2.44, 1.01 }, { 0.14, 1.36 },
				{ 0.56, 1.36 }, { 1.40, 1.36 }, { 1.80, 1.36 }, { 2.66, 1.36 }, { 3.05, 1.36 }, { 0.75, 1.70 },
				{ 1.19, 1.70 }, { 2.00, 1.70 }, { 2.44, 1.70 }, { 0.14, 2.06 }, { 0.56, 2.06 }, { 1.40, 2.06 },
				{ 1.80, 2.06 }, { 2.66, 2.06 }, { 3.05, 2.06 }, { 0.75, 2.42 }, { 1.19, 2.42 }, { 2.00, 2.42 },
				{ 2.44, 2.42 }, { 0.56, 2.77 }, { 1.40, 2.77 }, { 1.80, 2.77 }, { 2.66, 2.77 }, { 1.19, 3.13 },
				{ 2.00, 3.13 } };
		for (int i = pointPositions.length - 1; i >= 0; i--) {
			points.add(new PlacePoint((int) (pointPositions[i][0] * scale) + 5,
					(int) (pointPositions[i][1] * scale) + 5, 20));
			points.get(points.size() - 1).setVisible(true);
		}

	}

	// Utility methods for the GameHandler to use
	public void showPoints() {
		// Only show points if enabled
		for (PlacePoint point : this.points) {
			if (point.isEnabled()) {
				point.setVisible(true);
			}
		}
	}

	public void hidePoints() {
		for (PlacePoint point : this.points) {
			point.setVisible(false);
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

	public ArrayList<PlacePoint> getPoints() {
		return this.points;
	}

	public void setPoints(ArrayList<PlacePoint> points) {
		this.points = points;
	}

}
