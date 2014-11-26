package templeraider.entity;
import java.awt.*;

import templeraider.Game;

public abstract class Entity{
	private int xPos;
	private int yPos;
	protected Image defaultImage;
	private long nextThink=-1;
	
	Entity(){
		//add this entity to the game's entity manager
		Game.getInstance().getEntities().addEntity(this);
		defaultImage=Toolkit.getDefaultToolkit().getImage("templeraider/img/entity.png");
	}

	Entity(int xcoord, int ycoord){
		this();
		setPos(xcoord, ycoord);
	}
	
	/**
	 * Gets a unique id for this entity type.
	 * @return string of the id
	 */
	public abstract String getClassID();
	
	/**
	 * get the image to display for this entity
	 * @return Image
	 */
	public Image getImage(){
		return defaultImage;
	}
	
	/**
	 * Returns true if other entities can walk overtop of this one.
	 * @return
	 */
	public boolean isPassable(){return true;}
	
	
	/** 
	 * Set the entity position
	 * @param x
	 * @param y
	 */
	public void setPos(int x,int y){
		xPos=x;
		yPos=y;
	};
	
	/**
	 * Get the entity x pos
	 * @return xpos
	 */
	public int getX(){
		return xPos;
	}
	
	/**
	 * Get the entity y pos
	 * @return ypos
	 */
	public int getY(){
		return yPos;
	}

	/**
	 * Called each update of the game loop
	 */
	public void think(long time){
		
	};
	
	/**
	 * set the next time that this entity should think
	 * @param long nextTime
	 */
	public void setNextThink(long nextTime){
		nextThink=nextTime;
	}
	
	/**
	 * get the next time that this entity should think
	 * @return long
	 */
	public long getNextThink(){
		return nextThink;
	}
	
	public void remove(){
		setNextThink(Long.MAX_VALUE);
		setPos(-999,-999);
		defaultImage=null;
		Game.getInstance().getEntities().removeEntity(this);
	}
	
	
	/**
	 * Called when another entity collides with this entity.
	 * @param Entity that collided
	 */
	public void onCollide(Entity ent){
		
	};
	
	/**
	 * get weather the entity can move
	 * @return
	 */
	public boolean isMovable(){
		return false;
	}
	
	
	/**
	 * Get the distance between this entity and another
	 * @param ent the other entity
	 * @return distance between ents
	 */
	public double distance(Entity ent){
		return Math.sqrt(Math.pow(getX()-ent.getX(),2)+Math.pow(getY()-ent.getY(),2));
	};
	
};