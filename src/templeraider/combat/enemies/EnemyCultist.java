package templeraider.combat.enemies;
import java.awt.*;

import templeraider.combat.EntityEnemy;

/**
 * The cultist enemy for Temple Raider, chases the player at a higher distance
 * than the standard bandit, and is stronger in combat.
 */
public class EnemyCultist extends EntityEnemy {
	/**
	 * Constructor - creates a cultist at the desired coordinates and sets
	 * all parameters appropriately
	 * @param xcoord desired x-coordinate
	 * @param ycoord desired y-coordinate
	 */
	public EnemyCultist(int xcoord, int ycoord) {
		super(xcoord, ycoord);
		combatEndurance = 8;
		combatStrength = 20;
		combatLuck = 10;
		setHealth(getMaxHealth());
		combatName = "Cultist";
		combatXP = 4;
		combatAttackMessage="chants some gibberish so loud that it hurts!";
		combatMissMessage="He didn't chant loud enough!";
		combatCriticalMessage="It turned out to be a Celine Dion song!";
		combatDefendMessage="sits down and starts meditating, negating some of your damage.";
		aggroDistance = 8;
		defaultImage = Toolkit.getDefaultToolkit().getImage("templeraider/img/cultist/south.png");
	}
}