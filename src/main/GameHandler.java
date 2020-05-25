package main;

import java.awt.Graphics;

import board.Board;

public class GameHandler {
	private Board gameBoard;

	// Generates game layout
	public void setupGame() {
		this.gameBoard = new Board();
	}

	public void render(Graphics graphics) {
		this.gameBoard.render(graphics);
	}

}
