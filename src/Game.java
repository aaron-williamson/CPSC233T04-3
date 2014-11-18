import java.util.Scanner;

import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game implements ActionListener{
	public static Game game;
	private static int timerSpeed=16;//delay in ms between game updates
	
	public String endmessage="Undefined game over condition";
	public boolean endgame=false;
	public RPGMap rpgmap;
	public RPGGUI gui;
	public Entities entities;

	private boolean inCombat = false;
	
	public Scanner in;
	public String playername="NONAME";
	
	public Timer timer;
	
	private long time=0;
	
	Game(){
		Game.game=this;
		in=new Scanner(System.in);
		
		System.out.println("lets start the game!");
		
		//this is just for demo, so no input validation.
		System.out.printf("What is your name? ");
		playername=in.nextLine();
		
		//make the entity manager
		entities=new Entities();
		
		//intialize the map
		rpgmap=new RPGMap();
		
		//add a player
		EntityPlayer player=new EntityPlayer();
		EntityPlayer.name = playername;
		player.setPos(1,RPGMap.mapheight-2);
		
		//add a goal to the bottom right corner
		EntityGoal goal=new EntityGoal();
		goal.setPos(RPGMap.mapwidth-2,RPGMap.mapheight-2);
		
		//add an enemy
		EntityEnemy enemy=new EntityEnemy();
		enemy.setPos(1,1);
		
		//make the gui
		gui=new RPGGUI();
		
		//make the timer
		timer=new Timer(Game.timerSpeed,(ActionListener)this);
		timer.start();
		
	}
	
	/**
	 * Get the game instance
	 * @return
	 */
	public static Game getGame(){
		return Game.game;
	}
	
	/**
	 * Get the timer speed of the game, I dont want anyone changing this after the game is started.
	 * @return
	 */
	public int getTimerSpeed(){
		return Game.timerSpeed;
	}
	
	/**
	 * Gets the game's entity manager
	 * @return
	 */
	public Entities getEntities(){
		return entities;
	}
	
	/**
	 * Gets the game's GUI.
	 * @return
	 */
	public RPGGUI getGUI(){
		return gui;
	}
	
	/**
	 * Gets the game's Map.
	 * @return RPGMAP map
	 */
	public RPGMap getMap(){
		return rpgmap;
	}
	
	/**
	 * gets the current time in milliseconds that the game has run
	 * @return long time
	 */
	public long getTime(){
		return time;
	}
	
	/**
	 * The update function for the game, call it once to run a single frame
	 */
	public void update(){
		if(!endgame){
			if (!inCombat) {
				time=(time+Game.timerSpeed)%(Long.MAX_VALUE-Game.timerSpeed-1);
				//draw debug graphics
				//DebugGraphics.printall(rpgmap);
				
				//Have the entities think
				getEntities().allThink();
				getGUI().redrawMap();
			}
			else {
				EntityCombat[] combatEnts = CombatMGR.getAll();

				System.out.println("Your HP: " + combatEnts[0].entHP);
				System.out.println(combatEnts[1].name + "'s HP: " + combatEnts[1].entHP + "\n");

				// Player's turn
				combatEnts[0].turn(combatEnts[1], turnNum);

				// If enemy is dead, do enemy's turn
				if (combatEnts[1].isAlive)
					combatEnts[1].turn(combatEnts[0], turnNum);
				turnNum++;

				if (!combatEnts[1].isAlive)
					removeEntity(combatEnts);
				else if (!combatEnts[0].isAlive)
					loseGame();
			}
		}else{
			
		}
	}
	
	public static void main(String[] args){
		Game.game=new Game();
		
		while(!Game.getGame().endgame){
		//	Game.getGame().update();
		}
		
		Game.getGame().in.close();
		System.out.println(Game.getGame().endmessage);
	}
	
	public void actionPerformed(ActionEvent event) {
		Game.getGame().update();
    }
	
	//ends the game, sets lose message
	public void loseGame(){
		endmessage=playername+" died a terrible death.";
		endgame=true;
	}
	//ends the game, sets win message
	public void winGame(){
		endmessage="Congrats, "+playername+"\nThe winner is You!";
		endgame=true;
	}

	public void beginCombat() {
		inCombat = true;
	}
};