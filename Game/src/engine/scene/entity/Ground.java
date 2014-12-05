package engine.scene.entity;

import java.awt.Rectangle;
import engine.scene.Scene;
import engine.Game;
import engine.graphics.Sprite;

public class Ground extends Entity{
	private final Sprite sprite;

	
	public Ground() {
		sprite = Sprite.get("/Floor.png");
		x = 0; 
		y = 0;
	}
	public Ground(int x, int y){
		sprite = Sprite.get("/Floor.png");
		this.x = x;
		this.y = y;
	}
	public Ground(int newx, int newy, String sprite){
		this.sprite = Sprite.get("/" + sprite);
		this.x = newx;
		this.y = newy;
	}
	
		public Rectangle getBounds() {
	        return new Rectangle(x, y, sprite.width, sprite.height);
	    }
	 
		public void checkCollisions(){
		Entity player = Game.scene.entities[0];
		Rectangle rect = getBounds();
		Rectangle player1 = new Rectangle(player.x, player.y, player.width, player.height);
		if(rect.intersects(player1)){System.out.println("Hit floor");}else{
			//System.out.println("hit nothing");
			};
		}
		
		
	@Override
	public void update(final Game game) {
		checkCollisions(); 
	}
	

	@Override
	public void render(final Game game) {
		game.screen.render(sprite, x, y);
	}
}
