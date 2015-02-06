package engine.scene;

import engine.Game;
import engine.scene.entity.Player;


public class ScrollControl {
	int playerX;
	
	public ScrollControl() {

	}
	void getX(){
		playerX = Game.scene.entities[0].getX();
	}
	boolean shouldScrollRight(){
		System.out.println("Scroll right");
		return playerX>960;
	}
	boolean shouldScrollLeft(){
		System.out.println("Scroll Left");
		return playerX<320;
	}
	void check(){
		shouldScrollLeft();
		shouldScrollRight();
		
	}
	
	public void update(){
		check();
	}
}
