package engine.scene.entity;

import engine.Game;
import engine.graphics.Sprite;
import engine.scene.Scene;

public class Player extends Entity {
	private final Sprite sprite;
	static boolean left, right, down, up;
	static boolean sprint;
	int boost;
	private double t = 0;
	private double jumpT = 0;
	private double v = 0;
	public static double vv;
	private double a = 0.05;
	public static boolean collision;
	
	public Player() {
		sprite = Sprite.get("/player.png");
		width = sprite.width;
		height = sprite.height;
	}
	public Player(int x, int y) {
		sprite = Sprite.get("/player.png");
		width = sprite.width;
		height = sprite.height;
		this.x = x;
		this.y = y;
	}

	

	// static int width = sprite.width;
	// static int height;
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
		} else if (right) {
			x = x + 1 + boost;
			move(scene, 1 + boost, 0);
		} else if (left) {
			x = x - 1 - boost;
			move(scene, -1 - boost, 0);
		}
		if (down) {
			//y++;
		} else if (up) {
			if(collision){v = -3; y--;}
		}
		if (sprint) {
			boost = 1;
		} else {
			boost = 0;
		}
	}
	
	public void gravity(final Scene scene) {
		
		
		
		if(!collision) {
			//falling
			y = (int)(y + v * t);
			v = v + t * a;
			t += 0.2;
		} else {
			//on ground
			v = 0;
			if(up){v=-3;}
			vv = v;
			//y--;
			t = 0;
			
		}
		
		
		
	}
	
	@Override
	public void update(final Game game) {
		//Ground.checkCollisions();
		move(game.scene);
		gravity(game.scene);
		System.out.println(v);
	}

	@Override
	public void render(final Game game) {
		game.screen.render(sprite, x, y);
	}
}
