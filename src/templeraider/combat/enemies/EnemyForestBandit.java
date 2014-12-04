package templeraider.combat.enemies;
import java.awt.*;
import java.util.Random;

import templeraider.Game;
import templeraider.combat.EntityCombat;
import templeraider.combat.EntityEnemy;

/**
 * The forest bandit enemy for Temple Raider, wields a powerful charge attack
 */
public class EnemyForestBandit extends EntityEnemy {
	
	// By default the bandit isn't charging an attack
	private boolean chargeStatus=false;
	private Random rand=new Random();
	
	/**
	 * Constructor creates a forest bandit at the desired coordinates and
	 * sets all parameters accordingly
	 * @param xcoord the desired x-coordinate
	 * @param ycoord the desired y-coordinate
	 */
	public EnemyForestBandit(int xcoord, int ycoord) {
		super(xcoord, ycoord);
		combatEndurance = 6;
		combatStrength = 12;
		combatLuck = 0;
		setHealth(getMaxHealth());
		combatName = "Forest Bandit";
		combatXP = 1;
		combatAttackMessage="stabs at you with a butter knife!";
		combatCriticalMessage="He buttered your bread!";
		defaultImage = Toolkit.getDefaultToolkit().getImage("templeraider/img/forestbandit/south.png");
	}
	
	/**
	 * The bandit's charge attack
	 * @param defender the entity the bandit is attacking
	 */
	private void chargeAttack(EntityCombat defender){
		chargeStatus=false;
		int damage=getDamage(defender)*3;
		defender.inflictDamage(damage);
		Game.getInstance().getGUI().printLine(getCombatName()+ " powerfully slashes! You're toast. " +
			defender.getCombatName() + " takes " + damage + " Damage!");
	}
	
	/**
	 * The bandit's charge to prepare for a charge attack
	 */
	private void charge(){
		chargeStatus=true;
		Game.getInstance().getGUI().printLine(getCombatName()+ " charges a powerful blow.");
	}
	
	/**
	 * Overrides the default AI for the sake of including the charge attack option
	 * @param defender the entity the bandit is fighting
	 */
	public void doTurn(EntityCombat defender){
		// Reset defense to zero in case the last move was defend
		combatDefense=0;
		
		// If the bandit has charged an attack, use a chargeattack.
		// Otherwise determine whether or not the next move is an attack
		// or just attack normally
		if(chargeStatus){
			chargeAttack(defender);
		}else if(rand.nextDouble()<0.3){
			charge();
		}else{
			attackOrDefend(defender);
		}
	}
}