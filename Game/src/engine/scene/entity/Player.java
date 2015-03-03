package engine.scene.entity;

import java.awt.Rectangle;

import engine.Game;
import engine.graphics.Counter;
import engine.graphics.Sprite;
import engine.scene.Scene;

public class Player extends Entity {
	// Variables
	private final Sprite sprite;

	public static int xx, yy, groundY;
	int boost;

	private double t = 0;
	private double v = 0;

	public static double vv;
	public static double vvv;
	private double a = 0.05d;

	public static boolean collisionTop, collisionRight, collisionLeft,
			collisionNext, closeToGround;
	public static boolean startIntersect;
	static boolean left, right, down, up;
	static boolean sprint;
	public static Rectangle playerRectPlus;
	public static Rectangle startArea = new Rectangle(-100, 0, 100, 5000);
	public Rectangle playerNext = getBounds();

	/**
	 * Constructor Method
	 */
	public Player() {
		sprite = Sprite.get("/player_idle.png");
		width = sprite.width;
		height = sprite.height;
		
	}

	public Player(int x, int y) {
		sprite = Sprite.get("/player_idle.png");
		width = sprite.width;
		height = sprite.height;
		this.x = x;
		this.y = y;
		type = "Player";
	}

	/**
	 * Checks collisions between Player and all objects
	 */
	public void checkCollisions() {
		collision = false;
		Rectangle playerRect = getBounds();
		playerRectPlus = getBounds();
		playerRectPlus.height += 1; // Bigger Player rectangle
		playerNext.x = 0;
		collisionTop = false;
		collisionLeft = false;
		collisionRight = false;
		collisionNext = false;
		if (startArea.intersects(playerRect)) {
			collisionRight = true;
		} else {
			collisionRight = false;
		}
		if (playerRect.intersects(Game.start)) {
			startIntersect = true;
		} else {
			startIntersect = false;
		}
		for (Entity entity : Scene.entities) {
			if (entity != null) {
				if (entity.getType() == "Chicken") {
					Rectangle chicken = entity.getBounds();
					if (chicken.intersects(playerRect)) {
						System.out.println("You gained some chicken!");
						Game.scene.removeEntity(entity);
						Counter.score++;
					}
				}
				if (entity.getType() == "Lava"
						&& entity.getBounds().intersects(playerRect)) {
					// You die
					Game.reset();
				}
				if (entity.getType() == "Ground") {
					// System.out.println(entity);
					Rectangle top = entity.getTop();
					Rectangle left = entity.getLeft();
					Rectangle right = entity.getRight();

					if (top.intersects(playerRect)) { // player on ground
						collisionTop = true;
						// System.out.println("top");
					}
					if (top.intersects(playerRectPlus)) { // player on ground +
															// 1
						collisionNext = true;
						groundY = entity.getY() - this.height;
						// System.out.println("next");
					}
					if (top.intersects(playerNext)) {
						v = 0.1;
						System.out.println("top");
					}

					if (left.intersects(playerRect)) {
						collisionLeft = true;
						System.out.println("left");
					}

					if (right.intersects(playerRect)) {
						collisionRight = true;
						System.out.println("right");
					}
				}
			}
		}

	}

	public static void left(boolean val) {
		left = val;
		// System.out.println("left val: " + left);
	}

	public static void up(boolean val) {
		up = val;
		// System.out.println("up val: " + up);
	}

	public static void down(boolean val) {
		down = val;
		// System.out.println("down val: " + down);
	}

	public static void right(boolean val) {
		right = val;
		// System.out.println("right val: " + right);
	}

	public static void sprint(boolean val) {
		sprint = val;
	}

	public void move(final Scene scene) {
		if (left && right) {
			// stand still
		} else if (right && !collisionLeft) {
			x = x + 1 + boost;
			move(scene, 1 + boost, 0);
		} else if (left && !collisionRight) {
			x = x - 1 - boost;
			move(scene, -1 - boost, 0);
		}
		if (down) {
			// y++;
		}
		if (up) {
			if (collisionTop) {
				y--;
			}
		}
		if (sprint && collisionNext && boost == 0) {
			boost = 3;
		} else if (sprint && boost == 3) {
			boost = 3;
		} else {
			boost = 0;
		}
	}

	public void gravity(final Scene scene) {
		if (collisionNext && !collisionTop && !up && v > 0) {
			v = 0;
			y = groundY;
		}
		if (collisionNext && up) {
			y--;
			v = -4;
		}

		if (collisionTop && collisionNext) {
			// on ground
			y--;

			v = 0;
			vv = 0;
			t = 0;
		}
		if (!collisionTop && !collisionNext) {
			// falling
			y = (int) (y + v * t);
			v = v + t * a;
			if (v > 1) {
				v = 1;
			}
			t += 0.2;
		}

	}

	@Override
	public void update(final Game game) {
		checkCollisions();
		gravity(Game.scene);
		move(Game.scene);
	
		yy = y;
		xx = x;
		vvv = v;
	}

	@Override
	public void render(final Game game) {
		game.screen.render(sprite, x, y);
	}
}