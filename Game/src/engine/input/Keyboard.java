package engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import engine.scene.entity.Player;

public class Keyboard implements KeyListener {
	private final boolean[] down;
	public boolean left = false;

	public Keyboard() {
		down = new boolean[256];
	}

	public boolean isDown(final int code) {
		return down[code];
	}

	@Override
	public void keyPressed(final KeyEvent e) {
		down[e.getKeyCode()] = true;
		if (e.equals(KeyEvent.VK_A)) {
			Player.left(true);
		} else if (e.equals(KeyEvent.VK_W)) {

		} else if (e.equals(KeyEvent.VK_D)) {

		} else if (e.equals(KeyEvent.VK_S)) {

		}
	}

	@Override
	public void keyReleased(final KeyEvent e) {
		down[e.getKeyCode()] = false;
		if (e.equals(KeyEvent.VK_A)) {
			Player.left(false);
		} else if (e.equals(KeyEvent.VK_W)) {

		} else if (e.equals(KeyEvent.VK_D)) {

		} else if (e.equals(KeyEvent.VK_S)) {

		}
	}

	@Override
	public void keyTyped(final KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
