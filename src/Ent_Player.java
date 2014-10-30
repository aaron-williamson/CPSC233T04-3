import java.util.Scanner;

public class Ent_Player extends Ent_Movable{
	public String getClassID(){return "player";}
	
	public String debuggraphic(){return "P";}
	
	//lose the game if something runs into the player
	public void onCollide(Entity ent){
		CombatV2.beginCombat();
	};

	//lose the game if the player dies
	public void isDead(){
		Game.loseGame();
	}
	
	public void think(){
		boolean needmove=true;

		
		while(needmove){
			System.out.printf("What direction would you like to move (N/S/E/W)? ");
			String movestr="";
			
			movestr=Game.in.nextLine();//get input string
			
			
			if(movestr.equalsIgnoreCase("n")){
				needmove=!moveUp(true);
			}else if(movestr.equalsIgnoreCase("e")){
				needmove=!moveRight(true);
			}else if(movestr.equalsIgnoreCase("s")){
				needmove=!moveDown(true);
			}else if(movestr.equalsIgnoreCase("w")){
				needmove=!moveLeft(true);
			}
		}
		
	}

	// Player stats for combat
	private int entEND = 10;
	private int entSTR = 15;
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
};