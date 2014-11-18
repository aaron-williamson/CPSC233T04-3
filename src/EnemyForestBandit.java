public class EnemyForestBandit extends EntityEnemy {
	public EnemyForestBandit() {
		entEND = 6;
		entSTR = 12;
		entLCK = 0;
		entHP = entEND * 10;
		entHIT = (80 + (entLCK / 2)) - 1;
		entCRIT = entLCK - 1;
		name = "Forest Bandit";
		givesExp = 1;
	}
}