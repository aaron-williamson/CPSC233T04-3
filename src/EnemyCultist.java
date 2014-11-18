public class EnemyCultist extends EntityEnemy {
	public EnemyCultist() {
		super();
		combatEndurance = 8;
		combatStrength = 20;
		combatLuck = 10;
		setHealth(getMaxHealth());
		combatName = "Cultist";
		combatXP = 4;
	}
}
