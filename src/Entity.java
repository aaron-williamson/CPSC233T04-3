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

	private int entEND = 5;
	private int entSTR = 5;
	private int entLCK = 5;
	private int entHP = entEND * 10;
	private int entHIT = (80 + (entLCK / 2)) - 1;
	private int entCRIT = entLCK - 1;
	
	/**
	 * Get the distance between this entity and another
	 * @param ent the other entity
	 * @return distance between ents
	 */
	public double distance(Entity ent){
		return Math.sqrt(Math.pow(xpos-ent.getX(),2)+Math.pow(ypos-ent.getY(),2));
	};

	// Combat stuff from here onward


	/**
	 * Getter Function for entEND (Endurance stat)
	 * 
	 * @return entEND the player's endurance stat
	 */
	public int getEntEND() {
		return entEND;
	}

	/**
	 * Getter Function for entSTR (Strength stat)
	 * 
	 * @return entSTR the player's strength stat
	 */
	public int getEntSTR() {
		return entSTR;
	}

	/**
	 * Getter Function for entLCK (Luck stat)
	 * 
	 * @return entLCK the player's luck stat
	 */
	public int getEntLCK() {
		return entLCK;
	}

	/**
	 * Getter Function for entHP (Health stat)
	 * 
	 * @return entHP the player's health stat
	 */
	public int getEntHP() {
		return entHP;
	}

	/**
	 * Getter Function for entHIT (Hit chance stat)
	 * 
	 * @return entHIT the player's hit chance stat
	 */
	public int getEntHIT() {
		return entHIT;
	}

	/**
	 * Getter Function for entCRIT (Critical hit chance stat)
	 * 
	 * @return entCRIT the player's critical hit chance stat
	 */
	public int getEntCRIT() {
		return entCRIT;
	}

	/**
	 * Setter funciton for entHP (Health stat)
	 *
	 * @param health the value health should be set to
	 */
	public void setEntHP(int health) {
		entHP = health;
	}
	
};