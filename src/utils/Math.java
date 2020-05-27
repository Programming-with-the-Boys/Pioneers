package utils;

import java.awt.Rectangle;

public class Math {
	public static boolean inBounds(Rectangle rect, int objectX, int objectY, int testX, int testY) {
		if (testX >= objectX && testX <= objectX + rect.width && testY >= objectY && testY <= objectY + rect.height) {
			return true;
		}
		return false;
	}

}
