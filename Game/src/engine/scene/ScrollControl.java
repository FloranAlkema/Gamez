package engine.scene;

import engine.Game;
import engine.scene.entity.Entity;
import engine.scene.entity.Player;

public class ScrollControl extends Entity {
	int playerX;
	int playerY;

	public ScrollControl() {

	}

	public int getY() {
		playerY = Game.scene.entities[0].getY();
		return playerY;
	}

	public int getX() {
		playerX = Game.scene.entities[0].getX();
		return playerX;
	}

	boolean characterDead() {
		if (playerY > Game.HEIGHT) {
			System.out.println("You are dead - good bye");
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
			Player.start.x += 2;
			for (Entity entity : Game.scene.entities) {
				if (entity != null) {
					entity.setX(2);
				}
			}
		}
		if (shouldScrollRightSlow()) {
			Player.start.x += -2;
			for (Entity entity : Game.scene.entities) {
				if (entity != null) {
					entity.setX(-2);
				}
			}
		}
		if (shouldScrollLeftFast() && !Player.startIntersect) {
			Player.start.x += 4;
			for (Entity entity : Game.scene.entities) {
				if (entity != null) {
					entity.setX(4);
				}
			}
		}
		if (shouldScrollRightFast()) {
			Player.start.x += -4;
			for (Entity entity : Game.scene.entities) {
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
		// TODO Auto-generated method stub

	}
}
