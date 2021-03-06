package engine.scene.entity;

import engine.Game;
import engine.graphics.Sprite;

public class Ground extends Entity {
	private final Sprite sprite;

	public Ground() {
		this(0, 0);
		
	}

	public Ground(int x, int y) {
		this(x, y, "grass_single_tile.png");
		type = "Ground";
	}

	public Ground(int x, int y, String sprite) {
		this.sprite = Sprite.get("/" + sprite);
		this.x = x;
		this.y = y;
		width = this.sprite.width;
		height = this.sprite.height;
		type = "Ground";
	}

	@Override
	public void update(final Game game) {

	}

	@Override
	public void render(final Game game) {
		game.screen.render(sprite, x, y);
	}
}
