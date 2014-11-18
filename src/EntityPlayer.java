public class EntityPlayer extends EntityCombat{
	public String getClassID(){return "player";}
	
	public String debuggraphic(){return "P";}
	
	//lose the game if something runs into the player
	public void onCollide(Entity ent){
		Game.getGame().beginCombat();
	};

	/**
	 * Default constructor sets all of the players stats
	 */
	public EntityPlayer() {
		entEND = 10;
		entSTR = 15;
		entLCK = 10;
		entHP = entEND * 10;
		entHIT = (80 + (entLCK / 2)) - 1;
		entCRIT = entLCK - 1;
		name = "Player";
	}
	
	public void think(long time){
		super.think(time);
		
		//I can't use a switch here, huah huah
		if(Game.getGame().gui.isKeyDown(38)){
			moveUp();
		}else if(Game.getGame().gui.isKeyDown(39)){
			moveRight();
		}else if(Game.getGame().gui.isKeyDown(37)){
			moveLeft();
		}else if(Game.getGame().gui.isKeyDown(40)){
			moveDown();
		}
	}

	public void combatTurn(EntityCombat enemy, int turnNum) {
		entBlock = 0;

		String playerCommand = "attack";

		System.out.println("Your turn!\nWhat would you like to do?");
		//playerCommand = Game.in.nextLine();

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
};