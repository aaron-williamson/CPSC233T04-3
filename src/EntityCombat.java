import java.util.Random;

public class EntityCombat extends EntityMovable {

	// Lots of variables, protected makes life easier unless prof/TA specifies otherwise
	protected int entEND = 5;
	protected int entSTR = 5;
	protected int entLCK = 5;
	protected int entHP = entEND * 10;
	protected int entHIT = (80 + (entLCK / 2)) - 1;
	protected int entCRIT = entLCK - 1;
	protected int entBlock = 0;
	protected boolean isAlive = true;
	protected String name;

	public void turn(EntityCombat enemy, int turnNum) {

	}
	
	// Random number generator for this class
	private Random rand = new Random();

	private boolean isCrit() {
		boolean criticalHit = false;

		int crit = rand.nextInt(100);

		if (crit <= entCRIT) {
			criticalHit = true;
			System.out.print("CRITICAL HIT! ");
		}

		return criticalHit;
	}

	private boolean isHit() {
		int hit = rand.nextInt(100);
		return (hit <= entHIT);
	}

	private int damage() {
		if (isCrit())
			return (2 * (entSTR + rand.nextInt(10 + (2 * (entLCK / 2)))));
		else
			return (entSTR + rand.nextInt(10 + (2 * (entLCK / 2))));
	}

	public void attack(EntityCombat defender) {

		// If the attack hits
		if (isHit())
		{
			// Get the damage dealt (including whether or not it is a critical hit)
			int damageDealt = damage();

			// If the defender is not blocking do damage to health
			if (defender.entBlock == 0)
			{
				defender.entHP = defender.entHP - damageDealt;
				System.out.println(name + " dealt " + damageDealt + " damage to " + defender.name + "!");
				// Check to see if attacker killed the defender
				if (defender.entHP > 0)
				{
					System.out.println(defender.name + "'s health is now: " + defender.entHP + "\n");
				}
				else
				{
					System.out.println(name + " killed " + defender.name + "!\n");
					defender.isAlive = false;
				}
			}

			// If the defender is blocking calculate blocked damage and damage/dealt
			else
			{
				boolean blockBroken;

				if (damageDealt <= defender.entBlock)
					blockBroken = false;
				else
					blockBroken = true;

				// If the defender blocked the entire attack
				if (!blockBroken)
				{
					System.out.println(defender.name + " blocked " + name + "'s attack!");
					System.out.println(defender.name + "'s HP is still: " + defender.entHP + "\n");
				}
				// If the attack wasn't blocked take the extra damage from the defenders's health
				else
				{
					defender.entHP = defender.entHP + (defender.entBlock - damageDealt);
					System.out.println(name + " broke " + defender.name + "'s block and did " + -(defender.entBlock - damageDealt) + " damage to "
						+ defender.name + "!");

					// Check to see if attacker killed the defender
					if (defender.entHP > 0)
					{
						System.out.println(defender.name + "'s health is now: " + defender.entHP + "\n");
					}
					else
					{
						System.out.println(name + " killed " + defender.name + "!\n");
						defender.isAlive = false;
					}
				}
			}
		}

		// If the attacker missed
		else
		{
			System.out.println(name + " missed!");
			System.out.println(defender.name + "'s health is still: " + defender.entHP + "\n");
		}
	}

	public void defend() {
		entBlock = 5 + ((entSTR + entEND)/2);

		System.out.println(name + " will defend the next physical attack for up to " + entBlock + " damage!\n");
	}
}