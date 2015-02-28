package engine.scene.entity;

import engine.Game;
import engine.graphics.Sprite;

public class Lava extends Entity {
	private final Sprite sprite;

	public Lava() {
		this(0, 0);
		type = "Lava";
	}

	public Lava(int x, int y) {
		this(x, y, "lava.png");
		
	}

	public Lava(int x, int y, String sprite) {
		this.sprite = Sprite.get("/" + sprite);
		this.x = x;
		this.y = y;
		width = this.sprite.width;
		height = this.sprite.height;
		type = "Lava";
	}

	@Override
	public void update(final Game game) {

	}

	@Override
	public void render(final Game game) {
		game.screen.render(sprite, x, y);
	}
}
