import java.util.Random;

public class EntityEnemy extends EntityCombat{
	protected int givesExp = 0;

	public String getClassID(){return "enemy";}
	
	public String debuggraphic(){return "E";}
	
	//lose the game if the player runs into an enemy
	public void onCollide(Entity ent){
		Game.getGame().beginCombat();
	}

	/**
	 * Default Contructor sets the combat stats for Ent_Enemy
	 */
	public EntityEnemy() {
		entEND = 7;
		entSTR = 15;
		entLCK = 5;
		entHP = entEND * 10;
		entHIT = (80 + (entLCK / 2)) - 1;
		entCRIT = entLCK - 1;
		name = "Bandit";
	}
	
	public void think(long time){
		//super cheap 'ai' here
		
		//get the nearest player
		Entity[] playersarray=Game.getGame().getEntities().getByClass("player");
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
	public void turn(EntityCombat enemy, int turnNum) {
		System.out.println(name + "'s turn!");
		Random rand = new Random();

		entBlock = 0;

		// If it's the first turn attack
		if (turnNum == 1) {
			System.out.println(name + " attacks!\n");
			attack(enemy);
		}

		// If it's not the first turn
		else {
			int banditAction = rand.nextInt(5);

			// If at or above 50% HP, there is an 80% chance entityenemy will attack
			if (entHP >= (entHP / 2)) {
				if (banditAction > 0) {
					System.out.println(name + " attacks!");
					attack(enemy);
				}
				else {
					System.out.println(name + " defends!");
					defend();
				}
			}

			// If entityenemy has less than 50% HP it will defend more often
			else {
				if (banditAction > 1) {
					System.out.println(name + " attacks!");
					attack(enemy);
				}

				else {
					System.out.println(name + " defends!");
					defend();
				}
			}
		}
	}
};