package engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	private final boolean[] down;
	
	public Keyboard() {
		down = new boolean[256];
	}
	
	public boolean isDown(final int code) {
		return down[code];
	}
	
	@Override
	public void keyPressed(final KeyEvent e) {
		down[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(final KeyEvent e) {
		down[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(final KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
