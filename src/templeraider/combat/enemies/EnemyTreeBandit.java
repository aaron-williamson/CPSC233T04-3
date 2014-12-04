package templeraider.combat.enemies;
import java.awt.*;

import templeraider.combat.EntityEnemy;

/**
 * The tree bandit enemy for Temple Raider, disguised like a tree and very fast
 */
public class EnemyTreeBandit extends EntityEnemy {
	/**
	 * Constructor creates a tree bandit at the desired coordinates and
	 * sets all parameters accordingly
	 * @param xcoord the desired x-coordinate
	 * @param ycoord the desired y-coordinate
	 */
	public EnemyTreeBandit(int xcoord, int ycoord) {
		super(xcoord, ycoord);
		combatEndurance = 6;
		combatStrength = 12;
		combatLuck = 3;
		setHealth(getMaxHealth());
		combatName = "Tree";
		combatXP = 1;
		combatAttackMessage="hits you with a branch!";
		setMoveSpeed(6);
		defaultImage = Toolkit.getDefaultToolkit().getImage("templeraider/img/treeslone.png");
	}
}