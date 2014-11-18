public class EnemyCultist extends EntityEnemy {
	public EnemyCultist(int xcoord, int ycoord) {
		super(xcoord, ycoord);
		combatEndurance = 8;
		combatStrength = 20;
		combatLuck = 10;
		setHealth(getMaxHealth());
		combatName = "Cultist";
		combatXP = 4;
	}
}
