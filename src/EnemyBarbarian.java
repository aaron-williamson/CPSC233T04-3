public class EnemyBarbarian extends EntityEnemy {
	public EnemyBarbarian() {
		entEND = 12;
		entSTR = 20;
		entLCK = 0;
		entHP = entEND * 10;
		entHIT = (80 + (entLCK / 2)) - 1;
		entCRIT = entLCK - 1;
		name = "Barbarian";
		givesExp = 4;
	}
}