package engine.scene.entity;

import engine.Game;
import engine.graphics.Sprite;
import engine.scene.Scene;

public class Player extends Entity {
	private final Sprite sprite;
	static boolean left, right, down, up;
	static boolean sprint;
	int boost;
	private double t = 0;
	
	public Player() {
		sprite = Sprite.get("/player.png");
		width = sprite.width;
		height = sprite.height;
	}

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

	public void move(final Scene scene) {
		if (left && right) {
			// stand still
		} else if (right) {
			x = x + 1 + boost;
			move(scene, 1 + boost, 0);
		} else if (left) {
			x = x - 1 - boost;
			move(scene, -1 - boost, 0);
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
	
	public void gravity(final Scene scene) {
		move(scene, 0, 2);
		/*if(y < 336) {
			falling = true;
			//System.out.println("falling");
		} else {
			falling = false;
			//System.out.println("not falling");
		}
		
		if(falling) {
			y = (int) (y + 0.5 * t);
			t = t + 0.20;
		} else {
			t = 0;
		}*/
		
	}
	
	@Override
	public void update(final Game game) {
		move(game.scene);
		gravity(game.scene);
	}

	@Override
	public void render(final Game game) {
		game.screen.render(sprite, x, y);
	}
}
