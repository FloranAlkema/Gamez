package engine.scene.entity;

import java.awt.Rectangle;
import engine.scene.Scene;
import engine.Game;
import engine.graphics.Sprite;

public class Ground extends Entity{
	private final Sprite sprite;

	public Ground() {
		this(0, 0);
	}
	
	public Ground(int x, int y){
		this(x, y, "Floor.png");
	}
	
	public Ground(int x, int y, String sprite){
		this.sprite = Sprite.get("/" + sprite);
		this.x = x;
		this.y = y;
		width = this.sprite.width;
		height = this.sprite.height;
	}
	
		/*public void checkCollisions(){
		Entity player = Game.scene.entities[0];
		Rectangle rect = getBounds();
		Rectangle player1 = new Rectangle(player.x, player.y, player.width, player.height);
		if(rect.intersects(player1)){System.out.println("Hit floor");}else{
			//System.out.println("hit nothing");
			};
		}
		*/
		
	@Override
	public void update(final Game game) {
		//checkCollisions(); 
	}
	

	@Override
	public void render(final Game game) {
		game.screen.render(sprite, x, y);
	}
}
