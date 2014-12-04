package templeraider.combat.enemies;
import java.awt.*;

import templeraider.combat.EntityEnemy;

/**
 * The temple bandit enemy for Temple Raider, stronger in combat than the forest bandit
 * but same otherwise
 */
public class EnemyTempleBandit extends EntityEnemy {
	/**
	 * Constructor creates a temple bandit at the provided coordinates and
	 * sets all parameters accordingly
	 * @param xcoord desired x-coordinate
	 * @param ycoord desired y-coordinate
	 */
	public EnemyTempleBandit(int xcoord, int ycoord) {
		super(xcoord, ycoord);
		combatEndurance = 8;
		combatStrength = 15;
		combatLuck = 5;
		setHealth(getMaxHealth());
		combatName = "Temple Bandit";
		combatXP = 2;
		combatAttackMessage="stabs you with his sword!";
		defaultImage = Toolkit.getDefaultToolkit().getImage("templeraider/img/templebandit/south.png");
	}
}