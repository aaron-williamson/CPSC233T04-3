import java.awt.*;

public abstract class Entity{
	private int xpos;
	private int ypos;
	private Image defaultImage;
	
	Entity(){
		//add this entity to the game's entity manager
		Game.getGame().getEntities().addEnt(this);
		defaultImage=Toolkit.getDefaultToolkit().getImage("../img/entity.png");
	}
	
	/**
	 * Gets a unique id for this entity type.
	 * @return string of the id
	 */
	public abstract String getClassID();
	
	public Image getImage(long timestamp){
		return defaultImage;
	}

	public String debuggraphic(){return "E";} //character to draw for the debug graphics
	
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
		xpos=x;
		ypos=y;
	};
	
	/**
	 * Get the entity x pos
	 * @return xpos
	 */
	public int getX(){
		return xpos;
	}
	
	/**
	 * Get the entity y pos
	 * @return ypos
	 */
	public int getY(){
		return ypos;
	}

	/**
	 * Called each update of the game loop
	 */
	public void think(long time){
		
	};
	
	/**
	 * Called when another entity collides with this entity.
	 * @param Entity that collided
	 */
	public void onCollide(Entity ent){
		
	};

	
	/**
	 * Get the distance between this entity and another
	 * @param ent the other entity
	 * @return distance between ents
	 */
	public double distance(Entity ent){
		return Math.sqrt(Math.pow(xpos-ent.getX(),2)+Math.pow(ypos-ent.getY(),2));
	};
	
};