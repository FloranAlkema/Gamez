package engine.graphics;

import engine.Game;

public class Screen {
	public final int[] pixels;
	
	public Screen() {
		 pixels = new int[Game.WIDTH * Game.HEIGHT];
	}
	
	/**
	 * This method renders a sprite at a given position on the screen
	 * 
	 * @param sprite
	 * @param xPos
	 * @param yPos
	 */
	public void render(final Sprite sprite, final int xPos, final int yPos) {
		render(sprite, xPos, yPos, 0);
	}
	
	/**
	 * This method renders a sprite at a given position with an overlay color
	 * 
	 * @param sprite
	 * @param xPos
	 * @param yPos
	 * @param overlay
	 */
	public void render(final Sprite sprite, final int xPos, final int yPos, final int overlay) {
        for (int x = 0; x < sprite.width; x++) {
            final int xx = x + xPos;
            if (xx > Game.WIDTH || xx < 0) {
                continue;
            }
 
            for (int y = 0; y < sprite.height; y++) {
                final int yy = y+ yPos;
                if (yy > Game.HEIGHT || yy < 0) {
                    continue;
                }
 
                final int result = xx + yy * Game.WIDTH;
                if (result >= 0 && result < Game.DIMENSION) {
                    pixels[result] = sprite.pixels[x + y * sprite.width] + overlay;
                }
            }
        }
    }
	
}
