package templeraider.entity;

import templeraider.Game;

public class EntityMovable extends Entity{
	public String getClassID(){return "movable_entity";}
	private int moveSpeed=6;
	private boolean moving=false;
	private int oldX;//used for interpolation of entity position
	private int oldY;
	
	/**
	 * moves the entity to the position x,y checks for collisions,
	 * and calls the onCollide if moving to position x,y will
	 * cause a collision with another entity
	 * @param x 
	 * @param y
	 * @return returns false if unable to move to the position x,y
	 */
	public boolean moveTo(int x,int y){
		boolean domove=false;
		
		//check if the map is passable in the location we want to move to
		if(Game.getInstance().getMap().isPassable(x,y)){
			Entity collidedent=Game.getInstance().getEntities().getEntInPos(x,y);
			if(collidedent!=null){
				//if there is an entity in the position, call its oncollide method
				collidedent.onCollide(this);
				if(collidedent.isPassable()){
					//we can move into that position if the entity that we're colliding
					//with is passable
					domove=true;
				}
			}else{
				//we can move into that position since there is no entity in it
				domove=true;
			}
		}
		
		if(domove){
			//set the oldx and oldy for movement interpolation
			oldX=getX();
			oldY=getY();
			setPos(x,y);
			moving=true;
			//set the next think for the entity based on its movement speed
			setNextThink(Game.getInstance().getTime()+Game.getInstance().getTimerSpeed()*getMoveSpeed());
			return true;
		}else{
			//return false if we weren't allowed to move
			return false;
		}
	};

	public EntityMovable(int xcoord, int ycoord) {
		super(xcoord, ycoord);
	}
	
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
	 * get the interpolation ratio between 0 and 1 for movement interpolation
	 * @return
	 */
	private double getMovementInterp(){
		return (double)(Game.getInstance().getTime()-getNextThink())/(double)(Game.getInstance().getTimerSpeed()*getMoveSpeed());
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
	
	/**
	 * Move the entity up by 1 on the map
	 * @return returns false if unable to move that direction
	 */
	public boolean moveUp(){
		return moveTo(this.getX(),this.getY()-1);
	};
	
	/**
	 * Move the entity down by 1 on the map
	 * @return returns false if unable to move that direction
	 */
	public boolean moveDown(){
		return moveTo(this.getX(),this.getY()+1);
	};
	
	/**
	 * Move the entity left by 1 on the map
	 * @return returns false if unable to move that direction
	 */
	public boolean moveLeft(){
		return moveTo(this.getX()-1,this.getY());
	};
	
	/**
	 * Move the entity right by 1 on the map
	 * @return returns false if unable to move that direction
	 */
	public boolean moveRight(){
		return moveTo(this.getX()+1,this.getY());
	};
	
};