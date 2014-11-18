public class EnemyBat extends EntityEnemy {
	public EnemyBat() {
		entEND = 3;
		entSTR = 15;
		entLCK = 10;
		entHP = entEND * 10;
		entHIT = (80 + (entLCK / 2)) - 1;
		entCRIT = entLCK - 1;
		name = "Bat";
		givesExp = 2;
	}
}