package main;

import java.awt.Graphics;

import board.Board;

public class GameHandler {
	private Board gameBoard = new Board();

	// Generates game layout
	public void setupGame() {
		gameBoard.initializeBoard();
	}

	public void render(Graphics graphics) {
		this.gameBoard.render(graphics);
	}

}
