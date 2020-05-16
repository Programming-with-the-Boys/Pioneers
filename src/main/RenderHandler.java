package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class RenderHandler {
	private BufferedImage view;

	public RenderHandler(int width, int height) {
		// Create BufferedImage for view
		view = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}

	public void render(Graphics graphics) {
		Graphics g = view.getGraphics();

		graphics.drawImage(view, 0, 0, view.getWidth(), view.getHeight(), null);
	}

}
