package board;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import main.ImageHandler;

public class Board extends GameObject {
	ArrayList<Hex> hexes;

	public Board() {
		this.hexes = new ArrayList<Hex>();
	}

	public void initializeBoard() {
		// Array with Hex ID (-1 to compensate for Desert tile) as position and amount
		// of that tile as the number
		int[] typeAmounts = { 3, 3, 4, 4, 4 }; // Set amounts
		Random ran = new Random(); // Create a new Random
		int counter = 0; // Make a counter to keep tracker of items assigned

		while (counter <= 17) {
			int index = ran.nextInt(5);
			if (typeAmounts[index] > 0) {
				hexes.add(new Hex(index + 1));
				counter++;
				typeAmounts[index]--;
			}
		}
		hexes.add(6, new Hex(0)); //Add the center hex

		// For Testing: Color the tiles
		String[] nameMap = { "desert", "bricks", "ore", "sheep", "timber", "wheat" };
		for (int i = 0; i < hexes.size(); i++) {
			int type = hexes.get(i).getType();
			String path = "/images/hex" + nameMap[type] + ".png";
			System.out.println(path);
			hexes.get(i).setImage(ImageHandler.loadImage(path));
		}

		// For Testing: Label the Tiles
		for (int i = 0; i < hexes.size(); i++) {
			BufferedImage img = hexes.get(i).getImage();
			System.out.println(hexes.get(i).toString());
			Graphics tempG = img.getGraphics();

			tempG.setColor(Color.BLACK);
			tempG.setFont(new Font("TimesRoman", Font.PLAIN, 140));
			tempG.drawString("" + i, img.getWidth() / 2 - 80, img.getHeight() / 2 + 15);

			hexes.get(i).setImage(img);
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
	}

	@Override
	public void render(Graphics graphics) {
		for (Hex value : hexes) {
			value.render(graphics);
		}
	}

	@Override
	public void update() {

	}
}
