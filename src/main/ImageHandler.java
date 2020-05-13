package main;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageHandler {

	public static BufferedImage loadImage(String path) {
		BufferedImage loadedImage = null;
		try {
			loadedImage = ImageIO.read(Game.class.getResource(path));
			return loadedImage;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
