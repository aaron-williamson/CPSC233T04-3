public class Entity{
	String getClassID(){return "base_entity";} //a unique identifier for this entity type

	String debuggraphic(){return "E";} //character to draw for the debug graphics
	boolean isPassable(){return true;} //can other ents walk ontop of this ent

	int xpos;
	int ypos;
	boolean passable=true;
	
	void setPos(int x,int y){
		xpos=x;
		ypos=y;
	};
	
	void think(){
		
	};
	
	void onCollide(Entity ent){
		
	};
	
	//get the distance between this ent and another ent
	double distance(Entity ent){
		return Math.sqrt(Math.pow(xpos-ent.xpos,2)+Math.pow(ypos-ent.ypos,2));
	};
	
};