package engine.scene.entity;

import engine.Game;
import engine.graphics.Sprite;
import engine.input.*;

public class Player extends Entity {
	private final Sprite sprite;
	static boolean left, right, down, up;

	public static void left(boolean val) {
		left = val;
		System.out.println("left val: " + left);
	}

	public static void up(boolean val) {
		up = val;
		System.out.println("left val: " + up);
	}

	public static void down(boolean val) {
		down = val;
		System.out.println("left val: " + down);
	}

	public static void right(boolean val) {
		right = val;
		System.out.println("left val: " + right);
	}

	public Player() {
		sprite = Sprite.get("/player.png");
	}

	@Override
	public void update(final Game game) {

		if (left) {
			x--;
		}else if(right){
			x++;
		}else{}
		if(down){
			y++;
		}else if(up){
			y--;
		}
	}

	@Override
	public void render(final Game game) {
		game.screen.render(sprite, x, y);
	}
}
