public class EnemyCultist extends EntityEnemy {
	public EnemyCultist(int xcoord, int ycoord) {
		super(xcoord, ycoord);
		combatEndurance = 8;
		combatStrength = 20;
		combatLuck = 10;
		setHealth(getMaxHealth());
		combatName = "Cultist";
		combatXP = 4;
		combatAttackMessage="chants some gibberish so loud that it hurts!";
		combatMissMessage="He didn't chant loud enough!";
		combatCriticalMessage="It turned out to be a Celine Dion song!";
		combatDefendMessage="sits down and starts meditating, negating some of your damage.";
		aggroDistance = 8;
	}
}
