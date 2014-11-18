public class EnemyBoss extends EntityEnemy {
	public EnemyBoss(int xcoord, int ycoord) {
		super(xcoord, ycoord);
		combatEndurance = 20;
		combatStrength = 30;
		combatLuck = 15;
		setHealth(getMaxHealth());
		combatName = "BIG BAD BOSS";
		combatXP = 9001;
	}
}