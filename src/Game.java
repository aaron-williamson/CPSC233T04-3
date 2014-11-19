import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game implements ActionListener{
	private static Game game;
	public static String title="Temple Raider"; 
	private static int timerSpeed=16;//delay in ms between game updates
	
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
	
	Game(){
		Game.game=this;
		
		System.out.println("lets start the game!");
		
		//make the entity manager
		entities=new Entities();
		
		//make the combat manager
		combat=new Combat();
		
		//add a player
		new EntityPlayer(0,0);
		
		//make the default map
		level=-1;
		loadNextMap();
		
		//make the gui
		gui=new RPGGUI();
		
		//make the timer
		timer=new Timer(Game.timerSpeed,(ActionListener)this);
		
		
	}

	public void nextMap() {
		Game.getGame().getGUI().printLine("Next Map!");
		loadNextMap();
	}
	
	/**
	 * Get the game instance
	 * @return
	 */
	public static Game getGame(){
		return Game.game;
	}
	
	private void loadNextMap(){
		Entity[] entityArray=getEntities().getAll();
		Entity player=getEntities().getByClass("player")[0];
		
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
		return Game.timerSpeed;
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
	
	public Combat getCombat(){
		return combat;
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
			time=(time+Game.timerSpeed)%(Long.MAX_VALUE-Game.timerSpeed-1);
			//Have the entities think
			getEntities().allThink();
			getGUI().redrawMap();
		}else{
			
		}
	}
	
	public static void main(String[] args){
		Game.game=new Game();
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
