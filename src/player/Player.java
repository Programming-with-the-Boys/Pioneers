package player;

public abstract class Player {
	private String name;

	public Player(String name) {
		this.name = name;
	}

	// Getters and Setters
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
