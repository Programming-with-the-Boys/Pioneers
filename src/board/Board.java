package board;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Board extends GameObject {
	ArrayList<Hex> hexes;

	public Board() {
		this.hexes = new ArrayList<Hex>();
	}

	public void initializeBoard() {
		// Array with Hex ID (-1 to compensate for Desert tile) as position and amount
		// of that tile as the number
		int[] typeAmounts = { 3, 3, 4, 4, 4 };
		Random ran = new Random();
		int counter = 0;

		while (counter <= 17) {
			int index = ran.nextInt(5);
			if (typeAmounts[index] > 0) {
				hexes.add(new Hex(index + 1));
				counter++;
				typeAmounts[index]--;
			}
		}
		hexes.add(6, new Hex(0));

		// To Test: Label the Tiles
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
		double[][] hexPositions = { { 0, -5.402 }, { 1.560, -2.701 }, { 3.119, -5.402 }, { 1.560, -8.104 },
				{ 3.119, 0 }, { 4.679, -2.701 }, { 6.238, -5.402 }, { 4.679, -8.104 }, { 3.119, -10.805 }, { 6.238, 0 },
				{ 7.798, -2.701 }, { 9.357, -5.402 }, { 7.798, -8.104 }, { 6.238d, -10.805 }, { 9.357, 0 },
				{ 10.917, -2.701 }, { 12.476, -5.402 }, { 10.917, -8.104 }, { 9.357, -10.805 } };

		double scale = 55.0;
		int offset = 100;
		for (int i = 0; i < hexPositions.length; i++) {
			hexes.get(i).setX((int) (hexPositions[i][0] * scale) + offset);
			hexes.get(i).setY((int) (hexPositions[i][1] * scale) + offset + 500);
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
