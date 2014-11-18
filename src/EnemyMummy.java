public class EnemyMummy extends EntityEnemy {
	public EnemyMummy() {
		super();
		combatEndurance = 10;
		combatStrength = 12;
		combatLuck = 0;
		setHealth(getMaxHealth());
		combatName = "Mummy";
		combatXP = 2;
	}
}
