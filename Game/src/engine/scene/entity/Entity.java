package engine.scene.entity;

import engine.Game;

public abstract class Entity {
	public int x, y;
	public boolean removable;
	
	/**
	 * This method is used to update the entity
	 * 
	 * @param game
	 */
	public abstract void update(final Game game);
	
	/**
	 * This method is used to render the entity
	 * 
	 * @param game
	 */
	public abstract void render(final Game game);

}
