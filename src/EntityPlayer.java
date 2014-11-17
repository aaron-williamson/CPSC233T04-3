public class EntityPlayer extends EntityMovable{
	public String getClassID(){return "player";}
	
	public String debuggraphic(){return "P";}
	
	//lose the game if something runs into the player
	public void onCollide(Entity ent){
		Game.getGame().loseGame();
	};
	
	public void think(long time){
		super.think(time);
		
		//I can't use a switch here, huah huah
		if(Game.getGame().gui.isKeyDown(38)){
			moveUp();
		}else if(Game.getGame().gui.isKeyDown(39)){
			moveRight();
		}else if(Game.getGame().gui.isKeyDown(37)){
			moveLeft();
		}else if(Game.getGame().gui.isKeyDown(40)){
			moveDown();
		}
	}
};