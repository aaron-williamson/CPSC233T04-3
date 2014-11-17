public class EntityMovable extends Entity{
	public String getClassID(){return "movable_entity";}
	private int moveSpeed=4;
	private boolean moving=false;
	private int oldX;//used for interpolation of entity position
	private int oldY;
	
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
			oldX=getX();
			oldY=getY();
			setPos(x,y);
			moving=true;
			setNextThink(Game.getGame().getTime()+Game.getGame().getTimerSpeed()*getMoveSpeed());
			return true;
		}else{
			return false;
		}
	};
	
	/**
	 * Sets the Entity's movement speed,
	 * it takes the entity this many game ticks to move from one tile to the next
	 * @param speed
	 */
	public void setMoveSpeed(int speed){
		if(speed>0){
			moveSpeed=speed;
		}
	}
	
	/**
	 * get the old x pos before this entity last moved
	 * @return
	 */
	private int getOldX(){
		return oldX;
	}
	
	/**
	 * get the old y pos before this entity last moved
	 * @return
	 */
	private int getOldY(){
		return oldY;
	}
	
	/**
	 * get the interpolation value between 0 and 1 for movement interpolation
	 * @return
	 */
	private double getMovementInterp(){
		return (double)(Game.getGame().getTime()-getNextThink())/(double)(Game.getGame().getTimerSpeed()*getMoveSpeed());
	}
	
	/**
	 * get the Y interpolation offset
	 * @return
	 */
	public double getInterpolationY(){
		if(isMoving()){
			return -(getOldY()-getY())*getMovementInterp();
		}else{
			return 0;
		}
	}
	
	/**
	 * get the x interpolation offset
	 * @return
	 */
	public double getInterpolationX(){
			if(isMoving()){
				return -(getOldX()-getX())*getMovementInterp();
			}else{
				return 0;
			}
	}
	
	/**
	 * get the Entity's movement speed.
	 * it takes the entity this many game ticks to move from one tile to the next
	 * @return
	 */
	public int getMoveSpeed(){
		return moveSpeed;
	}
	
	/**
	 * get weather the entity can move,
	 * @return
	 */
	public boolean isMovable(){
		return true;
	}
	
	/**
	 * get weather the entity is currently moving or not
	 * @return
	 */
	public boolean isMoving(){
		return moving;
	}
	
	public void think(long time){
		moving=false;
	}
	
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