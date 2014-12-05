package engine.scene.entity;

import java.awt.Rectangle;

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
		Rectangle rect = this.getBounds();
		Rectangle player = Player.getBounds();
		}
	//*/
	
	@Override
	public void update(final Game game) {
		
	}
	

	@Override
	public void render(final Game game) {
		game.screen.render(sprite, x, y);
	}
}
