package engine.scene.entity;

import java.awt.Rectangle;

import engine.Game;
import engine.scene.Scene;

public abstract class Entity {
	private Rectangle bounds;

	public int x, y, width, height;
	public boolean removable;
	public int velocity;
	public boolean falling;
	public boolean collision;

	/**
	 * This method is used to update the entity
	 * 
	 * @param game
	 */
	public abstract void update(final Game game);

	public Rectangle getBounds() {
		if (bounds == null) {
			bounds = new Rectangle(x, y, width, height);
		} else {
			bounds.setBounds(x, y, width, height);
		}

		return bounds;
	}

	/**
	 * This method is called after a collision with the entity and should return
	 * true if it's a passable collision
	 * 
	 * @param entity
	 * @return
	 */
	public boolean onCollision(final Entity entity) {
		return false;
	}

	protected boolean move(final Scene scene, final int x, final int y) {
		final int xx = this.x;
		final int yy = this.y;
		this.x += x;
		this.y += y;

		final Rectangle bounds = getBounds();
		for (final Entity entity : scene.entities)
			if (entity != null && entity != this
					&& bounds.intersects(entity.getBounds()))
				if (!onCollision(entity) || !entity.onCollision(this)) {
					this.x -= x;
					this.y -= y;
					break;
				}

		return this.x != xx || this.y != yy;
	}

	/**
	 * This method is used to render the entity
	 * 
	 * @param game
	 */
	public abstract void render(final Game game);

}
