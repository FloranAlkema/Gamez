package engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import engine.graphics.Screen;
import engine.input.Keyboard;
import engine.input.Mouse;
import engine.scene.Scene;
import engine.scene.entity.Chicken;
import engine.scene.entity.Ground;
import engine.scene.entity.Player;

public class Game extends JFrame implements Runnable {
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final int DIMENSION = WIDTH * HEIGHT;

	private boolean running;
	private Thread thread;
	@SuppressWarnings("unused")
	private int updates, frames;
	public static Scene scene = null;

	private final BufferedImage image;
	private final int[] pixels;
	public final Screen screen;

	public final Keyboard keyboard;
	public final Mouse mouse;

	public Game() {
		setTitle("How to be a dEEEEM niggeR");
		setSize(WIDTH, HEIGHT);
		setResizable(false);

		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		addKeyListener(keyboard = new Keyboard());
		addMouseListener(mouse = new Mouse());

		// scene = new Scene();
		// gedaan in variable declaration voor static

		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		screen = new Screen();

		scene = new Scene();

		scene.addEntity(new Ground(400, 380));

		scene.addEntity(new Ground(0, 400));
		scene.addEntity(new Player(0, 320));
		scene.addEntity(new Chicken(150, 320));
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
		g.drawImage(image, 0, 0, null);
		g.setColor(Color.WHITE);
		g.drawString("speed: " + Player.vvv, 100, 110);
		g.drawString("X: " + Player.xx, 100, 120);
		g.drawString("Y: " + Player.yy, 100, 130);
	//	g.drawString("FPS: " + updates, 100, 140);
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
