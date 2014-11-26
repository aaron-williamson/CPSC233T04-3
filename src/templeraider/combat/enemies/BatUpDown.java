package templeraider.combat.enemies;
public class BatUpDown extends EnemyBat {
	public BatUpDown(int xcoord, int ycoord) {
		super(xcoord, ycoord);
		this.moveDirection = "updown";
		this.currentDirection = "up";
	}
}