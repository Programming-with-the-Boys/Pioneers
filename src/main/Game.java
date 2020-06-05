package main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Arrays;

import javax.swing.JFrame;

public class Game extends JFrame implements Runnable {


	private Canvas canvas = new Canvas();
	private RenderHandler renderer;
	private GameHandler handler;
	private MouseEventListener mouseListener;

	public Game() {
		// Set what happens when window is closed
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set frame position
		this.setBounds(0, 0, 1000, 1000);

		// Put frame in the center of the screen
		this.setLocationRelativeTo(null);

		// Add the editable graphics component
		this.add(canvas);

		// Make it not resizeable (temporary)
		this.setResizable(false);

		// Make the frame visible
		this.setVisible(true);

		// BufferStrategy Creation
		canvas.createBufferStrategy(3);

		// Initialize renderer
		renderer = new RenderHandler(this.getWidth(), this.getHeight());

		// Instantiate MouseListener
		mouseListener = new MouseEventListener();

		// Add listener to Canvas
		canvas.addMouseListener(mouseListener);
		canvas.addFocusListener(mouseListener);

		// Initialize game controller
		handler = new GameHandler();

		// Start game setup
		handler.setupGame(this, this.mouseListener);
	}

	@Override
	public void run() {
		BufferStrategy bufferStrategy = canvas.getBufferStrategy();
		int i = 0;
		int x = 0;

		long lastTime = System.nanoTime();
		double nanoSecondConversion = 1000000000.0 / 60;
		double changeInSeconds = 0;

		while (true) {
			long now = System.nanoTime();

			changeInSeconds += (now - lastTime);
			while (changeInSeconds >= 1) {
				update();
				changeInSeconds = 0;
			}

			render();
			lastTime = now;
		}

	}

	public void render() {
		BufferStrategy bufferStrategy = canvas.getBufferStrategy();
		Graphics graphics = bufferStrategy.getDrawGraphics();
		super.paint(graphics);

		renderer.render(graphics);
		handler.render(graphics);

		graphics.dispose();
		bufferStrategy.show();
	}

	public void update() {

	}

	public static void main(String[] args) {
		Game game = new Game();
		Thread gameThread = new Thread(game);
		gameThread.start();
	}

}
