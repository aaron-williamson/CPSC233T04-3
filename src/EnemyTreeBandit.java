public class EnemyTreeBandit extends EntityEnemy {
	public EnemyTreeBandit(int xcoord, int ycoord) {
		super(xcoord, ycoord);
		combatEndurance = 6;
		combatStrength = 12;
		combatLuck = 3;
		setHealth(getMaxHealth());
		combatName = "Tree";
		combatXP = 1;
	}
}
