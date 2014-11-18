public class EnemyBoss extends EntityEnemy {
	public EnemyBoss(int xcoord, int ycoord) {
		super(xcoord, ycoord);
		combatEndurance = 20;
		combatStrength = 30;
		combatLuck = 15;
		setHealth(getMaxHealth());
		combatName = "BIG BAD BOSS";
		combatXP = 9001;
		combatAttackMessage="SWINGS AT YOU WITH HIS GIANT SWORD OF AWESOMENESS!";
		combatCriticalMessage="HIS ATTACK WAS JUST TOO AWESOME!!!!!!";
		combatDefendMessage="JUST STANDS THERE BEING SO AWESOME THAT YOUR ATTACKS DO LESS DAMAGE.";
	}

	public void think(long time){
	}
}