public class EnemyMummy extends EntityEnemy {
	public EnemyMummy(int xcoord, int ycoord) {
		super(xcoord, ycoord);
		combatEndurance = 10;
		combatStrength = 12;
		combatLuck = 0;
		setHealth(getMaxHealth());
		combatName = "Mummy";
		combatXP = 2;
	}
}
