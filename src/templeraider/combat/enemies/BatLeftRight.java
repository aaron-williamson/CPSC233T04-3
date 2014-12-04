package templeraider.combat.enemies;
/**
 * The LeftRight bat enemy for Temple Raider, moves left and right in a hallway
 */
public class BatLeftRight extends EnemyBat {
	/**
	 * Constructor - Creates an LeftRight bat at the provided coordinates
	 * 
	 * @param xcoord the desired x-coordinate
	 * @param ycoord the desired y-coordinate
	 */
	public BatLeftRight(int xcoord, int ycoord) {
		// Construct a bat so that it's directions are set to LeftRight
		super(xcoord, ycoord);
		this.moveDirection = "leftright";
		this.currentDirection = "left";
	}
}