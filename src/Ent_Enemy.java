import java.util.Random;

public class Ent_Enemy extends Ent_Combat {
	public String getClassID(){return "enemy";}
	
	public String debuggraphic(){return "E";}
	
	//lose the game if the player runs into an enemy
	public void onCollide(Entity enemy) {
		Game.beginCombat();
		System.out.println("TEST");
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
		entEND = 5;
		entSTR = 5;
		entLCK = 1;
		entHP = entEND * 10;
		entHIT = (80 + (entLCK / 2)) - 1;
		entCRIT = entLCK - 1;
		name = "Bandit";
	}

	public void turn(Ent_Combat enemy, int turnNum) {
		Random rand = new Random();

		entBlock = 0;

		// If it's the first turn attack
		if (turnNum == 1) {
			attack(enemy);
		}

		// If it's not the first turn
		else {
			int banditAction = rand.nextInt(5);

			// If at or above 50% HP, there is an 80% chance ent_enemy will attack
			if (entHP >= (entHP / 2)) {
				if (banditAction > 0) {
					attack(enemy);
				}
				else {
					defend();
				}
			}

			// If ent_enemy has less than 50% HP it will defend more often
			else {
				if (banditAction > 1) {
					attack(enemy);
				}

				else {
					defend();
				}
			}
		}
	}
};