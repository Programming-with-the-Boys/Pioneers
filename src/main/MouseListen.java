package main;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseListen implements MouseListener, FocusListener{
	public boolean[] keys = new boolean[4];

	// Button clicked
	@Override
	public void mouseClicked(MouseEvent e) {
		int keycode = e.getButton();
		this.keys[keycode] = true;
	}

	// Push down
	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	// Pull up
	@Override
	public void mouseReleased(MouseEvent e) {
		int keycode = e.getButton();
		this.keys[keycode] = false;
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
