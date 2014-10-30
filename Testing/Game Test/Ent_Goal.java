public class Ent_Goal extends Entity{
	String getClassID(){return "goal";}
	String debuggraphic(){return "G";}
	
	void onCollide(Entity ent){
		Game.winGame();
	};
};