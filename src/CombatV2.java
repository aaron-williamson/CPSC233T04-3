import java.util.List;

public class CombatV2
{
	public void beginCombat() {

	}

	public void attack() {
		// Check first to see if the attack hits
		int attackHit = rand.nextInt(100);

		// If the player hits
		if (attackHit <= playerHitChance)
		{
			// Determine if it was a critical hit
			int criticalHit = rand.nextInt(100);
			// If it's a critical hit
			if (criticalHit <= playerCritChance)
			{
				// Calculate player's damage dealt
				int damageDealt = 2 * (playerSTR + rand.nextInt(10 + (2 * (playerLCK / 2))));

				// If the bandit isn't blocking do damage to health
				if (banditBlock == 0)
				{
					banditHealth = banditHealth - damageDealt;
					System.out.println("CRITICAL HIT! You dealt " + damageDealt + " damage to the bandit!");
					// Check to see if player killed the bandit
					if (banditHealth > 0)
					{
						System.out.println("His health is now: " + banditHealth + "\n");
					}
					else
					{
						System.out.println("You have killed the bandit!\n");
						banditDead = true;
					}
				}
				// If the bandit is blocking calculate blocked damage and damage/dealt
				else
				{
					banditBlock = banditBlock - damageDealt;

					// If the bandit blocked the entire attack
					if (banditBlock >= 0)
					{
						System.out.println("The bandit blocked your attack!");
						System.out.println("His HP is still: " + banditHealth + "\n");
					}
					// If the attack wasn't blocked take the extra damage from the bandit's health
					else
					{
						banditHealth = banditHealth + banditBlock;
						System.out.println("CRITICAL HIT! You broke the bandit's block and did " + -banditBlock + " damage to him!");
						// Check to see if player killed the bandit
						if (banditHealth > 0)
						{
							System.out.println("His health is now: " + banditHealth + "\n");
						}
						else
						{
							System.out.println("You have killed the bandit!\n");
							banditDead = true;
						}
					}
				}
			}
			// If it's not a critical hit
			else
			{
				// Calculate player's damage dealt
				int damageDealt = playerSTR + rand.nextInt(10 + (2 * (playerLCK / 2)));

				// If the bandit isn't blocking do damage to health
				if (banditBlock == 0)
				{
					banditHealth = banditHealth - damageDealt;
					System.out.println("You dealt " + damageDealt + " damage to the bandit!");
					// Check to see if player killed the bandit
					if (banditHealth > 0)
					{
						System.out.println("His health is now: " + banditHealth + "\n");
					}
					else
					{
						System.out.println("You have killed the bandit!\n");
						banditDead = true;
					}
				}
				// If the bandit is blocking calculate blocked damage and damage/dealt
				else
				{
					banditBlock = banditBlock - damageDealt;

					// If the bandit blocked the entire attack
					if (banditBlock >= 0)
					{
						System.out.println("The bandit blocked your attack!");
						System.out.println("His HP is still: " + banditHealth + "\n");
					}
					// If the attack wasn't blocked take the extra damage from the bandit's health
					else
					{
						banditHealth = banditHealth + banditBlock;
						System.out.println("You broke the bandit's block and did " + -banditBlock + " damage to him!");
						// Check to see if player killed the bandit
						if (banditHealth > 0)
						{
							System.out.println("His health is now: " + banditHealth + "\n");
						}
						else
						{
							System.out.println("You have killed the bandit!\n");
							banditDead = true;
						}
					}
				}
			}
		}

		// If the player missed
		else
		{
			System.out.println("You missed!");
			System.out.println("The bandit's health is still: " + banditHealth + "\n");
		}
	}

	public void defend() {
		playerBlock = 5 + ((playerSTR + playerEND)/2);
		System.out.println("You will defend the next physical attack for up to " + playerBlock + " damage!\n");
	}
}