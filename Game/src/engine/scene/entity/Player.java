package engine.scene.entity;

import engine.Game;
import engine.graphics.Sprite;
import engine.input.*;

public class Player extends Entity {
	private final Sprite sprite;
	static boolean left;
	public static void left(boolean val){
		left = val;
	}
	public Player() {
		sprite = Sprite.get("/player.png");
	}

	@Override
	public void update(final Game game) {
		
		if(left){
			x--;
		}
	}

	@Override
	public void render(final Game game) {
		game.screen.render(sprite, x, y);
	}
}
