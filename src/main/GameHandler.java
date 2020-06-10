package main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import board.Board;
import board.Hex;
import board.PlacePoint;
import board.Road;
import board.RoadPoint;
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

			// Place Settlement
			this.placeSettlement(this.players.get(i));

			// Place Road
			this.placeRoad(this.players.get(i));
		}

		// Go from bottom to top placing settlements
		for (int i = this.players.size() - 1; i >= 0; i--) {
			// Update player information
			this.infoGUI.setPlayer(players.get(i));

			// Place Settlement 2
			this.placeSettlement(this.players.get(i));

			// Place Road 2
			this.placeRoad(this.players.get(i));
		}
	}

	public void runGame() {
		Random ran = new Random();
		while (!this.wonGame(this.players)) {
			for (int i = 0; i < this.players.size(); i++) { // Roll dice
				int roll = (ran.nextInt(6) + 1) + (ran.nextInt(6) + 1);

				// Give resources to player
				this.obtainResources(roll, this.players.get(i));

				// Update GUI
				this.infoGUI.getDiceInfo().setLastRoll(roll);
				this.game.render();

				while (true) {
					game.render();
				}
			}

		}
	}

	public void placeSettlement(Player player) {
		// Get points for testing
		ArrayList<PlacePoint> points = this.gameBoard.getPoints();

		// Make PlacePoints visible
		this.gameBoard.showPlacePoints();

		// Forcibly render the game to show points
		game.render();

		boolean placed = false;
		while (!placed) {
			// Render the Game
			this.game.render();

			// If the mouse is clicked:
			if (mouseListener.keys[1]) {

				// Go through all the points
				for (int i = 0; i < points.size(); i++) {
					// Declare point for ease
					PlacePoint point = points.get(i);

					// If the mouse is on the selected object and it is enabled
					if (point.onObject(mouseListener.x, mouseListener.y) && point.isEnabled()) {
						// Add new Settlement
						this.gameBoard.getSettlements()
								.add(new Settlement(point.getX() - 6, point.getY() - 6, player, point));

						// Disable the point and make it invisible
						point.setEnabled(false);
						point.setVisible(false);

						// Disable all the surrounding points
						this.gameBoard.disableSurroundingPoints(point);

						// Add requisite VPs
						player.addVictoryPoints(1);

						// Stop the loop
						placed = true;
					}
				}
			}
		}

		// Make PlacePoints invisible again
		this.gameBoard.hidePlacePoints();
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

	// Temporary Testing
	public void placeRoad(Player player) {
		// Get RoadPoints for testing
		ArrayList<RoadPoint> points = this.gameBoard.getRoadPoints();

		boolean placed = false;

		while (!placed) {
			// Render the Game
			this.game.render();

			// If the mouse is clicked:
			if (mouseListener.keys[1]) {

				// Go through all the points
				for (int i = 0; i < points.size(); i++) {

					// Declare point for ease
					RoadPoint point = points.get(i);

					// If the mouse is on the selected object and it is enabled
					if (point.onObject(mouseListener.x, mouseListener.y) && point.isEnabled()) {
						
						// Check to make sure that a Settlement or Road is next to it
						boolean validPlace = false;
						
						// Check Settlement
						ArrayList<PlacePoint> testPoints = point.getSurroundingPlacePoints();
						for (Settlement value : this.gameBoard.getSettlements()) {
							for (PlacePoint testPlacePoint : testPoints) {
								if (value.getOriginPoint().equals(testPlacePoint)) {
									validPlace = true;
								}
							}
						}

						if (validPlace) {
							// Add Road
							this.gameBoard.getRoads().add(new Road(point, player));

							// Disable the Point
							point.setEnabled(false);

							// Stop the loop
							placed = true;
						}
					}
				}
			}
		}
	}

	public void obtainResources(int diceRoll, Player player) {
		for (Settlement settlement : this.gameBoard.getSettlements()) {
			if (settlement.getOwner().equals(player)) {
				for (Hex hex : settlement.getSurroundingHexes()) {
					if (hex.getNumber() == diceRoll) {
						player.getResources()[hex.getType() - 1]++;
					}
				}
			}
		}
	}

	// Getters and Setters
	public ArrayList<Player> getPlayers() {
		return this.players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

}
