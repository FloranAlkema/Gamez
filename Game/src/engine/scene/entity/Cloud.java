package engine.scene.entity;

import engine.Game;
import engine.graphics.Sprite;

public class Cloud extends Entity {

	private final Sprite sprite;
	int myX = 0, myY = 0, dX, dY;
	boolean goingRight, goingUp;
	public Cloud() {
		this(0, 0);
		type = "Ground";
	}

	public Cloud(int x, int y) {
		this(x, y, 0, 0);

	}
	public Cloud(int x, int y, int dX, int dY){
		this(x, y, dX, dY, "Cloud.png");
	}

	public Cloud(int x, int y, int dX, int dY, String sprite) {
		this.sprite = Sprite.get("/" + sprite);
		this.x = x;
		this.y = y;
		this.dX = dX;
		this.dY = dY;
		width = this.sprite.width;
		height = this.sprite.height;
		type = "Ground";
	}
	
	
	public void moveCloud() {
		if(goingRight && myX < dX){
			myX++; x++;
		}else if(goingRight && myX == dX){
			goingRight = false;
		}else if(!goingRight && myX > 0){
			myX--; x--;
		}else if(!goingRight && myX == 0){
			goingRight = true;
		}
	}

	@Override
	public void update(final Game game) {
		moveCloud();
	}

	@Override
	public void render(final Game game) {
		game.screen.render(sprite, x, y);
	}

}
