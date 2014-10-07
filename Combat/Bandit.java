// Class for the bandit
public class Bandit extends Player
{
	public String name = "Bandit";
	// Initialize bandit stats
	public int END = 7;
	public int STR = 10;
	public int LCK = 5;
	public int INT = 5;
	public int HP = END * 10;

	// Determine additional stats
	public int hitCance = (80 + (LCK / 2)) - 1;
	public int critChance = LCK - 1;

	// Set bandit as alive
	public boolean dead = false;

	// Bandit's are computer controlled
	public boolean isHuman = false;
}