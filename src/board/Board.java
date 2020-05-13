package board;

import java.awt.Graphics;
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
		
		int foo = 0;
		for (Hex h : hexes) {
			h.setX(foo);
			foo += 80;
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
