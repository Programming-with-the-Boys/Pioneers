package player;

public abstract class Player {
	private String name;
	private int victoryPoints;

	public Player(String name) {
		this.name = name;
		this.victoryPoints = 0;
	}

	// Getters and Setters
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
