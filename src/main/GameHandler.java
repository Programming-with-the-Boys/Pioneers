package main;

import java.awt.Graphics;
import java.util.ArrayList;

import board.Board;
import gui.Gui;
import player.LocalPlayer;
import player.Player;

public class GameHandler {
	private Board gameBoard;
	private Gui mainGui;
	private ArrayList<Player> players;

	// Generates game layout
	public void setupGame() {
		// Setup GUI
		this.gameBoard = new Board();
		this.mainGui = new Gui(0, 700, 1000, 300);

		// Test Setup
		this.players = new ArrayList<Player>();
		this.players.add(new LocalPlayer("Bot1"));
		this.players.add(new LocalPlayer("Bot2"));
		this.players.add(new LocalPlayer("Bot3"));
		this.players.add(new LocalPlayer("Bot4"));
	}

	public void firstPicks() {

	}

	public void render(Graphics graphics) {
		this.gameBoard.render(graphics);
		this.mainGui.render(graphics);
	}

}
