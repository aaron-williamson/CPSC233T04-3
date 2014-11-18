public class EnemyTreeBandit extends EntityEnemy {
	public EnemyTreeBandit() {
		entEND = 6;
		entSTR = 12;
		entLCK = 3;
		entHP = entEND * 10;
		entHIT = (80 + (entLCK / 2)) - 1;
		entCRIT = entLCK - 1;
		name = "Tree";
		givesExp = 1;
	}
}