package main;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseEventListener implements MouseListener, FocusListener {
	public boolean[] keys = new boolean[4];
	public int x, y;

	// Button clicked (up and down) DO NOT USE
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	// Push down
	@Override
	public void mousePressed(MouseEvent e) {
		this.keys[e.getButton()] = true;
		this.x = e.getX();
		this.y = e.getY();
	}

	// Pull up
	@Override
	public void mouseReleased(MouseEvent e) {
		keys[e.getButton()] = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void focusGained(FocusEvent e) {

	}

	@Override
	public void focusLost(FocusEvent e) {

	}

}
