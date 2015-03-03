package engine.scene.entity;

import engine.Game;
import engine.graphics.Sprite;

public class Cloud extends Entity {

	private final Sprite sprite;
	int myX = 0, myY = 0, dX, dY;							// Bijhouden van locatie in beweging en verplaatsing afstand
	boolean goingRight = true, goingUp = true;				// Status van beweging
	
	public Cloud() {
		this(0, 0);
	}

	public Cloud(int x, int y) {
		this(x, y, 0, 0);						//geen verplaatsing

	}
	public Cloud(int x, int y, int dX, int dY){	//input verplaatsing
		this(x, y, dX, dY, "Cloud.png");
	}

	public Cloud(int x, int y, int dX, int dY, String sprite) {
		this.sprite = Sprite.get("/" + sprite);
		this.x = x;
		this.y = y;
		this.dX = dX;
		this.dY = dY;
		width = this.sprite.width;
		height = this.sprite.height;
		type = "Ground";
	}
	public boolean playerOn(){
		if(this.getBounds().intersects(Player.playerRectPlus)){
			return true;
		}	else	{
			return false;}
	}
	
	
	public void moveCloud() {

		if(goingRight && myX < dX){				//
			myX++; x++;							//
		}else if(goingRight && myX == dX){		//	
			goingRight = false;					// Verplaatsing in X richting met lengte dX vanaf spawnpunt
		}else if(!goingRight && myX > 0){		//
			myX--; x--;							//
		}else if(!goingRight && myX == 0){		//	
			goingRight = true;					//
		}
		
		if(goingUp && myY < dY){				//
			myY++; y++;	//Player.y++;				//
		}else if(goingUp && myY == dY){			//	
			goingUp = false;					// Verplaatsing in Y richting met hoogte dY vanaf spawnpunt
		}else if(!goingUp && myY > 0){			//
			myY--; y--;	//Player.y--;				//
		}else if(!goingUp && myY == 0){			//	
			goingUp = true;						//
		}
	}

	@Override
	public void update(final Game game) {
		moveCloud();
	}

	@Override
	public void render(final Game game) {
		game.screen.render(sprite, x, y);
	}

}
