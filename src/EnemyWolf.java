public class EnemyWolf extends EntityEnemy {
	public EnemyWolf(int xcoord, int ycoord) {
		super(xcoord, ycoord);
		combatEndurance = 6;
		combatStrength = 12;
		combatLuck = 0;
		setHealth(getMaxHealth());
		combatName = "Wolf";
		combatXP = 1;
		combatAttackMessage="tries to bite you!";
	}
}
