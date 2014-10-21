public class Ent_Goal extends Entity{
	public String getClassID(){return "goal";}
	public String debuggraphic(){return "G";}
	
	public void onCollide(Entity ent){
		Game.winGame();
	};
};