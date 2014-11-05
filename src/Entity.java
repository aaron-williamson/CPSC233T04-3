public class Entity{
	public String getClassID(){return "base_entity";} //a unique identifier for this entity type

	public String getName(){return "name";}

	public String debuggraphic(){return "E";} //character to draw for the debug graphics
	public boolean isPassable(){return true;} //can other ents walk ontop of this ent

	private int xpos;
	private int ypos;
	boolean passable=true;
	
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
	
	public void think(){
		
	};
	
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