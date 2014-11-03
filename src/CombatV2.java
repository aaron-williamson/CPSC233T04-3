import java.util.Random;

public class CombatV2
{
	private Random rand = new Random();

	private boolean isCrit(Entity ent) {
		boolean criticalHit = false;

		int crit = rand.nextInt(100);

		if (crit <= ent.getEntCRIT()) {
			criticalHit = true;
			System.out.print("CRITICAL HIT! ");
		}

		return criticalHit;
	}

	private boolean isHit(Entity ent) {
		boolean entHit = false;

		int hit = rand.nextInt(100);

		if (hit <= ent.getEntHIT())
			entHit = true;

		return entHit;
	}

	private int damage(Entity ent) {
		int damageDealt;
		if (isCrit(ent))
			damageDealt = 2 * (ent.getEntSTR() + rand.nextInt(10 + (2 * (ent.getEntLCK() / 2))));
		else
			damageDealt = ent.getEntSTR() + rand.nextInt(10 + (2 * (ent.getEntLCK() / 2)));

		return damageDealt;
	}

	public void attack(Entity attacker, Entity defender) {

		// If the attack hits
		if (isHit(attacker))
		{
			// Get the damage dealt (including whether or not it is a critical hit)
			int damageDealt = damage(attacker);

			// If the defender is not blocking do damage to health
			if (defender.getEntBlock() == 0)
			{
				defender.setEntHP(defender.getEntHP() - damageDealt);
				System.out.println(attacker.getName() + " dealt " + damageDealt + " damage to " + defender.getName() + "!");
				// Check to see if attacker killed the defender
				if (defender.getEntHP() > 0)
				{
					System.out.println(defender.getName() + "'s health is now: " + defender.getEntHP() + "\n");
				}
				else
				{
					System.out.println(attacker.getName() + " killed " + defender.getName() + "!\n");
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
					System.out.println(defender.getName() + " blocked " + attacker.getName() + "'s' attack!");
					System.out.println(defender.getName() + "'s' HP is still: " + defender.getEntHP() + "\n");
				}
				// If the attack wasn't blocked take the extra damage from the defenders's health
				else
				{
					defender.setEntHP((defender.getEntHP() + defender.getEntBlock()) - damageDealt);
					System.out.println(attacker.getName() + " broke " + defender.getName() + "'s' block and did " + -(defender.getEntBlock() - damageDealt) + " damage to " + defender.getName() + "!");

					// Check to see if attacker killed the defender
					if (defender.getEntHP() > 0)
					{
						System.out.println("His health is now: " + defender.getEntHP() + "\n");
					}
					else
					{
						System.out.println(attacker.getName() + " killed " + defender.getName() + "!\n");
						defender.setIsAlive = false;
					}
				}
			}
		}

		// If the attacker missed
		else
		{
			System.out.println(attacker.getName() + " missed!");
			System.out.println(defender.getName() + "'s health is still: " + defender.getEntHP() + "\n");
		}
	}

	public void defend(Entity ent) {
		int entBlock = 5 + ((ent.getEntSTR() + ent.getEntEND())/2);

		ent.setBlock(entBlock);

		System.out.println("You will defend the next physical attack for up to " + entBlock + " damage!\n");
	}

	

	public void beginCombat(Entity player, Entity enemy) {
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
	}
}