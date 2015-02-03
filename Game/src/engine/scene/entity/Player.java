package engine.scene.entity;

import java.awt.Rectangle;

import engine.Game;
import engine.graphics.Counter;
import engine.graphics.Sprite;
import engine.scene.Scene;

public class Player extends Entity {
	private final Sprite sprite;
	static boolean left, right, down, up;
	static boolean sprint;
	int boost;
	private double t = 0;
	private double v = 0;
	public static double vv;
	private double a = 0.05d;
	public static boolean collisionTop, collisionRight, collisionLeft,
			collisionNext, closeToGround;
	public static int xx, yy, groundY;
	public static double vvv;
	public Rectangle playerNext = getBounds();

	public Player() {
		sprite = Sprite.get("/player.png");
		width = sprite.width;
		height = sprite.height;
		type = "Player";
	}

	public Player(int x, int y) {
		sprite = Sprite.get("/player.png");
		width = sprite.width;
		height = sprite.height;
		this.x = x;
		this.y = y;
		type = "Player";
	}


	public void checkCollisions() {
		collision = !true;
		Rectangle playerRect = getBounds();
		Rectangle playerRectPlus = getBounds();
		playerRectPlus.height += 1; //Bigger Player rectangle
		playerNext.x = 0;
		collisionTop = false;
		collisionLeft = false;
		collisionRight = false;
		collisionNext = false;
		for (Entity entity : Scene.entities) {
			if (entity != null) {
				if (entity.getType() == "Chicken") {
					Rectangle chicken = entity.getBounds();
					if (chicken.intersects(playerRect)) {
						Chicken.playSound();
						System.out.println("You gained some chicken!");
						Game.scene.removeEntity(entity);
						Counter.score++;
					}
				}
				if (entity.getType() == "Ground") {
					// System.out.println(entity);
					Rectangle top = entity.getTop();
					Rectangle left = entity.getLeft();
					Rectangle right = entity.getRight();
					
					if (top.intersects(playerRect)) { 		//player on ground
						collisionTop = true;
						//System.out.println("top");
					}
					if (top.intersects(playerRectPlus)) {	// player on ground + 1
						collisionNext = true;
						groundY = entity.getY() - this.height;
						//System.out.println("next");
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
			boost = 5;
		} else if (sprint && !collisionNext && boost == 5) {
			boost = 5;
		} else {
			boost = 0;
		}
	}

	public void gravity(final Scene scene) {
		if (collisionNext && !collisionTop && !up && v > 0) {
			v = 0;
			y = groundY;
		}
		if(collisionNext && up){
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
		gravity(game.scene);
		move(game.scene);
		//System.out.println(v);

		// System.out.println(v);
		yy = y;
		xx = x;
		vvv = v;
	}

	@Override
	public void render(final Game game) {
		game.screen.render(sprite, x, y);
	}
}