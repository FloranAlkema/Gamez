package engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import engine.graphics.Counter;
import engine.graphics.Screen;
import engine.input.Keyboard;
import engine.input.Mouse;
import engine.scene.Scene;
import engine.scene.ScrollControl;
import engine.scene.entity.Chicken;
import engine.scene.entity.Ground;
import engine.scene.entity.Lava;
import engine.scene.entity.Player;

public class Game extends JFrame implements Runnable {
	// Variables
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final int DIMENSION = WIDTH * HEIGHT;
	public static Rectangle start = new Rectangle(0, 0, 320, 5000);
	private boolean running;
	private Thread thread;
	private int updates, frames;
	public static Scene scene = null;
	static BufferedImage chicken = null;
	private final BufferedImage image;
	private final int[] pixels;

	public final Keyboard keyboard;
	public final Mouse mouse;
	public final Screen screen;

	// ImageIcon scoreIcon = new ImageIcon(
	// "/Game/src/ChickenScore.png");
	// Image chicken = scoreIcon.getImage();
	// Image chicken;

	// Game constructor
	public Game() {
		
		

		setTitle("How to be a dEEEEM niggeR");
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

	public static void reset() {// on game start & death
		Counter.score = 0; 							// reset chicken score
		start = new Rectangle(0, 0, 320, 5000);		//reset start rectangle
		scene = null;
		scene = new Scene();
		
		for (int i = 0; i < scene.entities.length; i++) { // Clear all entities
			scene.entities[i] = null;

		}
		//add all entities
		scene.addEntity(new Player(0, 320));
		scene.addEntity(new Ground(0, 500));
		scene.addEntity(new Lava(1200, 420));
		scene.addEntity(new Chicken(150, 320));
		scene.addEntity(new Chicken(200, 320));
		scene.addEntity(new Chicken(250, 320));
		scene.addEntity(new Chicken(300, 320));
		scene.addEntity(new Chicken(350, 320));
		scene.addEntity(new Chicken(400, 300));
		scene.addEntity(new Chicken(450, 300));
		scene.addEntity(new ScrollControl());

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
		new Background(g);								//background trials
		g.drawImage(image, 0, 0, null);					//draw the screen/entities
		g.drawImage(chicken, 1150, 40, null);			//draw chicken score image -- not working
		g.setColor(Color.WHITE);
		g.drawString("speed: " + Player.vvv, 50, 110); 	// draw player speed
		g.drawString("X: " + Player.xx, 50, 120);		//draw player x
		g.drawString("Y: " + Player.yy, 50, 130);		//draw player y
		// g.drawString("FPS: " + updates, 50, 140);
		g.drawString("= " + Counter.score, 1210, 80);	//draw chicken score text
		g.dispose();
		buffer.show();

		frames++;
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
