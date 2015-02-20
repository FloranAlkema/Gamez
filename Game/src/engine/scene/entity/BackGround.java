package engine.scene.entity;

import engine.Game;
import engine.graphics.Sprite;

public class BackGround extends Entity {
	private final Sprite sprite;

	public BackGround() {
		this(0, 0);
		type = "BackGround";
	}

	public BackGround(int x, int y) {
		this(x, y, "Background2.png");
		type = "BackGround";
	}

	public BackGround(int x, int y, String sprite) {
		this.sprite = Sprite.get("/" + sprite);
		this.x = x;
		this.y = y;
		width = this.sprite.width;
		height = this.sprite.height;
	}

	@Override
	public void update(final Game game) {
		// checkCollisions();
	}

	@Override
	public void render(final Game game) {
		game.screen.render(sprite, x, y);
	}
}
