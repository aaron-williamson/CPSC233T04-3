public class EnemyBoss extends EntityEnemy {
	public EnemyBoss() {
		entEND = 20;
		entSTR = 30;
		entLCK = 15;
		entHP = entEND * 10;
		entHIT = (80 + (entLCK / 2)) - 1;
		entCRIT = entLCK - 1;
		name = "BIG BAD BOSS";
		givesExp = 9001;
	}
}