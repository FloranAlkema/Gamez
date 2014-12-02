package engine.scene.entity;

import engine.Game;
import engine.graphics.Sprite;

public class Ground extends Entity{
	private final Sprite sprite;
	
	public Ground() {
		sprite = Sprite.get("/Floor.png");
	}
	
	@Override
	public void update(final Game game) {
		x++;
	}
	

	@Override
	public void render(final Game game) {
		game.screen.render(sprite, x, y);
	}
}
