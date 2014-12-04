package templeraider.combat.enemies;
import java.awt.*;

import templeraider.combat.EntityEnemy;

/**
 * The barbarian enemy for Temple Raider, very strong in combat
 */
public class EnemyBarbarian extends EntityEnemy {
	/**
	 * Constructor creates a barbarian at the desired coordinates and
	 * sets all parameters accordingly
	 * @param xcoord the desired x-coordinate
	 * @param ycoord the desired y-coordinate
	 */
	public EnemyBarbarian(int xcoord, int ycoord) {
		super(xcoord, ycoord);
		combatEndurance = 12;
		combatStrength = 20;
		combatLuck = 0;
		setHealth(getMaxHealth());
		combatName = "Barbarian";
		combatXP = 4;
		combatAttackMessage="swing's his axe!";
		defaultImage = Toolkit.getDefaultToolkit().getImage("templeraider/img/barbarian/south.png");
	}
}