package engine.scene.entity;

import java.awt.Rectangle;

import engine.Game;
import engine.graphics.Sprite;
import engine.scene.Scene;

public class Player extends Entity {
	private final Sprite sprite;
	static boolean left, right, down, up;
	static boolean sprint;
	int boost;
	private double t = 0, tNext = 0;
	private double jumpT = 0;
	private double v = 0, vNext = 0, yNext = 0;
	public static double vv;
	private double a = 0.05;
	public static boolean collisionTop, collisionRight, collisionLeft;
	public static int xx, yy;
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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void checkCollisions() {
		collision = !true;
		Rectangle playerRect = getBounds();
		playerNext.x = 0;
		collisionTop = false;
		collisionLeft = false;
		collisionRight = false;
		for (Entity entity : Scene.entities) {
			if (entity != null) {
				if (entity.getType() == "Ground") {
					System.out.println(entity);
					Rectangle top = entity.getTop();
					Rectangle left = entity.getLeft();
					Rectangle right = entity.getRight();

					if (top.intersects(playerRect)) {
						collisionTop = true;
						System.out.println("top");
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

	public boolean intersect(int location1, int width1, int location2,
			int width2) {
		Rectangle rect1 = new Rectangle(location1, 1, location1 + width1, 1);
		Rectangle rect2 = new Rectangle(location2, 1, location2 + width2, 1);
		return rect1.intersects(rect2);
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
		if (sprint && collisionTop && boost == 0) {
			boost = 1;
		} else if (sprint && !collisionTop && boost == 1) {
			boost = 1;
		} else {
			boost = 0;
		}
	}

	public void gravity(final Scene scene) {

		if (collisionTop) {
			// on ground
			v = 0;
			vv = 0;
			if (up) {
				v = -3;
			}
			t = 0;
		}
		if (!collisionTop) {
			// falling
			y = (int) (y + v * t);
			v = v + t * a;
			if (v > 1) {
				v = 1;
			}
			t += 0.2;
			tNext = t + 0.2;
			yNext = (int) (yNext + vNext * t);
			vNext = vNext + tNext * a;
			if (vNext > 1) {
				vNext = 1;
			}
			playerNext.y = (int) yNext;
		}

	}

	@Override
	public void update(final Game game) {
		checkCollisions();
		gravity(game.scene);
		move(game.scene);

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
