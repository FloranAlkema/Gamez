package engine.scene.entity;

import engine.Game;
import engine.graphics.Sprite;

public class GrassBlock extends Entity {
	private final Sprite sprite;

	public GrassBlock() {
		this(0, 0);
		type = "Ground";
	}

	public GrassBlock(int x, int y) {
		this(x, y, "grass_block.png");
		type = "Ground";
	}

	public GrassBlock(int x, int y, String sprite) {
		this.sprite = Sprite.get("/" + sprite);
		this.x = x;
		this.y = y;
		width = this.sprite.width;
		height = this.sprite.height;
	}

	@Override
	public void update(final Game game) {

	}

	@Override
	public void render(final Game game) {
		game.screen.render(sprite, x, y);
	}
}
