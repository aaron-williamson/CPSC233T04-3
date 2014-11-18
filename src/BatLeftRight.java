public class BatLeftRight extends EnemyBat {
	public BatLeftRight(int xcoord, int ycoord) {
		super(xcoord, ycoord);
		this.moveDirection = "leftright";
		this.currentDirection = "left";
	}
}