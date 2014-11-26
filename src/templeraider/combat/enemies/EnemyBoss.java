package templeraider.combat.enemies;
import java.awt.*;

import templeraider.Game;
import templeraider.combat.EntityEnemy;

public class EnemyBoss extends EntityEnemy {
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

	public void think(long time){
	}

	public void remove() {
		super.remove();
		Game.getInstance().winGame();
	}
}