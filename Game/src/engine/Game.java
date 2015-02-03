package engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import engine.graphics.Counter;
import engine.graphics.Screen;
import engine.input.Keyboard;
import engine.input.Mouse;
import engine.scene.Scene;
import engine.scene.entity.Chicken;
import engine.scene.entity.Ground;
import engine.scene.entity.Player;

public class Game extends JFrame implements Runnable {
	// Variables
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final int DIMENSION = WIDTH * HEIGHT;

	private boolean running;
	private Thread thread;
	private int updates, frames;
	public static Scene scene = null;

	private final BufferedImage image;
	private final int[] pixels;

	public final Keyboard keyboard;
	public final Mouse mouse;
	public final Screen screen;

	ImageIcon scoreIcon = new ImageIcon(
			"C:/Users/Floran/git/Gamez/Game/src/chickenScore.png");
	Image chicken = scoreIcon.getImage();

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

		scene = new Scene();


		scene.addEntity(new Ground(0, 500));
		scene.addEntity(new Player(0, 320));
		scene.addEntity(new Chicken(150, 320));
		scene.addEntity(new Chicken(200, 320));
		scene.addEntity(new Chicken(250, 320));
		scene.addEntity(new Chicken(300, 320));
		scene.addEntity(new Chicken(350, 320));
		scene.addEntity(new Chicken(400, 300));
		scene.addEntity(new Chicken(450, 300));
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
		g.drawImage(chicken, 1150, 40, null);
		g.setColor(Color.WHITE);
		g.drawString("speed: " + Player.vvv, 50, 110);
		g.drawString("X: " + Player.xx, 50, 120);
		g.drawString("Y: " + Player.yy, 50, 130);
		// g.drawString("FPS: " + updates, 50, 140);
		g.drawString("= " + Counter.score, 1210, 80);
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
