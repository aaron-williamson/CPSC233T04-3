package templeraider.combat.enemies;
import java.awt.*;
import java.util.Random;

import templeraider.combat.EntityEnemy;

/**
 * The mummy enemy for Temple Raider, stumbles around in random directions,
 * fairly strong in combat.
 */
public class EnemyMummy extends EntityEnemy {
	
	// Random generator for the AI
	Random rand = new Random();

	/**
	 * Constructor creates a mummy at the desired coordinates and
	 * sets all parameters accordingly
	 * @param xcoord the desired x-coordinate
	 * @param ycoord the desired y-coordinate
	 */
	public EnemyMummy(int xcoord, int ycoord) {
		super(xcoord, ycoord);
		combatEndurance = 10;
		combatStrength = 12;
		combatLuck = 0;
		setHealth(getMaxHealth());
		combatName = "Mummy";
		combatXP = 2;
		combatAttackMessage="swipes at you with what remains of it's arm!";
		combatCriticalMessage="Some of his arm broke off inside you! EW!";
		combatDefendMessage="stiffens up.";
		setMoveSpeed(20);
		defaultImage = Toolkit.getDefaultToolkit().getImage("templeraider/img/mummy/south.png");
	}

	/**
	 * Overrides the AI for the mummy so it merely moves in random directions instead of
	 * chasing the player
	 * @param time the game time
	 */
	public void think(long time){
		int direction = rand.nextInt(4);

		switch (direction) {
			case 0:
				this.moveRight();
				break;
			case 1:
				this.moveLeft();
				break;
			case 2:
				this.moveUp();
				break;
			case 3:
				this.moveDown();
				break;
		}
	}
}