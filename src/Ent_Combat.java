import java.util.Random;

public class Ent_Combat extends Ent_Movable {
	private int entEND = 5;
	private int entSTR = 5;
	private int entLCK = 5;
	private int entHP = entEND * 10;
	private int entHIT = (80 + (entLCK / 2)) - 1;
	private int entCRIT = entLCK - 1;
	private int entBlock = 0;
	private boolean isAlive = true;
	private String name;

	public void turn(Ent_Combat enemy, int turnNum) {

	}
	
	/**
	 * Getter Function for entEND (Endurance stat)
	 * 
	 * @return entEND the player's endurance stat
	 */
	public int getEntEND() {
		return entEND;
	}

	/**
	 * Getter Function for entSTR (Strength stat)
	 * 
	 * @return entSTR the player's strength stat
	 */
	public int getEntSTR() {
		return entSTR;
	}

	/**
	 * Getter Function for entLCK (Luck stat)
	 * 
	 * @return entLCK the player's luck stat
	 */
	public int getEntLCK() {
		return entLCK;
	}

	/**
	 * Getter Function for entHP (Health stat)
	 * 
	 * @return entHP the player's health stat
	 */
	public int getEntHP() {
		return entHP;
	}

	/**
	 * Getter Function for entHIT (Hit chance stat)
	 * 
	 * @return entHIT the player's hit chance stat
	 */
	public int getEntHIT() {
		return entHIT;
	}

	/**
	 * Getter Function for entCRIT (Critical hit chance stat)
	 * 
	 * @return entCRIT the player's critical hit chance stat
	 */
	public int getEntCRIT() {
		return entCRIT;
	}

	/**
	 * Setter Function for entEND (Endurance stat)
	 * 
	 * @param endurance the player's endurance stat
	 */
	public void setEntEND(int endurance) {
		entEND = endurance;
	}

	/**
	 * Getter Function for entSTR (Strength stat)
	 * 
	 * @param strength the player's strength stat
	 */
	public void setEntSTR(int strength) {
		entSTR = strength;
	}

	/**
	 *  Function for entLCK (Luck stat)
	 * 
	 * @param luck the player's luck stat
	 */
	public void setEntLCK(int luck) {
		entLCK = luck;
	}

	/**
	 * Setter funciton for entHP (Health stat)
	 *
	 * @param health the value health should be set to
	 */
	public void setEntHP(int health) {
		entHP = health;
	}

	/**
	 * Setter function for entHIT (Hit chance stat)
	 * 
	 * @param hitChance the player's hit chance stat
	 */
	public void setEntHIT(int hitChance) {
		entHIT = hitChance;
	}

	/**
	 * Setter function for entCRIT (Critical hit chance stat)
	 * 
	 * @param critical the player's critical hit chance stat
	 */
	public void setEntCRIT(int critical) {
		entCRIT = critical;
	}

	/**
	 * Getter function for isAlive (Whether or not the entity is alive)
	 *
	 * @return isAlive the life status of this entity
	 */
	public boolean getIsAlive() {
		return isAlive;
	}

	/**
	 * Setter function for isAlive (Whether or not the entity is alive)
	 *
	 * @param alive specifies whether or not the entity is alive
	 */
	public void setIsAlive(boolean alive) {
		isAlive = alive;
	}

	/**
	 * Setter function for name (Entities name)
	 *
	 * @param aName the desired name
	 */
	public void setName(String aName) {
		name = aName;
	}

	/**
	 * Getter function for Name (Entities name)
	 *
	 * @return name the entities name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter function for entBlock (Entities blocking)
	 *
	 * @param block the desired block value
	 */
	public void setEntBlock(int block) {
		entBlock = block;
	}

	/**
	 * Getter function for entBlock (Entities blocking)
	 *
	 * @return entBlock the entities name
	 */
	public String getEntBlock() {
		return entBlock;
	}

		private Random rand = new Random();

	private boolean isCrit(int entCRIT) {
		boolean criticalHit = false;

		int crit = rand.nextInt(100);

		if (crit <= entCRIT) {
			criticalHit = true;
			System.out.print("CRITICAL HIT! ");
		}

		return criticalHit;
	}

	private boolean isHit(int entHIT) {
		boolean isHit = false;

		int hit = rand.nextInt(100);

		if (hit <= entHIT)
			entHit = true;

		return isHit;
	}

	private int damage(int entCRIT, int entSTR, int entLCK) {
		int damageDealt;
		if (isCrit(entCRIT))
			damageDealt = 2 * (entSTR + rand.nextInt(10 + (2 * (entLCK / 2))));
		else
			damageDealt = entSTR + rand.nextInt(10 + (2 * (entLCK / 2)));

		return damageDealt;
	}

	public void attack(Ent_Combat defender) {

		// If the attack hits
		if (isHit(entHIT))
		{
			// Get the damage dealt (including whether or not it is a critical hit)
			int damageDealt = damage(entCRIT, entSTR, entLCK);

			// If the defender is not blocking do damage to health
			if (defender.getEntBlock() == 0)
			{
				defender.setEntHP(defender.getEntHP() - damageDealt);
				System.out.println(name + " dealt " + damageDealt + " damage to " + defender.getName() + "!");
				// Check to see if attacker killed the defender
				if (defender.getEntHP() > 0)
				{
					System.out.println(defender.getName() + "'s health is now: " + defender.getEntHP() + "\n");
				}
				else
				{
					System.out.println(name + " killed " + defender.getName() + "!\n");
					defender.setIsAlive(false);
				}
			}

			// If the defender is blocking calculate blocked damage and damage/dealt
			else
			{
				boolean blockBroken;

				if (damageDealt >= defender.getEntBlock())
					blockBroken = false;
				else
					blockBroken = true;

				// If the defender blocked the entire attack
				if (!blockBroken)
				{
					System.out.println(defender.getName() + " blocked " + name + "'s' attack!");
					System.out.println(defender.getName() + "'s' HP is still: " + defender.getEntHP() + "\n");
				}
				// If the attack wasn't blocked take the extra damage from the defenders's health
				else
				{
					defender.setEntHP((defender.getEntHP() + defender.getEntBlock()) - damageDealt);
					System.out.println(name + " broke " + defender.getName() + "'s' block and did " + -(defender.getEntBlock() - damageDealt) + " damage to "
						+ defender.getName() + "!");

					// Check to see if attacker killed the defender
					if (defender.getEntHP() > 0)
					{
						System.out.println("His health is now: " + defender.getEntHP() + "\n");
					}
					else
					{
						System.out.println(atkName + " killed " + defender.getName() + "!\n");
						defender.setIsAlive = false;
					}
				}
			}
		}

		// If the attacker missed
		else
		{
			System.out.println(name + " missed!");
			System.out.println(defender.getName() + "'s health is still: " + defender.getEntHP() + "\n");
		}
	}

	public void defend() {
		entBlock = 5 + (entSTR + entEND/2);

		System.out.println(name + " will defend the next physical attack for up to " + entBlock + " damage!\n");
	}

	public void beginCombat(Ent_Combat enemy) {
		// Clear the screen
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

		int turnNum = 1;
		System.out.println("Prepare yourself " + player.getName() + "You have begun combat with " + enemy.getName());

		while (player.getIsAlive() && enemy.getIsAlive()) {
			System.out.println("Your HP: " + player.getEntHP());
			System.out.println(enemy.getName() + "'s HP: " + enemy.getHP());
			player.turn(enemy);
			if (enemy.getIsAlive())
				enemy.turn(player, turnNum);
			turnNum++;
		}

		if (player.getIsAlive())
			Game.winGame();
		else 
			Game.loseGame();
	}
}