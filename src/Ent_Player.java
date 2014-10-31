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
};