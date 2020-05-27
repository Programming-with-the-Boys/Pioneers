package main;

import java.awt.Graphics;
import java.util.ArrayList;

import board.Board;
import board.PlacePoint;
import board.Settlement;
import gui.GUI;
import player.LocalPlayer;
import player.Player;
import utils.Math;

public class GameHandler {
	private Game game;
	private GUI mainGui;
	private Board gameBoard;
	private ArrayList<Player> players;
	private MouseEventListener mouseListener;

	// Generates game layout
	public void setupGame(Game game, MouseEventListener mouseListener) {
		// SetupStuff?
		this.gameBoard = new Board();
		this.mainGui = new GUI(0, 700, 1000, 300);
		this.mouseListener = mouseListener;
		this.game = game;

		// Test Setup
		this.players = new ArrayList<Player>();
		this.players.add(new LocalPlayer("Bot1"));
		this.players.add(new LocalPlayer("Bot2"));
		this.players.add(new LocalPlayer("Bot3"));
		this.players.add(new LocalPlayer("Bot4"));
		
		this.placeSettlement(new LocalPlayer("tester"));
		this.placeSettlement(this.players.get(0));
	}

	public void placeSettlement(Player p) {
		// Instantiate useful variable
		ArrayList<PlacePoint> points = this.gameBoard.getPoints();
		boolean placed = false;
		
		// Make PlacePoints visible
		for (PlacePoint point : points) {
			if(point.isEnabled()) {
				point.setVisible(true);
			}
		}
		game.render(); // Forcibly render the game to show points
		
		while (!placed) {
			this.game.render(); // Keeps the game rendering
			if (mouseListener.keys[1]) {
				// Find whether it intersects
				for (int i = 0; i < points.size(); i++) {
					PlacePoint point = points.get(i);
					if (point.onObject(mouseListener.x, mouseListener.y) && point.isEnabled()) {
						this.gameBoard.getSettlements().add(new Settlement(point.getX() - 6, point.getY() - 6)); // Add new Settlement in same place with offsets
						point.setEnabled(false);
						point.setVisible(false);
						placed = true; // Stop loop
					}
				}
			}
		}
		
		// Make PlacePoints in-visible again
		for (PlacePoint point : points) {
			point.setVisible(false);
		}
		game.render();

	}

	public void render(Graphics graphics) {
		this.gameBoard.render(graphics);
		this.mainGui.render(graphics);
	}

}
