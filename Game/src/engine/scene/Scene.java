package engine.scene;

import engine.Game;
import engine.scene.entity.Entity;

public class Scene {
	public static Entity[] entities = null;
	
	public Scene() {
		entities = new Entity[1024];
	}
	
	/**
	 * This method finds the first null entity in the list and puts a given there
	 * 
	 * @param entity
	 */
	public void addEntity(final Entity entity) {
		for (int i = 0; i < entities.length; i++) {
			if (entities[i] == null) {
				entities[i] = entity;
				System.out.println(i + " " + entity);
				return;
			}
		}
	}
	
	public void removeEntity(final Entity entity){
		for(int i = 0; i < entities.length; i++){
			if (entities[i] == entity){
				entities[i] = null;
			}
		}
	}
	
	/**
	 * This method updates all the entities
	 * 
	 * @param game
	 */
	public void update(final Game game) {
		
		for (int i = 0; i < entities.length; i++) {
			final Entity entity = entities[i];
			if (entity != null) {
				if (entity.removable) {
					entities[i] = null;
				} else {
					entity.update(game);
				}
			}
		}
	}
	
	/**
	 * This method renders all the entities
	 * 
	 * @param game
	 */
	public void render(final Game game) {
		for (int i = 0; i < entities.length; i++) {
			final Entity entity = entities[i];
			if (entity != null) {
				entity.render(game);
			}
		}
	}

}
