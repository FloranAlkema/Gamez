package engine.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class Sprite {
	private static final Map<String, Sprite> cache;

	public final int width, height;
	public final int[] pixels;

	private Sprite(final BufferedImage image) {
		width = image.getWidth();
		height = image.getHeight();
		pixels = image.getRGB(0, 0, width, height, null, 0, width);
	}

	static {
		cache = new HashMap<String, Sprite>();
	}

	/**
	 * This method loads an image
	 * 
	 * @param name
	 * @return
	 */
	private static BufferedImage loadImage(final String name) {
		try {
			return ImageIO.read(Sprite.class.getResource(name));
		} catch (final IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * This method returns an image in the cache, if not found, it will load it
	 * 
	 * @param name
	 * @return
	 */
	public static Sprite get(final String name) {
		Sprite sprite = cache.get(name);
		if (sprite == null) {
			cache.put(name, sprite = new Sprite(loadImage(name)));
		}

		return sprite;
	}

}
