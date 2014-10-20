import java.util.List;

import java.util.ArrayList;
//this is mostly entity utility functions
public class Ents{
	private static List<Entity> entitylist=new ArrayList<Entity>();
	
	/**
	 * returns an array of all entities in the ent manager.
	 * @return Entity[] containing all ents
	 */
	public static Entity[] getAll(){
		Entity[] entsarray=new Entity[1];
		return entitylist.toArray(entsarray);
	}
	
	/**
	 * Returns the number of entities of a given classid
	 * @param classid classid you want to count
	 * @return number of ents
	 */
	public static int countByClass(String classid){
		int n=0;
		int size=entitylist.size();
		
		for(int i=0;i<size;i++){
			if(classid.equalsIgnoreCase(entitylist.get(i).getClassID())){
				n++;
			}
		}
		return n;
	}
	
	/**
	 * Returns all of the entities of a given classid
	 * @param classid classid of entities you want
	 * @return Entity[] containing entities of class classid
	 */
	public static Entity[] getByClass(String classid){
		Entity[] entsarray=new Entity[countByClass(classid)];
		int size=entitylist.size();
		for(int i=0;i<size;i++){
			Entity ent=entitylist.get(i);
			if(classid.equalsIgnoreCase(ent.getClassID())){
				entsarray[i]=ent;
			}
		}
		
		return entsarray;
	}
	
	/**
	 * Add and entity to the entity manager, do this for any entity you make.
	 * @param ent entity to add
	 */
	public static void addEnt(Entity ent){
		entitylist.add(ent); 
	}
	
	/**
	 * Returns true if there is an entity in the position, false otherwise
	 * @param x x pos
	 * @param y y pos
	 * @return boolean
	 */
	public static boolean isEntInPos(int x,int y){
		Entity[] entsarray=Ents.getAll();
		for(int i=0;i<entsarray.length;i++){
			if(entsarray[i].xpos==x && entsarray[i].ypos==y){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gets the entity in pos x,y. If no entity in pos, returns null
	 * @param x x pos
	 * @param y y pos
	 * @return Entity or null
	 */
	public static Entity getEntInPos(int x,int y){
		Entity[] entsarray=Ents.getAll();
		for(int i=0;i<entsarray.length;i++){
			if(entsarray[i].xpos==x && entsarray[i].ypos==y){
				return entsarray[i];
			}
		}
		return null;
	}
	
	/**
	 * Calls Entity.think() for all entities in the entity manager.
	 */
	public static void allThink(){
		Entity[] entsarray=Ents.getAll();
		for(int i=0;i<entsarray.length;i++){
			entsarray[i].think();
		}
	}
};