/*
	Basic combat system for T04 - 3 RPG
	Assumes bandit encounter
	V1.0 
*/

// Import scanner and random number generator
import java.util.Scanner;
import java.util.Random;

public class CombatV1
{
	public static void main(String[] args)
	{
		// Initialize scanner and random number generator
		Scanner input = new Scanner(System.in);
		Random rand = new Random();

		// Initialize player stats (player is warrior for now)
		int playerSTR = 15;
		int playerINT = 5;
		int playerEND = 10;
		int playerLCK = 5;

		// Initialize bandit stats (modify for testing)
		int banditSTR = 10;
		int banditINT = 3;
		int banditEND = 10;
		int banditLCK = 5;

		// Initialize player and bandit health
		int playerHealth = playerEND * 10;
		int banditHealth = banditEND * 10;

		// Initialize the player and bandit block values
		int playerBlock = 0;
		int banditBlock = 0;

		// Initialize player and bandit hit chance and critical chance
		int playerHitChance = (80 + (playerLCK / 2)) - 1;
		//int playerCritChance = playerLCK - 1;
		int playerCritChance = 50;
		int banditHitChance = (80 + (banditLCK / 2)) - 1;
		int banditCritChance = banditLCK - 1;

		// Initialize Booleans
		boolean playerDead = false;
		boolean banditDead = false;

		// Initialize the turn counter
		int turn = 0;

		// Prompt player for their name and store it as a string
		//System.out.print("Please enter your name: ");
		//String playerName = input.nextLine();

		// Output the introduction
		//System.out.println("Welcome " + playerName + "! Prepare to fight a Bandit.");

		// Start the combat loop
		while(!playerDead && !banditDead)
		{
			// Initialize the player's block value to zero
			playerBlock = 0;

			// Player's turn, output the player's HP and bandit's HP
			//System.out.println("It is your turn, " + playerName + "!");
			System.out.println("Player HP: " + playerHealth);
			System.out.println("Bandit HP: " + banditHealth);

			// Prompt for user's action
			System.out.println("Would you like to attack or defend? ");
			String playerCommand = input.next();

			// If the player attacks
			if (playerCommand.equals("attack") || playerCommand.equals("Attack") || playerCommand.equals("a") || playerCommand.equals("A"))
			{
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

			// If the player defends
			else if (playerCommand.equals("defend") || playerCommand.equals("Defend") || playerCommand.equals("d") || playerCommand.equals("D"))
			{
				playerBlock = 5 + ((playerSTR + playerEND)/2);
				System.out.println("You will defend the next physical attack for up to " + playerBlock + " damage!\n");
			}

			// If the input is invalid
			else
			{
				System.out.println("That input is not valid.\n");
				break;
			}

			// End player turn

			// Check bandit's health and continue if he's alive, if he's not, skip this.
			if (banditHealth > 0)
			{

				// Begin bandit turn
				System.out.println("It is the bandit's turn now!");

				// Initialize the bandit's block value to 0
				banditBlock = 0;

				// If it's the first turn of the fight, the bandit will always attack
				if (turn == 0)
				{
					// Output that the bandit is attacking
					System.out.println("The bandit attacks you!");

					// Check first to see if the attack hits
					int attackHit = rand.nextInt(100);

					// If the bandit hits
					if (attackHit <= banditHitChance)
					{
						// Determine if it was a critical hit
						int criticalHit = rand.nextInt(100);
						// If it's a critical hit
						if (criticalHit <= banditCritChance)
						{
							// Calculate bandits damage dealt
							int damageDealt = 2 * (banditSTR + rand.nextInt(10 + (2 * (banditLCK / 2))));

							// If the player isn't blocking do damage to health
							if (playerBlock == 0)
							{
								playerHealth = playerHealth - damageDealt;
								System.out.println("CRITICAL HIT! The bandit did " + damageDealt + " damage to you!");
								if (playerHealth > 0)
								{
									System.out.println("Your health is now: " + playerHealth + "\n");
								}
								else
								{
									System.out.println("The bandit killed you!\n");
									playerDead = true;
								}
							}
							// If the player is blocking calculate blocked damage and damage/dealt
							else
							{
								playerBlock = playerBlock - damageDealt;

								// If the player blocked the entire attack
								if (banditBlock >= 0)
								{
									System.out.println("You blocked the bandit's attack!");
									System.out.println("Your HP is still: " + playerHealth + "\n");
								}
								// If the attack wasn't blocked take the extra damage from the bandit's health
								else
								{
									playerHealth = playerHealth + playerBlock;
									System.out.println("CRITICAL HIT! The bandit broke your block and did " + -playerBlock + " damage to you!");
									if (playerHealth > 0)
									{
										System.out.println("Your health is now: " + playerHealth + "\n");
									}
									else
									{
										System.out.println("The bandit killed you!\n");
										playerDead = true;
									}
								}
							}
						}
						// If it's not a critical hit
						else
						{
							// Calculate bandit's damage dealt
							int damageDealt = banditSTR + rand.nextInt(10 + (2 * (banditLCK / 2)));

							// If the player isn't blocking do damage to health
							if (playerBlock == 0)
							{
								playerHealth = playerHealth - damageDealt;
								System.out.println("The bandit dealt " + damageDealt + " damage to you!");
								if (playerHealth > 0)
								{
									System.out.println("Your health is now: " + playerHealth + "\n");
								}
								else
								{
									System.out.println("The bandit killed you!\n");
									playerDead = true;
								}
							}
							// If the bandit is blocking calculate blocked damage and damage/dealt
							else
							{
								playerBlock = playerBlock - damageDealt;

								// If the player blocked the entire attack
								if (banditBlock >= 0)
								{
									System.out.println("You blocked the bandit's attack!");
									System.out.println("Your HP is still: " + playerHealth + "\n");
								}
								// If the attack wasn't blocked take the extra damage from the bandit's health
								else
								{
									playerHealth = playerHealth + banditBlock;
									System.out.println("The bandit broke your block and did " + -playerBlock + " damage to you!");
									if (playerHealth > 0)
									{
										System.out.println("Your health is now: " + playerHealth + "\n");
									}
									else
									{
										System.out.println("The bandit killed you!\n");
										playerDead = true;
									}
								}
							}
						}
					}

					// If the bandit missed
					else
					{
						System.out.println("The bandit missed!");
						System.out.println("Your health is still: " + playerHealth + "\n");
					}
				}

				// If it's not the first turn, use the bandit's health and a random number to determine attack or defend
				else
				{
					// Generate the random number
					int banditAction = rand.nextInt(5);

					// If the bandit is above or at 50% HP he attacks more often
					if (banditHealth >= (banditHealth / 2))
					{
						// A 80% chance the bandit will attack and 20% chance he will defend
						if (banditAction > 0)
						{
							// Output that the bandit is attacking
							System.out.println("The bandit attacks you!");

							// Check first to see if the attack hits
							int attackHit = rand.nextInt(100);

							// If the bandit hits
							if (attackHit <= banditHitChance)
							{
								// Determine if it was a critical hit
								int criticalHit = rand.nextInt(100);
								// If it's a critical hit
								if (criticalHit <= banditCritChance)
								{
									// Calculate bandits damage dealt
									int damageDealt = 2 * (banditSTR + rand.nextInt(10 + (2 * (banditLCK / 2))));

									// If the player isn't blocking do damage to health
									if (playerBlock == 0)
									{
										playerHealth = playerHealth - damageDealt;
										System.out.println("CRITICAL HIT! The bandit did " + damageDealt + " damage to you!");
										if (playerHealth > 0)
										{
											System.out.println("Your health is now: " + playerHealth + "\n");
										}
										else
										{
											System.out.println("The bandit killed you!\n");
											playerDead = true;
										}
									}
									// If the player is blocking calculate blocked damage and damage/dealt
									else
									{
										playerBlock = playerBlock - damageDealt;

										// If the player blocked the entire attack
										if (banditBlock >= 0)
										{
											System.out.println("You blocked the bandit's attack!");
											System.out.println("Your HP is still: " + playerHealth + "\n");
										}
										// If the attack wasn't blocked take the extra damage from the bandit's health
										else
										{
											playerHealth = playerHealth + playerBlock;
											System.out.println("CRITICAL HIT! The bandit broke your block and did " + -playerBlock + " damage to you!");
											if (playerHealth > 0)
											{
												System.out.println("Your health is now: " + playerHealth + "\n");
											}
											else
											{
												System.out.println("The bandit killed you!\n");
												playerDead = true;
											}
										}
									}
								}
								// If it's not a critical hit
								else
								{
									// Calculate bandit's damage dealt
									int damageDealt = banditSTR + rand.nextInt(10 + (2 * (banditLCK / 2)));

									// If the player isn't blocking do damage to health
									if (playerBlock == 0)
									{
										playerHealth = playerHealth - damageDealt;
										System.out.println("The bandit dealt " + damageDealt + " damage to you!");
										if (playerHealth > 0)
										{
											System.out.println("Your health is now: " + playerHealth + "\n");
										}
										else
										{
											System.out.println("The bandit killed you!\n");
											playerDead = true;
										}
									}
									// If the bandit is blocking calculate blocked damage and damage/dealt
									else
									{
										playerBlock = playerBlock - damageDealt;

										// If the player blocked the entire attack
										if (banditBlock >= 0)
										{
											System.out.println("You blocked the bandit's attack!");
											System.out.println("Your HP is still: " + playerHealth + "\n");
										}
										// If the attack wasn't blocked take the extra damage from the bandit's health
										else
										{
											playerHealth = playerHealth + banditBlock;
											System.out.println("The bandit broke your block and did " + -playerBlock + " damage to you!");
											if (playerHealth > 0)
											{
												System.out.println("Your health is now: " + playerHealth + "\n");
											}
											else
											{
												System.out.println("The bandit killed you!\n");
												playerDead = true;
											}
										}
									}
								}
							}

							// If the bandit missed
							else
							{
								System.out.println("The bandit missed!");
								System.out.println("Your health is still: " + playerHealth + "\n");
							}
						}
						else
						{
							// Output that the bandit is blocking
							System.out.println("The bandit readies his defense!");

							banditBlock = 5 + ((banditSTR + banditEND)/2);
							System.out.println("The bandit will defend the next physical attack for up to " + banditBlock + " damage!\n");
						}
					}
					// If the bandit is below 50% HP he blocks more often
					else
					{
						// A 40% chance the bandit will attack and 60% chance he will defend
						if (banditAction > 2)
						{
							// Output that the bandit is attacking
							System.out.println("The bandit attacks!");

							// Check first to see if the attack hits
							int attackHit = rand.nextInt(100);

							// If the bandit hits
							if (attackHit <= banditHitChance)
							{
								// Determine if it was a critical hit
								int criticalHit = rand.nextInt(100);
								// If it's a critical hit
								if (criticalHit <= banditCritChance)
								{
									// Calculate bandits damage dealt
									int damageDealt = 2 * (banditSTR + rand.nextInt(10 + (2 * (banditLCK / 2))));

									// If the player isn't blocking do damage to health
									if (playerBlock == 0)
									{
										playerHealth = playerHealth - damageDealt;
										System.out.println("CRITICAL HIT! The bandit did " + damageDealt + " damage to you!");
										if (playerHealth > 0)
										{
											System.out.println("Your health is now: " + playerHealth + "\n");
										}
										else
										{
											System.out.println("The bandit killed you!\n");
											playerDead = true;
										}
									}
									// If the player is blocking calculate blocked damage and damage/dealt
									else
									{
										playerBlock = playerBlock - damageDealt;

										// If the player blocked the entire attack
										if (banditBlock >= 0)
										{
											System.out.println("You blocked the bandit's attack!");
											System.out.println("Your HP is still: " + playerHealth + "\n");
										}
										// If the attack wasn't blocked take the extra damage from the bandit's health
										else
										{
											playerHealth = playerHealth + playerBlock;
											System.out.println("CRITICAL HIT! The bandit broke your block and did " + -playerBlock + " damage to you!");
											if (playerHealth > 0)
											{
												System.out.println("Your health is now: " + playerHealth + "\n");
											}
											else
											{
												System.out.println("The bandit killed you!\n");
												playerDead = true;
											}
										}
									}
								}
								// If it's not a critical hit
								else
								{
									// Calculate bandit's damage dealt
									int damageDealt = banditSTR + rand.nextInt(10 + (2 * (banditLCK / 2)));

									// If the player isn't blocking do damage to health
									if (playerBlock == 0)
									{
										playerHealth = playerHealth - damageDealt;
										System.out.println("The bandit dealt " + damageDealt + " damage to you!");
										if (playerHealth > 0)
										{
											System.out.println("Your health is now: " + playerHealth + "\n");
										}
										else
										{
											System.out.println("The bandit killed you!\n");
											playerDead = true;
										}
									}
									// If the bandit is blocking calculate blocked damage and damage/dealt
									else
									{
										playerBlock = playerBlock - damageDealt;

										// If the player blocked the entire attack
										if (banditBlock >= 0)
										{
											System.out.println("You blocked the bandit's attack!");
											System.out.println("Your HP is still: " + playerHealth + "\n");
										}
										// If the attack wasn't blocked take the extra damage from the bandit's health
										else
										{
											playerHealth = playerHealth + banditBlock;
											System.out.println("The bandit broke your block and did " + -playerBlock + " damage to you!");
											if (playerHealth > 0)
											{
												System.out.println("Your health is now: " + playerHealth + "\n");
											}
											else
											{
												System.out.println("The bandit killed you!\n");
												playerDead = true;
											}
										}
									}
								}
							}

							// If the bandit missed
							else
							{
								System.out.println("The bandit missed!");
								System.out.println("Your health is still: " + playerHealth + "\n");
							}
						}
						else
						{
							// Output that the bandit is defending
							System.out.println("The bandit readies his defense!");

							banditBlock = 5 + ((banditSTR + banditEND)/2);
							System.out.println("The bandit will defend the next physical attack for up to " + banditBlock + " damage!\n");
						}
					}
				}
			}

			else
			{
				banditDead=true;
			}

			turn++;
		}

		// If the player died output game over
		if (playerDead)
		{
			System.out.println("GAME OVER");
		}
		else if (banditDead)
		{
			System.out.println("You won!");
		}
		else
		{
			System.out.println("Something weird happened.");
		}
	}
}