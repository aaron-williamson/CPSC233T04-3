import java.util.Scanner;

public class Ent_Player extends Ent_Combat{
	public String getClassID(){return "player";}
	
	public String debuggraphic(){return "P";}
	
	//lose the game if something runs into the player
	public void onCollide(Ent_Combat enemy){
		Game.beginCombat();
	}

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

	/**
	 * Default constructor sets all of the players stats
	 */
	public Ent_Player() {
		entEND = 10;
		entSTR = 15;
		entLCK = 5;
		entHP = entEND * 10;
		entHIT = (80 + (entLCK / 2)) - 1;
		entCRIT = entLCK - 1;
		name = "Player";
	}

	public void turn(Ent_Combat enemy, int turnNum) {
		entBlock = 0;

		String playerCommand;

		System.out.println("What would you like to do?");
		playerCommand = Game.in.nextLine();

		if (playerCommand.equals("attack") || playerCommand.equals("Attack") || playerCommand.equals("a")) {
			attack(enemy);
		}

		else if (playerCommand.equals("defend") || playerCommand.equals("Defend") || playerCommand.equals("d")) {
			defend();
		}

		else {
			System.out.println("That input is invalid. Turn is void.");
		}
	}
}