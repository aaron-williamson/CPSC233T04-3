public class EnemyBat extends EntityEnemy {
	public EnemyBat() {
		super();
		combatEndurance = 3;
		combatStrength = 15;
		combatLuck = 10;
		setHealth(getMaxHealth());
		combatName = "Bat";
		combatXP = 2;
	}
}