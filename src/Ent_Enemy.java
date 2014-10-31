import java.util.Random;

public class Ent_Enemy extends Ent_Combat{
	public String getClassID(){return "enemy";}
	
	public String debuggraphic(){return "E";}
	
	//lose the game if the player runs into an enemy
	public void onCollide(Entity ent){
		Game.loseGame();
	};
	
	public void think(){
		//super cheap 'ai' here
		
		//get the nearest player
		Entity[] playersarray=Ents.getByClass("player");
		Entity target=playersarray[0];
		double windist=999999;
		for(int i=0;i<playersarray.length;i++){
			double dist=this.distance(playersarray[i]);
			if(dist<windist){
				target=playersarray[i];
				windist=dist;
			}
		}
		
		int difx=target.getX()-this.getX();
		int dify=target.getY()-this.getY();
		
		//if we need to move both horizontally and vertically, choose one randomly
		if(difx!=0 && dify!=0){
			boolean rand=new Random().nextBoolean();
			if(rand){
				difx=0;
			}else{
				dify=0;
			}
		}
		
		if(difx>0){
			this.moveRight();
		}else if(difx<0){
			this.moveLeft();
		}else if(dify>0){
			this.moveDown();
		}else if(dify<0){
			this.moveUp();
		}
		
	}

	/**
	 * Default Contructor sets the combat stats for Ent_Enemy
	 */
	public Ent_Enemy() {
		setEntEND(5);
		setEntSTR(5);
		setEntLCK(1);
		setEntHP(getEntEND() * 10);
		setEntHIT((80 + (getEntLCK() / 2)) - 1);
		setEntCRIT(getEntLCK() - 1);
	}
};