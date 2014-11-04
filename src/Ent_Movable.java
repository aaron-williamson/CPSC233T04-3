public class Ent_Movable extends Entity{
	public String getClassID(){return "movable_entity";}
	
	public String debuggraphic(){return "M";}
	
	public boolean moveTo(int x,int y,boolean ... verbose){
		boolean domove=false;
		
		if(Game.rpgmap.isPassable(x,y)){
			Entity collidedent=Ents.getEntInPos(x,y);
			System.out.print(collidedent);
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
	
	public boolean moveUp(boolean ... verbose){
		return moveTo(this.getX(),this.getY()-1,verbose);
	};
	public boolean moveDown(boolean ... verbose){
		return moveTo(this.getX(),this.getY()+1,verbose);
	};
	public boolean moveLeft(boolean ... verbose){
		return moveTo(this.getX()-1,this.getY(),verbose);
	};
	public boolean moveRight(boolean ... verbose){
		return moveTo(this.getX()+1,this.getY(),verbose);
	};
	
};