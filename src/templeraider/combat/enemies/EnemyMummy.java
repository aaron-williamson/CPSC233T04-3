package templeraider.combat.enemies;
import java.awt.*;
import java.util.Random;

import templeraider.combat.EntityEnemy;

public class EnemyMummy extends EntityEnemy {

	Random rand = new Random();

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
