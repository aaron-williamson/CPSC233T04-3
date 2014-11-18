public class EnemyTempleBandit extends EntityEnemy {
	public EnemyTempleBandit(int xcoord, int ycoord) {
		super(xcoord, ycoord);
		combatEndurance = 8;
		combatStrength = 15;
		combatLuck = 5;
		setHealth(getMaxHealth());
		combatName = "Temple Bandit";
		combatXP = 2;
	}
}
