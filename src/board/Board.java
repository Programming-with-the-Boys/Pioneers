package board;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import main.ImageHandler;
import utils.ArrayUtil;

public class Board extends GameObject {
	ArrayList<Hex> hexes;
	ArrayList<PlacePoint> points;

	public Board() {
		this.hexes = new ArrayList<Hex>();
		this.points = new ArrayList<PlacePoint>();
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
		ArrayList<Integer> tokenAmounts = ArrayUtil
				.intArraytoArrayList(new int[] { 2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12 });
		for (int i = 0; i < hexes.size(); i++) {
			if (i != 6) {
				int index = ran.nextInt(tokenAmounts.size());
				hexes.get(i).setAmount((int) tokenAmounts.get(index));
				tokenAmounts.remove(index);
			}
		}

		// Set hex positions on the board
		double[][] hexPositions = { { 5.402, 0 }, { 2.701, -1.560 }, { 5.402, -3.119 }, { 8.104, -1.560 },
				{ 0, -3.119 }, { 2.701, -4.679 }, { 5.402, -6.238 }, { 8.104, -4.679 }, { 10.805, -3.119 },
				{ 0, -6.238 }, { 2.701, -7.798 }, { 5.402, -9.357 }, { 8.104, -7.798 }, { 10.805, -6.238 },
				{ 0, -9.357 }, { 2.701, -10.917 }, { 5.402, -12.476 }, { 8.104, -10.917 }, { 10.805, -9.357 } };
		double scale = 36.7;
		for (int i = hexPositions.length - 1; i >= 0; i--) {
			hexes.get(i).setX((int) ((hexPositions[i][0] * -1) * scale) + 600);
			hexes.get(i).setY((int) ((hexPositions[i][1] * -1) * scale) + 100);
		}

		// For Testing: Color the tiles
		String[] nameMap = { "desert", "bricks", "ore", "sheep", "timber", "wheat" };
		for (int i = 0; i < hexes.size(); i++) {
			int type = hexes.get(i).getType();
			String path = "/images/hex" + nameMap[type] + ".png";
			hexes.get(i).setImage(ImageHandler.loadImage(path));
		}

		// For Testing: Setup PlacePoints
		double[][] pointPositions = { { 6.303, 0 }, { 8.104, 0 }, { 9.004, -1.560 }, { 8.104, -3.119 },
				{ 6.303, -3.119 }, { 5.402, -1.560 }, { 3.602, -1.560 }, { 5.402, -1.560 }, { 6.303, -3.119 },
				{ 5.402, -4.679 }, { 3.602, -4.679 }, { 2.701, -3.119 }, { 6.303, -3.119 }, { 8.104, -3.119 },
				{ 9.004, -4.679 }, { 8.104, -6.238 }, { 6.303, -6.238 }, { 5.402, -4.679 }, { 9.004, -1.560 },
				{ 10.805, -1.560 }, { 11.705, -3.119 }, { 10.805, -4.679 }, { 9.004, -4.679 }, { 8.104, -3.119 },
				{ .9, -3.119 }, { 2.701, -3.119 }, { 3.602, -4.679 }, { 2, 701, -6.238 }, { .9, -6.238 }, { 0, -4.679 },
				{ 3.602, -4.679 }, { 5.402, -4.679 }, { 6.303, -6.238 }, { 5.402, -7.798 }, { 3.602, -7.798 },
				{ 2.701, -6.238 }, { 6.303, -6.238 }, { 8.104, -6.238 }, { 9.004, -7.798 }, { 8.104, -9.357 },
				{ 6.303, -9.357 }, { 5.402, -7.798 }, { 9.004, -4.679 }, { 10.805, -4.679 }, { 11.705, -6.238 },
				{ 10.805, -7.790 }, { 9.004, -7.798 }, { 8.104, -6.238 }, { 11.705, -3.119 }, { 13.506, -3.119 },
				{ 14.406, -4.679 }, { 13.506, -6.238 }, { 11.705, -6.238 }, { 10.805, -4.679 }, { .9, -6.238 },
				{ 2.701, -6.238 }, { 3.602, -7.798 }, { 2.701, -9.357 }, { .9, -9.357 }, { 0, -7.798 },
				{ 3.602, -7.798 }, { 5.402, -7.798 }, { 6.303, -9.357 }, { 5.402, -10.917 }, { 3.602, -10.917 },
				{ 2.701, -9.357 }, { 6.303, -9.357 }, { 8.104, -9.357 }, { 9.004, -10.917 }, { 8.104, -12.476 },
				{ 6.303, -12.476 }, { 5.402, -10.917 }, { 9.004, -7.798 }, { 10.805, -7.798 }, { 11.705, -9.357 },
				{ 10.805, -10.917 }, { 9.004, -10.917 }, { 8.104, -9.357 }, { 11.705, -6.238 }, { 13.506, -6.238 },
				{ 14.406, -7.798 }, { 13.506, -9.357 }, { 11.705, -9.357 }, { 10.805, -7.798 }, { .9, -9.357 },
				{ 2.701, -9.357 }, { 3.602, -10.917 }, { 2.701, -12.476 }, { .9, -12.476 }, { 0, -10.917 },
				{ 3.602, -10.917 }, { 5.402, -10.917 }, { 6.303, -12.476 }, { 5.402, -14.036 }, { 3.602, -14.036 },
				{ 2.701, -12.476 }, { 6.303, -12.476 }, { 8.104, -12.476 }, { 9.004, -14.036 }, { 8.104, -15.596 },
				{ 6.303, -15.595 }, { 5.402, -14.036 }, { 9.004, -10.917 }, { 10.805, -10.917 }, { 11.705, -12.476 },
				{ 10.805, -14.036 }, { 9.004, -14.036 }, { 8.104, -12.476 }, { 11.705, -9.357 }, { 13.506, -9.357 },
				{ 14.406, -10.917 }, { 13.506, -12.476 }, { 11.705, -12.476 }, { 10.805, -10.917 } };
		for (int i = pointPositions.length - 1; i >= 0; i--) {
			points.add(new PlacePoint((int) ((pointPositions[i][0] * -1) * scale) + 728,
					(int) ((pointPositions[i][1] * -1) * scale) + 96, 20));
		}

	}

	@Override
	public void render(Graphics graphics) {
		// Render all of the hexes
		for (Hex value : hexes) {
			value.render(graphics);
		}
		// Render the place points if they want to be rendered
		// Rendering of points is controlled by points
		for (PlacePoint value : points) {
			value.render(graphics);
		}
	}

	@Override
	public void update() {

	}
}
