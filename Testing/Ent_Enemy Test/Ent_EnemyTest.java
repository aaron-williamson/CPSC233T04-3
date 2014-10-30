//the only unique thing about ent_enemy is its think function,
//so all we'll need to test here is to see if it's moving the enemy correctly.

public class Ent_EnemyTest{
	public static void main(String[] args){
		Game.rpgmap.makeblankmap();//make a blank map for the testing
		
		//we'll add two test player entities, and check if the enemy goes to the closest one
		Ent_EnemyTestPlayer player1=new Ent_EnemyTestPlayer();
		player1.setPos(1,1);
		Ents.addEnt(player1);
		
		Ent_EnemyTestPlayer player2=new Ent_EnemyTestPlayer();
		player2.setPos(10,1);
		Ents.addEnt(player2);
		
		int enemystartxpos=3;
		
		//add the enemy, closer to player 1 so it should move left.
		Ent_Enemy enemy=new Ent_Enemy();
		enemy.setPos(3,1);
		Ents.addEnt(enemy);
		
		boolean testpassed=false;
		
		enemy.think();//make the enemy think, this should cause it to move one tile closer to the closest player
		
		testpassed=enemy.xpos==enemystartxpos-1;
		System.out.println("Ent_Enemy test passed: "+testpassed);
	}
};