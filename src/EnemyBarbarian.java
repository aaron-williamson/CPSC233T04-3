public class EnemyBarbarian extends EntityEnemy {
	public EnemyBarbarian(int xcoord, int ycoord) {
		super(xcoord, ycoord);
		combatEndurance = 12;
		combatStrength = 20;
		combatLuck = 0;
		setHealth(getMaxHealth());
		combatName = "Barbarian";
		combatXP = 4;
		combatAttackMessage="swing's his axe!";
	}
}