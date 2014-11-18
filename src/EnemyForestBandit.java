public class EnemyForestBandit extends EntityEnemy {
	public EnemyForestBandit(int xcoord, int ycoord) {
		super(xcoord, ycoord);
		combatEndurance = 6;
		combatStrength = 12;
		combatLuck = 0;
		setHealth(getMaxHealth());
		combatName = "Forest Bandit";
		combatXP = 1;
		combatAttackMessage="stabs at you with a butter knife!";
		combatCriticalMessage="He buttered your bread!";
	}
}
