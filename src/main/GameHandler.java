package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import board.Board;
import board.Hex;
import board.PlacePoint;
import board.Settlement;
import gui.DiceInfo;
import gui.GUI;
import gui.PlayerInfo;
import player.LocalPlayer;
import player.Player;

public class GameHandler {
	private Game game;
	private GUI infoGUI;
	private Board gameBoard;
	private ArrayList<Player> players;
	private MouseEventListener mouseListener;

	public void setupGame(Game game, MouseEventListener mouseListener) {
		// Initialize Variables
		this.gameBoard = new Board();
		this.game = game;
		this.mouseListener = mouseListener;
		this.players = new ArrayList<Player>();

		this.infoGUI = new GUI(0, 700, 1000, 300);
		this.infoGUI.setInfo(new PlayerInfo(this.infoGUI.getX(), this.infoGUI.getY(), this.infoGUI.getWidth(),
				this.infoGUI.getHeight()));
		this.infoGUI.setDiceInfo(new DiceInfo(this.infoGUI.getWidth() - 200, 30));

		// Test Player Setup
		this.players.add(new LocalPlayer("Bot1"));
		// this.players.add(new LocalPlayer("Bot2"));

		this.initialPlacement();
		this.runGame();
	}

	public void render(Graphics graphics) {
		this.gameBoard.render(graphics);
		this.infoGUI.render(graphics);
	}

	public void initialPlacement() {
		// Go top to bottom placing settlments
		for (int i = 0; i < this.players.size(); i++) {
			// Update player information
			this.infoGUI.setPlayer(players.get(i));

			// Place settlement
			this.placeSettlement(this.players.get(i));
			// TODO: Place roads (here)
		}

		// Go from bottom to top placing settlements
		for (int i = this.players.size() - 1; i >= 0; i--) {
			// Update player information
			this.infoGUI.setPlayer(players.get(i));

			this.placeSettlement(this.players.get(i));
		}
	}

	public void runGame() {
		Random ran = new Random();
		while (!this.wonGame(this.players)) {
			for (int i = 0; i < this.players.size(); i++) {
				// Roll dice
				// int roll = (ran.nextInt(11) + 1);
				int roll = 10;

				// Update GUI
				this.infoGUI.getDiceInfo().setLastRoll(roll);
				this.game.render();

				for (Settlement settlement : this.gameBoard.getSettlements()) {
					System.out.println(settlement.toString());
					
					if (settlement.getOwner().equals(this.players.get(i))) {
						System.out.println(settlement.getOwner().getName());
						
						for (Hex hex : settlement.getSurroundingHexes()) {
							System.out.println(hex.toString());
							
							if (hex.getNumber() == roll) {
								this.players.get(i).getResources()[hex.getType() - 1]++;
								System.out.println(hex.getType());
							}
						}
					}
				}
				
				while(true) {
					game.render();
				}
			}

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
						this.gameBoard.disableSurroundingPoints(point);
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

	public boolean wonGame(ArrayList<Player> players) {
		for (Player player : this.players) {
			if (player.getVictoryPoints() >= 10) {
				return true;
			}
		}
		return false;
	}

	public void placeRoad(Player p) {

	}

	public void obtainResources(int diceRoll, Player player) {

	}

	// Getters and Setters
	public ArrayList<Player> getPlayers() {
		return this.players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

}
