package main;

import java.util.ArrayList;
import java.util.Random;
import board.Board;
import board.Hex;

public class GameHandler {
	private Board gameBoard = new Board();

	// Generates game layout
	public void setupGame() {
		gameBoard.initializeBoard();
	}

	public Board getGameBoard() {
		return this.gameBoard;
	}

}
