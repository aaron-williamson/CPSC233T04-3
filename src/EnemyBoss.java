public class EnemyBoss extends EntityEnemy {
	public EnemyBoss() {
		combatEndurance = 20;
		combatStrength = 30;
		combatLuck = 15;
		setHealth(getMaxHealth());
		combatName = "BIG BAD BOSS";
		combatXP = 9001;
	}
}