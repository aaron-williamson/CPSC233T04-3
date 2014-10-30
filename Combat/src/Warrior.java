// Class for the warrior
public class Warrior extends Player
{
	public String name = "Warrior";
	// Initialize warrior stats
	public int END = 10;
	public int STR = 15;
	public int LCK = 5;
	public int INT = 5;
	public int HP = END * 10;

	// Determine additional stats
	public int hitCance = (80 + (LCK / 2)) - 1;
	public int critChance = LCK - 1;

	// Set warrior as alive
	public boolean dead = false;

	// Warrior will be player character
	public boolean isHuman = true;
}