package player;

import board.Hex;

public abstract class Player {
	private String name;
	private int victoryPoints;
	private int[] resources;

	public Player(String name) {
		this.name = name;
		this.victoryPoints = 0;
		this.resources = new int[5];
	}

	// Getters and Setters
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVictoryPoints() {
		return this.victoryPoints;
	}

	public void setVictoryPoints(int points) {
		this.victoryPoints = points;
	}

	public void addVictoryPoints(int addition) {
		this.victoryPoints += addition;
	}

	public void setResources(int[] resources) {
		this.resources = resources;
	}

	public int[] getResources() {
		return this.resources;
	}

}
