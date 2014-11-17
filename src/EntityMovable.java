public class EntityMovable extends Entity{
	public String getClassID(){return "movable_entity";}
	
	public boolean moveTo(int x,int y){
		boolean domove=false;
		
		if(Game.getGame().rpgmap.isPassable(x,y)){
			Entity collidedent=Game.getGame().getEntities().getEntInPos(x,y);
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
			setNextThink(Game.getGame().getTime()+Game.getGame().getTimerSpeed()*10);
			return true;
		}else{
			return false;
		}
	};
	
	public boolean moveUp(){
		return moveTo(this.getX(),this.getY()-1);
	};
	public boolean moveDown(){
		return moveTo(this.getX(),this.getY()+1);
	};
	public boolean moveLeft(){
		return moveTo(this.getX()-1,this.getY());
	};
	public boolean moveRight(){
		return moveTo(this.getX()+1,this.getY());
	};
	
};