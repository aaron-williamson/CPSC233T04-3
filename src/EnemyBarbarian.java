public class EnemyBarbarian extends EntityEnemy {
	public EnemyBarbarian() {
		super();
		combatEndurance = 12;
		combatStrength = 20;
		combatLuck = 0;
		setHealth(getMaxHealth());
		combatName = "Barbarian";
		combatXP = 4;
	}
}