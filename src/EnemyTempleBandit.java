public class EnemyTempleBandit extends EntityEnemy {
	public EnemyTempleBandit() {
		combatEndurance = 8;
		combatStrength = 15;
		combatLuck = 5;
		setHealth(getMaxHealth());
		combatName = "Temple Bandit";
		combatXP = 2;
	}
}