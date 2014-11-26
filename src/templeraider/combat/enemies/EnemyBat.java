package templeraider.combat.enemies;
import java.awt.*;

import templeraider.Game;
import templeraider.combat.EntityEnemy;

public class EnemyBat extends EntityEnemy {
	protected String moveDirection = "leftright";
	protected String currentDirection = "left";

	public EnemyBat(int xcoord, int ycoord) {
		super(xcoord, ycoord);
		combatEndurance = 3;
		combatStrength = 15;
		combatLuck = 10;
		setHealth(getMaxHealth());
		combatName = "Bat";
		combatXP = 2;
		combatAttackMessage="flies at you violently!";
		combatCriticalMessage="IT GOT YOU RIGHT IN THE EYE!!";
		combatDefendMessage="defends itself with its wings.";
		setMoveSpeed(5);
		defaultImage = Toolkit.getDefaultToolkit().getImage("templeraider/img/bat/south.png");
	}

	public void think(long time){
		if (moveDirection.equals("leftright")) {
			if ((currentDirection.equals("left")) && (Game.getInstance().getMap().isPassable(this.getX() - 1, this.getY())))
				this.moveLeft();
			else if ((currentDirection.equals("left")) && !(Game.getInstance().getMap().isPassable(this.getX() - 1, this.getY()))) {
				this.moveRight();
				currentDirection = "right";
			}
			else if ((currentDirection.equals("right")) && (Game.getInstance().getMap().isPassable(this.getX() + 1, this.getY())))
				this.moveRight();
			else if ((currentDirection.equals("right")) && !(Game.getInstance().getMap().isPassable(this.getX() + 1, this.getY()))) {
				this.moveLeft();
				currentDirection = "left";
			}
		}
		else if (moveDirection.equals("updown")) {
			if ((currentDirection.equals("up")) && (Game.getInstance().getMap().isPassable(this.getX(), this.getY() - 1)))
				this.moveUp();
			else if ((currentDirection.equals("up")) && !(Game.getInstance().getMap().isPassable(this.getX(), this.getY() - 1))) {
				this.moveDown();
				currentDirection = "down";
			}
			else if ((currentDirection.equals("down")) && (Game.getInstance().getMap().isPassable(this.getX(), this.getY() + 1)))
				this.moveDown();
			else if ((currentDirection.equals("down")) && !(Game.getInstance().getMap().isPassable(this.getX(), this.getY() + 1))) {
				this.moveUp();
				currentDirection = "up";
			}
		}
	}
}