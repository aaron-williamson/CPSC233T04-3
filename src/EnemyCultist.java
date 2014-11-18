public class EnemyCultist extends EntityEnemy {
	public EnemyCultist() {
		entEND = 8;
		entSTR = 20;
		entLCK = 10;
		entHP = entEND * 10;
		entHIT = (80 + (entLCK / 2)) - 1;
		entCRIT = entLCK - 1;
		name = "Cultist";
		givesExp = 4;
	}
}