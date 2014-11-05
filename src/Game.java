import java.util.Scanner;

public class Game{
	public static String endmessage="Undefined game over condition";
	public static boolean endgame=false;
	public static MapMakerV1 rpgmap=new MapMakerV1();
	public static boolean inCombat = false;
	
	public static Scanner in=new Scanner(System.in);
	public static String playername="NONAME";
	public static int turnNum = 1;
	
	public static void update(){
		if (!inCombat) {
			//draw debug graphics
			DebugGraphics.printall(rpgmap);
			
			//Have the entities think
			Ents.allThink();
		}
		else {
			Ent_Combat[] combatEnts = CombatMGR.getAll();

			System.out.println("Your HP: " + combatEnts[0].entHP);
			System.out.println(combatEnts[1].name + "'s HP: " + combatEnts[1].entHP + "\n");
			combatEnts[0].turn(combatEnts[1], turnNum);
			if (combatEnts[1].isAlive)
				combatEnts[1].turn(combatEnts[0], turnNum);
			turnNum++;

			if (!combatEnts[1].isAlive)
				winGame();
			else if (!combatEnts[0].isAlive)
				loseGame();
		}
	}
	
	public static void main(String[] args){
		System.out.println("Lets start the game!");
		
		//this is just for demo, so no input validation.
		System.out.printf("What is your name? ");
		playername=in.nextLine();
		System.out.printf("Would you like a blank(0) or rows(1) map? ");
		String maptype=in.nextLine();
		
		//intialize the map
		if(maptype.equals("1")){
			rpgmap.makerowmap();
		}else{
			rpgmap.makeblankmap();
		}
		
		//add a player
		Ent_Player player=new Ent_Player();
		player.setPos(1,rpgmap.mapheight-2);
		player.name = playername;
		Ents.addEnt(player);
		CombatMGR.addCombat(player);
		
		//add a goal to the bottom right corner
		Ent_Goal goal=new Ent_Goal();
		goal.setPos(rpgmap.mapwidth-2,rpgmap.mapheight-2);
		Ents.addEnt(goal);
		
		//add an enemy
		Ent_Enemy enemy=new Ent_Enemy();
		enemy.setPos(1,1);
		Ents.addEnt(enemy);
		CombatMGR.addCombat(enemy);
		
		//main game loop
		while(!endgame){
			
			update();
		}
		
		in.close();
		
		System.out.println(endmessage);
	}
	//ends the game, sets lose message
	public static void loseGame(){
		endmessage=playername+" died a terrible death.";
		endgame=true;
	}
	//ends the game, sets win message
	public static void winGame(){
		endmessage="Congrats, "+playername+"\nThe winner is You!";
		endgame=true;
	}
	//Begins combat

	public static void beginCombat() {
		inCombat = true;
		Ent_Combat[] combatEnts = CombatMGR.getAll();
		// Clear the screen
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

		int turnNum = 1;
		System.out.println("Prepare yourself, " + combatEnts[0].name + ", you have begun combat with: " + combatEnts[1].name + "!\n");
	}
};