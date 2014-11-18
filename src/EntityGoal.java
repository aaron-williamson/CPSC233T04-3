public class EntityGoal extends Entity{
	public String getClassID(){return "goal";}
	
	public void onCollide(Entity ent){
		Game.getGame().winGame();
	};
};