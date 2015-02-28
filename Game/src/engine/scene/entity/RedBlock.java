package engine.scene.entity;

import engine.Game;
import engine.graphics.Sprite;

public class RedBlock extends Entity {
	private final Sprite sprite;

	public RedBlock() {
		this(0, 0);
		type = "Ground";
	}

	public RedBlock(int x, int y) {
		this(x, y, "redblock.png");
		
	}

	public RedBlock(int x, int y, String sprite) {
		this.sprite = Sprite.get("/" + sprite);
		this.x = x;
		this.y = y;
		width = this.sprite.width;
		height = this.sprite.height;
		type = "Ground";
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
