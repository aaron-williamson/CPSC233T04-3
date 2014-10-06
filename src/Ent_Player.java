import java.util.Scanner;

public class Ent_Player extends Ent_Movable{
	String getClassID(){return "player";}
	
	String debuggraphic(){return "P";}
	
	//lose the game if something runs into the player
	void onCollide(Entity ent){
		Game.loseGame();
	};
	
	void think(){
		boolean needmove=true;

		
		while(needmove){
			System.out.printf("What direction would you like to move (N/S/E/W)? ");
			String movestr="";
			
			movestr=Game.in.nextLine();//get input string
			
			
			if(movestr.equalsIgnoreCase("n")){
				needmove=!moveUp(true);
			}else if(movestr.equalsIgnoreCase("e")){
				needmove=!moveRight(true);
			}else if(movestr.equalsIgnoreCase("s")){
				needmove=!moveDown(true);
			}else if(movestr.equalsIgnoreCase("w")){
				needmove=!moveLeft(true);
			}
		}
		
	}
};