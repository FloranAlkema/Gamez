package script.scene.entity;

import engine.Game;
import engine.graphics.Sprite;
import engine.scene.entity.Entity;

public class FloranEntity extends Entity {
	private final Sprite sprite;
	
	public FloranEntity() {
		sprite = Sprite.get("/testimg.jpg");
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
