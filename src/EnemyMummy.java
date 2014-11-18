public class EnemyMummy extends EntityEnemy {
	public EnemyMummy(int xcoord, int ycoord) {
		super(xcoord, ycoord);
		combatEndurance = 10;
		combatStrength = 12;
		combatLuck = 0;
		setHealth(getMaxHealth());
		combatName = "Mummy";
		combatXP = 2;
		combatAttackMessage="swipes at you with what remains of it's arm!";
		combatCriticalMessage="Some of his arm broke off inside you! EW!";
		combatDefendMessage="stiffens up.";
	}
}
