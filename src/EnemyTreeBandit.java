public class EnemyTreeBandit extends EntityEnemy {
	public EnemyTreeBandit() {
		super();
		combatEndurance = 6;
		combatStrength = 12;
		combatLuck = 3;
		setHealth(getMaxHealth());
		combatName = "Tree";
		combatXP = 1;
	}
}
