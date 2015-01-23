package engine.scene.entity;

import engine.Game;
import engine.graphics.Sprite;

public class Chicken extends Entity {

	private final Sprite sprite;

	public Chicken() {
		this(0, 0);
		type = "Chicken";
	}

	public Chicken(int x, int y) {
		this(x, y, "Chicken.png");
		type = "Chicken";
	}

	public Chicken(int x, int y, String sprite) {
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
