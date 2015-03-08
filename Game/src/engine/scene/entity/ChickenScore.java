package engine.scene.entity;


import engine.Game;
import engine.graphics.Sprite;

public class ChickenScore extends Entity {
	

	private final Sprite sprite;

	public ChickenScore() {
		this(0, 0);
	}

	public ChickenScore(int x, int y) {
		this(x, y, "ChickenScore.png");
		
	}

	public ChickenScore(int x, int y, String sprite) {
		this.sprite = Sprite.get("/" + sprite);
		this.x = x;
		this.y = y;
		width = this.sprite.width;
		height = this.sprite.height;
		type = "ChickenScore";
	}


	@Override
	public void update(final Game game) {

	}

	@Override
	public void render(final Game game) {
		game.screen.render(sprite, x, y);
	}

}
