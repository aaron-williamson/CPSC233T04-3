// Basic class for a player
public class Player
{
	public String name = "Player";
	// Initialize player stats
	public int END = 5;
	public int STR = 5;
	public int LCK = 5;
	public int INT = 5;
	public int HP = END * 10;

	// Determine additional stats
	public int hitCance = (80 + (LCK / 2)) - 1;
	public int critChance = LCK - 1;

	// Set player as alive
	public boolean dead = false;

	public boolean isHuman = false;
}