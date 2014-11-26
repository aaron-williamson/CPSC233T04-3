package templeraider;
import javax.swing.Timer;

import templeraider.combat.Combat;
import templeraider.combat.EntityPlayer;
import templeraider.entity.Entities;
import templeraider.entity.Entity;
import templeraider.gui.RPGGUI;
import templeraider.gui.RPGMap;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game implements ActionListener{
	private static Game instance;
	private static final int GAME_TIMER_SPEED=16;//delay in ms between game updates
	public static final String GAME_TITLE="Temple Raider"; 
	
	private String endmessage="Undefined game over condition";
	private boolean endgame=false;
	private RPGMap rpgmap;
	private RPGGUI gui;
	private Entities entities;
	private Combat combat;
	private int level=-1;
	
	public String playername="NONAME";
	
	private Timer timer;
	private long time=0;
	
	private Game(){
		
	}
	
	/**
	 * Get the game instance
	 * @return
	 */
	public static Game getInstance(){
		if(instance==null){
			instance=new Game();
		}
		return instance;
	}
	
	public void startNewGame(){
		//make the entity manager
		entities=new Entities();
				
		//make the combat manager
		combat=new Combat();
		
		//add a player
		new EntityPlayer(0,0);
				
		//make the default map
		level=-1;
		nextMap();
		
		//make the gui
		gui=new RPGGUI();
		
		//make the timer
		timer=new Timer(Game.GAME_TIMER_SPEED,(ActionListener)this);
	}

	
	/**
	 * Loads the next map
	 */
	public void nextMap(){
		Entity[] entityArray=getEntities().getAll();
		EntityPlayer player=(EntityPlayer)getEntities().getByClass("player")[0];
		
		for(int i=0;i<entityArray.length;i++){
			if(entityArray[i]!=player){
				entityArray[i].remove();
			}
		}
		
		level++;
		rpgmap=new RPGMap(level);
		
		entityArray=getEntities().getAll();
		for(int i=0;i<entityArray.length;i++){
			if(entityArray[i].getClassID().equals("playerspawn")){
				player.setPos(entityArray[i].getX(),entityArray[i].getY());
				entityArray[i].remove();
				break;
			}
		}
	}
	
	/**
	 * Get the timer speed of the game, I dont want anyone changing this after the game is started.
	 * @return
	 */
	public int getTimerSpeed(){
		return Game.GAME_TIMER_SPEED;
	}
	
	/**
	 * call this to pause the game, give it true to pause, false to unpause
	 * @param pause
	 */
	public void pauseTimer(boolean pause){
		if(pause){
			timer.stop();
		}else{
			timer.start();
		}
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
	 * Get the combat manager
	 * @return
	 */
	public Combat getCombat(){
		return combat;
	}
	
	/**
	 * Get the end game message
	 * @return
	 */
	public String getEndmessage(){
		return endmessage;
	}
	
	/**
	 * Get if the game is over
	 * @return
	 */
	public boolean isGameEnd(){
		return endgame;
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
			time=(time+Game.GAME_TIMER_SPEED)%(Long.MAX_VALUE-Game.GAME_TIMER_SPEED-1);
			//Have the entities think
			getEntities().allThink();
			getGUI().redrawMap();
		}else{
			Game.getInstance().pauseTimer(true);
			Game.getInstance().getGUI().disableButton(0);
			Game.getInstance().getGUI().disableButton(1);
			Game.getInstance().getGUI().disableButton(3);
			getGUI().redrawMap();
		}
	}
	
	public static void main(String[] args){
		Game.getInstance();//instantiate the game
		Game.getInstance().startNewGame();
	}
	
	public void actionPerformed(ActionEvent event) {
		Game.getInstance().update();
    }
	
	//ends the game, sets lose message
	public void loseGame(){
		String playername=((EntityPlayer)getEntities().getByClass("player")[0]).getPlayerName();
		endmessage=playername+" died a terrible death.";
		endgame=true;
	}
	//ends the game, sets win message
	public void winGame(){
		String playername=((EntityPlayer)getEntities().getByClass("player")[0]).getPlayerName();
		endmessage="Congrats, "+playername+", the winner is You!";
		endgame=true;
	}
}