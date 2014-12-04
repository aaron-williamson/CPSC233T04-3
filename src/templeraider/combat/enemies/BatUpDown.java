package templeraider.combat.enemies;
/**
 * The UpDown bat enemy for Temple Raider, moves up and down in a hallway
 */
public class BatUpDown extends EnemyBat {
	/**
	 * Constructor - Creates an UpDown bat at the provided coordinates
	 * 
	 * @param xcoord the desired x-coordinate
	 * @param ycoord the desired y-coordinate
	 */
	public BatUpDown(int xcoord, int ycoord) {
		// Construct a bat so that it's directions are set to UpDown
		super(xcoord, ycoord);
		this.moveDirection = "updown";
		this.currentDirection = "up";
	}
}