import java.util.Random;

public class Ent_Enemy extends Ent_Movable{
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

	// Combat stats for Enemy
	private int entEND = 7;
	private int entSTR = 10;
	private int entLCK = 5;
	private int entHP = entEND * 10;
	private int entHIT = (80 + (entLCK / 2)) - 1;
	private int entCRIT = entLCK - 1;

	/**
	 * Getter Function for entEND (Endurance stat)
	 * 
	 * @return entEND the player's endurance stat
	 */
	public int getEntEND() {
		return this.entEnd;
	}

	/**
	 * Getter Function for entSTR (Strength stat)
	 * 
	 * @return entSTR the player's strength stat
	 */
	public int getEntSTR() {
		return this.entSTR;
	}

	/**
	 * Getter Function for entLCK (Luck stat)
	 * 
	 * @return entLCK the player's luck stat
	 */
	public int getEntLCK() {
		return this.entLCK;
	}

	/**
	 * Getter Function for entHP (Health stat)
	 * 
	 * @return entHP the player's health stat
	 */
	public int getEntHP() {
		return this.entHP;
	}

	/**
	 * Getter Function for entHIT (Hit chance stat)
	 * 
	 * @return entHIT the player's hit chance stat
	 */
	public int getEntHIT() {
		return this.entHIT;
	}

	/**
	 * Getter Function for entCRIT (Critical hit chance stat)
	 * 
	 * @return entCRIT the player's critical hit chance stat
	 */
	public int getEntCRIT() {
		return this.entCRIT;
	}

	/**
	 * Setter funciton for entHP (Health stat)
	 *
	 * @param health the value health should be set to
	 */
	public void setEntHP(int health) {
		this.entHP = health;
	}
};