package templeraider.entity;
import java.awt.*;

import templeraider.Game;

/**
 * The Entity class for Temple Raider. 
 * base code for all entities 
 */
public abstract class Entity{
	//x and y positions for a entity
	private int xPos;
	private int yPos;
	//image for enitiy
	protected Image defaultImage;
	//Next think
	private long nextThink=-1;
	
	/**
	 * Default constructor that just adds a entity
	 */
	Entity(){
		//add this entity to the game's entity manager
		Game.getInstance().getEntities().addEntity(this);
		//sets a default entity image
		defaultImage=Toolkit.getDefaultToolkit().getImage("templeraider/img/entity.png");
	}

	/**
	 * Constructor that sets a entity at a given location
	 * @param x-coordinate on the map
	 * @param y-coordinate on the map
	 */
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
	
	/**
	 * Removes an entity from the map
	 * moves them to a position that is way off the map ie. they will never be seen again
	 */
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