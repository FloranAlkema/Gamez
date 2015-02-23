package engine.scene;

import engine.Game;
import engine.scene.entity.Entity;
import engine.scene.entity.Player;

public class ScrollControl extends Entity {
	// Variables
	int playerX;
	int playerY;

	/**
	 * Constructor Method
	 */
	public ScrollControl() {

	}

	public int getY() {
		playerY = Scene.entities[0].getY();
		return playerY;
	}

	public int getX() {
		if (Scene.entities[0] != null) {
			playerX = Scene.entities[0].getX();
		} else {
			playerX = 500;
		}
		return playerX;
	}

	boolean characterDead() {
		if (playerY > Game.HEIGHT) {
			System.out.println("You are dead - good bye");
			Game.reset();
			return true;
		} else {
			return false;
		}

	}

	boolean shouldScrollRightSlow() {
		return playerX > 960;
	}

	boolean shouldScrollLeftSlow() {
		return playerX < 320;
	}

	boolean shouldScrollRightFast() {
		return playerX > 1024;
	}

	boolean shouldScrollLeftFast() {
		return playerX < 256;
	}

	public void scroll() {
		if (shouldScrollLeftSlow() && !Player.startIntersect) {
			Game.start.x += 2;
			for (Entity entity : Scene.entities) {
				if (entity != null) {
					entity.setX(2);
				}
			}
		}
		if (shouldScrollRightSlow()) {
			Game.start.x += -2;
			for (Entity entity : Scene.entities) {
				if (entity != null) {
					entity.setX(-2);
				}
			}
		}
		if (shouldScrollLeftFast() && !Player.startIntersect) {
			Game.start.x += 4;
			for (Entity entity : Scene.entities) {
				if (entity != null) {
					entity.setX(4);
				}
			}
		}
		if (shouldScrollRightFast()) {
			Game.start.x += -4;
			for (Entity entity : Scene.entities) {
				if (entity != null) {
					entity.setX(-4);
				}
			}
		}

	}

	@Override
	public void update(Game game) {
		getX();
		getY();
		scroll();
		characterDead();
	}

	@Override
	public void render(Game game) {

	}
}
