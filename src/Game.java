import java.util.Scanner;

import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game implements ActionListener{
	public static Game game;
	public static int timerSpeed=300;//delay in ms between game updates
	
	public String endmessage="Undefined game over condition";
	public boolean endgame=false;
	public RPGMap rpgmap;
	public RPGGUI gui;
	public Entities entities;
	
	public Scanner in;
	public String playername="NONAME";
	
	public Timer timer;
	
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
		player.setPos(1,RPGMap.mapheight-2);
		
		//add a goal to the bottom right corner
		EntityGoal goal=new EntityGoal();
		goal.setPos(RPGMap.mapwidth-2,RPGMap.mapheight-2);
		
		//add an enemy
		//EntityEnemy enemy=new EntityEnemy();
		//enemy.setPos(1,1);
		
		//make the gui
		gui=new RPGGUI();
		
		//make the timer
		timer=new Timer(Game.timerSpeed,(ActionListener)this);
		timer.start();
		
	}
	
	public static Game getGame(){
		return Game.game;
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
	 * The update function for the game, call it once to run a single frame
	 */
	public void update(){
		if(!endgame){
			//draw debug graphics
			DebugGraphics.printall(rpgmap);
			
			//Have the entities think
			getEntities().allThink();
			getGUI().redrawMap();
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
};