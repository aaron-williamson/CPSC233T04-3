public class EnemyForestBandit extends EntityEnemy {
	public EnemyForestBandit() {
		combatEndurance = 6;
		combatStrength = 12;
		combatLuck = 0;
		setHealth(getMaxHealth());
		combatName = "Forest Bandit";
		combatXP = 1;
	}
}