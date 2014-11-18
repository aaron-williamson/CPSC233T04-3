public class EntityGoal extends Entity{
	public String getClassID(){return "goal";}
	
	public void onCollide(Entity ent){
		Game.getGame().winGame();
	};
	public EntityGoal(int xcoord, int ycoord) {
		super(xcoord, ycoord);
	}
};