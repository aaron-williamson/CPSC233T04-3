import java.awt.*;

public class EnemyWolf extends EntityEnemy {
	private int count = 0;
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
		defaultImage = Toolkit.getDefaultToolkit().getImage("../img/wolf/south.png");
	}

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
