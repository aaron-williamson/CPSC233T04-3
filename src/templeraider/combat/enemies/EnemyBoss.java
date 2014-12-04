package templeraider.combat.enemies;
import java.awt.*;

import templeraider.Game;
import templeraider.combat.EntityEnemy;

/**
 * The boss enemy for Temple Raider. Doesn't move but is extremely strong in combat
 */
public class EnemyBoss extends EntityEnemy {
	/**
	 * Constructor - creates a boss enemy at the provided coordinates and sets
	 * all parameters for the boss
	 * 
	 * @param xcoord desired x-coordinate
	 * @param ycoord desired y-coordinate
	 */
	public EnemyBoss(int xcoord, int ycoord) {
		super(xcoord, ycoord);
		combatEndurance = 20;
		combatStrength = 30;
		combatLuck = 15;
		setHealth(getMaxHealth());
		combatName = "BIG BAD BOSS";
		combatXP = 9001;
		combatAttackMessage="ATTACKS YOU WITH HIS SWORD OF AWESOMENESS!";
		combatCriticalMessage="IT WAS JUST TOO AWESOME!";
		combatDefendMessage="JUST STANDS THERE BEING SO AWESOME THAT YOUR ATTACKS DO LESS DAMAGE.";
		defaultImage = Toolkit.getDefaultToolkit().getImage("templeraider/img/boss/south.png");
	}

	/**
	 * The bosses AI - empty so that the boss doesn't move
	 * @param time the game time
	 */
	public void think(long time){
	}
	
	/**
	 * The remove function for the boss, upon removing the boss win the game
	 */
	public void remove() {
		super.remove();
		Game.getInstance().winGame();
	}
}