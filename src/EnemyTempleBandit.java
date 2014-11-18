public class EnemyTempleBandit extends EntityEnemy {
	public EnemyTempleBandit() {
		entEND = 8;
		entSTR = 15;
		entLCK = 5;
		entHP = entEND * 10;
		entHIT = (80 + (entLCK / 2)) - 1;
		entCRIT = entLCK - 1;
		name = "Temple Bandit";
		givesExp = 2;
	}
}