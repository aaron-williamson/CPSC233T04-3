package templeraider.combat.enemies;
import java.awt.*;

import templeraider.combat.EntityEnemy;

/**
 * The wolf enemy for Temple Raider, runs in circles until colliding with the player
 */
public class EnemyWolf extends EntityEnemy {
	// Variable for incrementing so the wolf moves in circles
	private int count = 0;
	
	/**
	 * Constructor creates a wolf at the provided x and y coordinates and sets
	 * all parameters accordingly
	 * @param xcoord desired x-coordinate
	 * @param ycoord desired y-coordinate
	 */
	public EnemyWolf(int xcoord, int ycoord) {
		super(xcoord, ycoord);
		combatEndurance = 6;
		combatStrength = 12;
		combatLuck = 0;
		setHealth(getMaxHealth());
		combatName = "Wolf";
		combatXP = 1;
		combatAttackMessage="bites you!";
		setMoveSpeed(10);
		defaultImage = Toolkit.getDefaultToolkit().getImage("templeraider/img/wolf/south.png");
	}

	/**
	 * The wolf's AI, runs in circles
	 * @param time the game time
	 */
	public void think(long time){
		switch (count) {
			case 0:
				this.moveRight();
				break;
			case 1:
				this.moveDown();
				break;
			case 2:
				this.moveLeft();
				break;
			case 3:
				this.moveUp();
				break;
			default:
				break;
		}
		count = (count + 1) % 4;
	}
}