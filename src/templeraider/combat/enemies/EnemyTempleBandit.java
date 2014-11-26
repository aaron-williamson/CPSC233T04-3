package templeraider.combat.enemies;
import java.awt.*;

import templeraider.combat.EntityEnemy;

public class EnemyTempleBandit extends EntityEnemy {
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
