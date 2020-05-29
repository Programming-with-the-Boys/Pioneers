package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import board.Board;
import board.PlacePoint;
import board.Settlement;
import gui.GUI;
import player.LocalPlayer;
import player.Player;

public class GameHandler {
	private Game game;
	private GUI infoGUI;
	private Board gameBoard;
	private ArrayList<Player> players;
	private MouseEventListener mouseListener;

	public void setupGame(Game game, MouseEventListener mouseListener) {
		// SetupStuff?
		this.gameBoard = new Board();
		this.game = game;
		this.mouseListener = mouseListener;
		this.infoGUI = new GUI(0, 700, 1000, 300);

		// Test Setup
		this.players = new ArrayList<Player>();
		this.players.add(new LocalPlayer("Bot1"));
		this.players.add(new LocalPlayer("Bot2"));

		this.initialPlacement();
		this.infoGUI.setPlayer(players.get(0));
	}

	public void render(Graphics graphics) {
		this.gameBoard.render(graphics);
		this.infoGUI.render(graphics);
	}

	public void initialPlacement() {
		// Go top to bottom placing settlments
		for (int i = 0; i < this.players.size(); i++) {
			this.placeSettlement(this.players.get(i));
			// TODO: Place roads (here)
		}

		// Go from bottom to top placing settlements
		for (int i = this.players.size() - 1; i >= 0; i--) {
			this.placeSettlement(this.players.get(i));
		}

	}

	public void placeSettlement(Player p) {
		// Get points for testing
		ArrayList<PlacePoint> points = this.gameBoard.getPoints();

		// Make PlacePoints visible
		this.gameBoard.showPoints();

		// Forcibly render the game to show points
		game.render();

		boolean placed = false;
		while (!placed) {
			this.game.render(); // Keeps the game rendering
			if (mouseListener.keys[1]) {
				// Find whether it intersects
				for (int i = 0; i < points.size(); i++) {
					PlacePoint point = points.get(i);
					if (point.onObject(mouseListener.x, mouseListener.y) && point.isEnabled()) {
						this.gameBoard.getSettlements()
								.add(new Settlement(point.getX() - 6, point.getY() - 6, p, point));
						point.setEnabled(false);
						point.setVisible(false);
						p.addVictoryPoints(1);
						placed = true; // Stop loop
					}
				}
			}
		}

		// Make PlacePoints invisible again
		this.gameBoard.hidePoints();
		game.render();
	}

	public void placeRoad(Player p) {

	}

	public void obtainResources(int roll) {

	}

	// Getters and Setters
	public ArrayList<Player> getPlayers() {
		return this.players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

}
