package engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import engine.graphics.Counter;
import engine.graphics.Screen;
import engine.input.Keyboard;
import engine.input.Mouse;
import engine.scene.Scene;
import engine.scene.ScrollControl;
import engine.scene.entity.Chicken;
import engine.scene.entity.ChickenScore;
import engine.scene.entity.Cloud;
import engine.scene.entity.Ground;
import engine.scene.entity.Lava;
import engine.scene.entity.Player;
import engine.scene.entity.RedBlock;
import engine.scene.entity.RedRectangle;
import engine.scene.entity.Water;

public class Game extends JFrame implements Runnable {

	// Variables
	private static final long serialVersionUID = 5143562850097544518L;
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final int DIMENSION = WIDTH * HEIGHT;

	public static Rectangle start = new Rectangle(0, 0, 320, 5000);

	public int updates, frames;
	private final int[] pixels;

	private boolean running;
	private Thread thread;

	public static Scene scene = null;
	private final BufferedImage image;

	public final Keyboard keyboard;
	public final Mouse mouse;
	public final Screen screen;
	Image chicken;

	/**
	 * Constructor Method
	 */
	public Game() {
		setTitle("How to be black");
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		addKeyListener(keyboard = new Keyboard());
		addMouseListener(mouse = new Mouse());

		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		screen = new Screen();
		reset();
		try {
			ImageIcon scoreIcon = new ImageIcon("ChickenScore.png");
			chicken = scoreIcon.getImage();
		} catch (Exception e) {
			e.printStackTrace();
		}
		setVisible(true);

	}

	/**
	 * Starts the thread and loop cycle
	 */
	public void start() {
		thread = new Thread(this);
		thread.start();
		running = true;

	}

	/**
	 * This method deletes and adds the entities whenever the player is dead.
	 */
	public static void endGame() {
		scene = null;
		scene = new Scene();

		for (int i = 0; i < Scene.entities.length; i++) {
			Scene.entities[i] = null;
		}
		scene.addEntity(new ChickenScore(1050, 40));
	}

	public static void reset() {
		Counter.score = 0;
		start = new Rectangle(0, 0, 320, 5000);
		scene = null;
		scene = new Scene();

		for (int i = 0; i < Scene.entities.length; i++) {
			Scene.entities[i] = null;

		}

		scene.addEntity(new Player(0, 320));
		scene.addEntity(new ChickenScore(1050, 40));
		scene.addEntity(new Ground(-10, 625));
		scene.addEntity(new Lava(780, 650));
		scene.addEntity(new Chicken(600, 350));
		scene.addEntity(new Chicken(1640, 540));
		scene.addEntity(new RedRectangle(855, 500));
		scene.addEntity(new Ground(1150, 625));
		scene.addEntity(new Chicken(150, 550));
		scene.addEntity(new Chicken(930, 320));
		scene.addEntity(new Chicken(1300, 400));
		scene.addEntity(new RedBlock(1500, 520));
		scene.addEntity(new RedBlock(1600, 420));
		scene.addEntity(new RedBlock(1700, 320));
		scene.addEntity(new RedBlock(1800, 220));
		scene.addEntity(new RedBlock(1900, 120));
		scene.addEntity(new RedBlock(2150, 120));
		scene.addEntity(new Chicken(2170, 50));
		scene.addEntity(new RedBlock(2400, 120));
		scene.addEntity(new RedRectangle(2000, 600));
		scene.addEntity(new Chicken(2060, 550));
		scene.addEntity(new RedRectangle(2250, 600));
		scene.addEntity(new Chicken(2310, 550));
		scene.addEntity(new Chicken(2700, 370));
		scene.addEntity(new Ground(2500, 625));
		scene.addEntity(new Water(3290, 650));
		scene.addEntity(new Cloud(3290, 500, 200, 0));
		scene.addEntity(new Ground(3680, 625));
		scene.addEntity(new Chicken(4400, 450));
		scene.addEntity(new ScrollControl());
		Counter.deaths++;
	}

	/**
	 * Updates the scene
	 */
	private void update() {
		scene.update(this);
		updates++;

	}

	/**
	 * Renders the scene (todo: widgets etc)
	 */
	private void render() {
		final BufferStrategy buffer = getBufferStrategy();
		if (buffer == null) {
			createBufferStrategy(3);
			return;
		}

		final Graphics g = buffer.getDrawGraphics();
		Arrays.fill(screen.pixels, 0x000000);

		scene.render(this);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		// g.drawImage(sprite, 0, 0, null);
		g.drawImage(image, 0, 0, null); // draw the screen/entities
		g.drawImage(chicken, 500, 300, null); // draw chicken score image -- not
												// working

		g.setColor(Color.WHITE);
		Font font = new Font("Calibri", Font.BOLD, 50);
		g.setFont(font);
		g.drawString("= " + Counter.score, 1150, 80); // draw chicken score
		win(g); // win check method
		g.dispose();
		buffer.show();

		frames++;
	}

	public void win(Graphics g) {
		if (Counter.score == 10) {
			Font font2 = new Font("Calibri", Font.BOLD, 20);
			g.setColor(Color.WHITE);
			g.setFont(font2);
			g.drawString(
					"Thank you for playing How To Be Black - Have a nice day! :-)",
					Game.WIDTH / 3, Game.HEIGHT / 2);
			g.drawString("Press ESC to close the Game", Game.WIDTH / 3,
					Game.HEIGHT / 2 + 300);
			if (Counter.deaths == 1) {
				g.drawString("You died 1 time!", Game.WIDTH / 3,
						Game.HEIGHT / 2 + 100);
			} else {
				g.drawString("You died " + Counter.deaths + " times!",
						Game.WIDTH / 3, Game.HEIGHT / 2 + 100);
			}
			endGame();
		}
	}

	/**
	 * Main game cycle, this executes the update and render method 60x per
	 * second
	 */
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double unprocessed = 0;
		double updateRate = 1000000000.0 / 60;
		long system = System.currentTimeMillis();

		requestFocus();

		while (running) {
			final long now = System.nanoTime();
			unprocessed += (now - lastTime) / updateRate;
			lastTime = now;

			while (unprocessed >= 1) {
				update();
				render();
				unprocessed -= 1;
			}

			if (System.currentTimeMillis() - system > 1000) {
				// System.out.println(String.format(
				// "[Game] - updates: %s, frames: %s", updates, frames));
				updates = 0;
				frames = 0;
				system += 1000;
			}
		}
	}

}
