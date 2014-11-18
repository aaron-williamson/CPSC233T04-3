public class EnemyWolf extends EntityEnemy {
	public EnemyWolf() {
		super();
		combatEndurance = 6;
		combatStrength = 12;
		combatLuck = 0;
		setHealth(getMaxHealth());
		combatName = "Wolf";
		combatXP = 1;
	}
}
