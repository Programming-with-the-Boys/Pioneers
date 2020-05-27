package main;

import java.awt.Graphics;
import java.util.ArrayList;

import board.Board;
import gui.GUI;
import player.LocalPlayer;
import player.Player;

public class GameHandler {
	private Board gameBoard;
	private GUI mainGui;
	private ArrayList<Player> players;
	private MouseListen mouseListener;

	// Generates game layout
	public void setupGame(MouseListen mouseListener) {
		// Setup GUI
		this.gameBoard = new Board();
		this.mainGui = new GUI(0, 700, 1000, 300);
		this.mouseListener = mouseListener;

		// Test Setup
		this.players = new ArrayList<Player>();
		this.players.add(new LocalPlayer("Bot1"));
		this.players.add(new LocalPlayer("Bot2"));
		this.players.add(new LocalPlayer("Bot3"));
		this.players.add(new LocalPlayer("Bot4"));
	}
	
	public void placeSettlement(Player p) {
		boolean validPosition = false;
		while(!validPosition) {
			if(this.mouseListener.keys[1]) {
				
			}
		}
	}

	public void render(Graphics graphics) {
		this.gameBoard.render(graphics);
		this.mainGui.render(graphics);
	}

}
