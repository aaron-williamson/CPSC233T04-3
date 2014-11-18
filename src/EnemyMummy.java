public class EnemyMummy extends EntityEnemy {
	public EnemyMummy() {
		entEND = 10;
		entSTR = 12;
		entLCK = 0;
		entHP = entEND * 10;
		entHIT = (80 + (entLCK / 2)) - 1;
		entCRIT = entLCK - 1;
		name = "Mummy";
		givesExp = 2;
	}
}