public class EnemyBat extends EntityEnemy {
	public EnemyBat(int xcoord, int ycoord) {
		super(xcoord, ycoord);
		combatEndurance = 3;
		combatStrength = 15;
		combatLuck = 10;
		setHealth(getMaxHealth());
		combatName = "Bat";
		combatXP = 2;
		combatAttackMessage="flies at you violently!";
		combatCriticalMessage="IT GOT YOU RIGHT IN THE EYE!!";
		combatDefendMessage="hides itself with its wings.";
		setMoveSpeed(5);
	}
}