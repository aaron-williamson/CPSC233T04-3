import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Combat implements ActionListener{
	public static String attackActionCommand="attack";
	public static String defendActionCommand="defend";
	private static String timerActionCommand="timer";
	private static int timerDelay=1500;
	private EntityPlayer playerInCombat;
	private EntityCombat enemyInCombat;
	private boolean isInCombat=false;
	private boolean playerTurn=false;
	private Timer timer;
	
	Combat(){
		timer=new Timer(timerDelay,(ActionListener)this);
		timer.setActionCommand(timerActionCommand);
		timer.setInitialDelay(timerDelay);
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
	 * @return
	 */
	public EntityPlayer getPlayer(){
		return playerInCombat;
	}
	
	/**
	 * get the enemy in combat
	 * @return
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
		Game.getGame().pauseTimer(true);
		
		playerTurn=playerFirst;
		String message;
		if(playerTurn){
			message="You enter combat with "+enemy.getCombatName()+"! You move first.";
		}else{
			message=enemy.getCombatName()+" attacks you! It's their move first.";
		}
		Game.getGame().getGUI().printLine(message);
		
		timer.restart();
		timer.start();
	}
	
	/**
	 * end combat
	 */
	private void endCombat(){
		isInCombat=false;
		Game.getGame().pauseTimer(false);
		timer.stop();
		
		if(getEnemy().getHealth()==0){
			Game.getGame().getGUI().printLine(getPlayer().getCombatName()+" beat "+getEnemy().getCombatName()+", and gained "+getEnemy().getRewardedXP()+"XP!");
			getPlayer().giveXP(getEnemy().getRewardedXP());
			getEnemy().remove();
		}else{
			Game.getGame().loseGame();
		}
	}
	
	public void startTurn(){
		if(playerTurn){
			
		}else{
			getEnemy().doTurn((EntityCombat)getPlayer());
			endTurn();
		}
	}
	
	public void endTurn(){
		Game.getGame().getGUI().redrawMap();
		playerTurn=!playerTurn;
		timer.restart();
	}
	
	public void actionPerformed(ActionEvent event) {
		String command=event.getActionCommand();
		
		if(command==timerActionCommand){
			timer.stop();
			
			if(getEnemy().getHealth()==0||getPlayer().getHealth()==0){
				endCombat();
			}else{
				startTurn();
			}
		}else if(playerTurn&&command==defendActionCommand){
			getPlayer().defend();
			endTurn();
		}else if(playerTurn&&command==attackActionCommand){
			getPlayer().attack(getEnemy());
			endTurn();
		}
	}
}