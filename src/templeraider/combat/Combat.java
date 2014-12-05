package templeraider.combat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import templeraider.Game;

public class Combat implements ActionListener{
	public static final String COMBAT_ATTACK_ACTION_CMD="attack";
	public static final String COMBAT_DEFEND_ACTION_CMD="defend";
	public static final String COMBAT_CHEAT_ACTION_CMD="cheat";
	private static final String COMBAT_TIMER_ACTION_CMD="timer";
	private static final int COMBAT_TIMER_DELAY=1500;
	private EntityPlayer playerInCombat;
	private EntityCombat enemyInCombat;
	private boolean isInCombat=false;
	private boolean playerTurn=false;
	private Timer timer;
	
	/**
	 * Default Constructor for combat class 
	 * This creates a new timer used in combat
	 */
	public Combat(){
		timer=new Timer(COMBAT_TIMER_DELAY,(ActionListener)this);
		timer.setActionCommand(COMBAT_TIMER_ACTION_CMD);
		timer.setInitialDelay(COMBAT_TIMER_DELAY);
		timer.setRepeats(false);
	}
	
	/**
	 * get weather we're in the combat state
	 * @return
	 */
	public boolean isInCombat(){
		return isInCombat;
	}
	
	/**
	 * get the player in combat
	 * @return the player in combat
	 */
	public EntityPlayer getPlayer(){
		return playerInCombat;
	}
	
	/**
	 * get the enemy in combat
	 * @return the enemy in combat
	 */
	public EntityCombat getEnemy(){
		return enemyInCombat;
	}
	
	/**
	 * Start combat between the player and an enemy, pass it true to make the player go first
	 * @param player
	 * @param enemy
	 * @param playerFirst
	 */
	public void startCombat(EntityPlayer player,EntityCombat enemy,boolean playerFirst){
		playerInCombat=player;
		enemyInCombat=enemy;
		isInCombat=true;
		Game.getInstance().pauseTimer(true); //this pauses the main game timer
		Game.getInstance().getGUI().enableButton(0);
		Game.getInstance().getGUI().enableButton(1);
		Game.getInstance().getGUI().disableButton(3); //stops the game from being paused
		Game.getInstance().getGUI().enableButton(4);	

		playerTurn=playerFirst;
		String message;
		if(playerTurn){
			message="You enter combat with "+enemy.getCombatName()+"! You move first.";
		}else{
			message=enemy.getCombatName()+" attacks you! It's their move first.";
		}
		Game.getInstance().getGUI().printLine(message);
		
		timer.restart(); 
		timer.start();
	}
	
	/**
	 * end combat
	 */
	private void endCombat(){
		isInCombat=false;
		Game.getInstance().pauseTimer(false); //restarts the main game timer
		timer.stop(); // stops the combat timer
		
		if(getEnemy().getHealth()==0){
			Game.getInstance().getGUI().printLine(getPlayer().getCombatName()+" beat "+getEnemy().getCombatName()+", and gained "+getEnemy().getRewardedXP()+"XP!");
			getPlayer().giveXP(getEnemy().getRewardedXP());
			getEnemy().remove();
			Game.getInstance().getGUI().disableButton(0);
			Game.getInstance().getGUI().disableButton(1);
			Game.getInstance().getGUI().enableButton(3);
			Game.getInstance().getGUI().disableButton(4);
		}else{
			Game.getInstance().loseGame();
		}
	}
	/**
	 * starts a turn
	 */
	public void startTurn(){
		if(playerTurn){
			
		}else{
			getEnemy().doTurn((EntityCombat)getPlayer());
			endTurn();
		}
	}
	/**
	 * ends the turn
	 */
	public void endTurn(){
		Game.getInstance().getGUI().redrawMap();
		playerTurn=!playerTurn;
		timer.restart();
	}
	/**
	 * Executes the selected action
	 * @param event 
	 */
	public void actionPerformed(ActionEvent event) {
		String command=event.getActionCommand();
		
		if(command==COMBAT_TIMER_ACTION_CMD){
			timer.stop();
			
			if(getEnemy().getHealth()==0||getPlayer().getHealth()==0){
				endCombat();
			}else{
				startTurn();
			}
		}else if(playerTurn&&command==COMBAT_DEFEND_ACTION_CMD){
			getPlayer().defend();
			endTurn();
		}else if(playerTurn&&command==COMBAT_ATTACK_ACTION_CMD){
			getPlayer().attack(getEnemy());
			endTurn();
		}else if(command==COMBAT_CHEAT_ACTION_CMD){
			Game.getInstance().getGUI().printLine(getPlayer().getCombatName()+" cheats to win the battle!");
			getEnemy().setHealth(0);
			endTurn();
		}
	}
}
