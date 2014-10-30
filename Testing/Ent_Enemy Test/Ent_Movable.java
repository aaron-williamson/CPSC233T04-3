public class Ent_Movable extends Entity{
	String getClassID(){return "movable_entity";}
	
	String debuggraphic(){return "M";}
	
	boolean moveTo(int x,int y,boolean ... verbose){
		boolean domove=false;
		
		if(Game.rpgmap.isPassable(x,y)){
			Entity collidedent=Ents.getEntInPos(x,y);
			if(collidedent!=null){
				collidedent.onCollide(this);
				if(collidedent.isPassable()){
					domove=true;
				}
			}else{
				domove=true;
			}
		}
		
		if(domove){
			setPos(x,y);
			return true;
		}else{
			if(verbose!=null){
				System.out.println("You cant move that way!");
			}
			return false;
		}
	};
	
	boolean moveUp(boolean ... verbose){
		return moveTo(xpos,ypos-1,verbose);
	};
	boolean moveDown(boolean ... verbose){
		return moveTo(xpos,ypos+1,verbose);
	};
	boolean moveLeft(boolean ... verbose){
		return moveTo(xpos-1,ypos,verbose);
	};
	boolean moveRight(boolean ... verbose){
		return moveTo(xpos+1,ypos,verbose);
	};
	
};