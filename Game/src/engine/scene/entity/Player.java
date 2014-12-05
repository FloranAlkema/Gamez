package engine.scene.entity;

import java.awt.Rectangle;

import engine.Game;
import engine.graphics.Sprite;

public class Player extends Entity {
	private final Sprite sprite;
	static boolean left, right, down, up;
	static boolean sprint;
	int boost;

	// static int width = sprite.width;
	// static int height;
	public static void left(boolean val) {
		left = val;
		// System.out.println("left val: " + left);
	}

	public static void up(boolean val) {
		up = val;
		// System.out.println("up val: " + up);
	}

	public static void down(boolean val) {
		down = val;
		// System.out.println("down val: " + down);
	}

	public static void right(boolean val) {
		right = val;
		// System.out.println("right val: " + right);
	}

	public static void sprint(boolean val) {
		sprint = val;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, sprite.width, sprite.height);
	}

	public void move() {
		if (left && right) {
			// stand still
		} else if (right) {
			x = x + 1 + boost;
		} else if (left) {
			x = x - 1 - boost;
		}
		if (down) {
			y++;
		} else if (up) {
			y--;
		}
		if (sprint) {
			boost = 1;
		} else {
			boost = 0;
		}
	}

	public Player() {
		sprite = Sprite.get("/player.png");
	}

	@Override
	public void update(final Game game) {
		move();

	}

	@Override
	public void render(final Game game) {
		game.screen.render(sprite, x, y);
	}
}
