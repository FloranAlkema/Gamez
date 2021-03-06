package engine.scene.entity;

import engine.Game;
import engine.graphics.Sprite;

public class Water extends Entity {
	private final Sprite sprite;

	public Water() {
		this(0, 0);
		type = "Water";
	}

	public Water(int x, int y) {
		this(x, y, "water.png");
		
	}

	public Water(int x, int y, String sprite) {
		this.sprite = Sprite.get("/" + sprite);
		this.x = x;
		this.y = y;
		width = this.sprite.width;
		height = this.sprite.height;
		type = "Water";
	}

	@Override
	public void update(final Game game) {

	}

	@Override
	public void render(final Game game) {
		game.screen.render(sprite, x, y);
	}
}
