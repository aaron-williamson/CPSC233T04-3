import java.util.List;

import java.util.ArrayList;
//this is mostly entity utility functions
public class Entities{
	private List<Entity> entitylist=new ArrayList<Entity>();
	
	/**
	 * returns an array of all entities in the ent manager.
	 * @return Entity[] containing all ents
	 */
	public Entity[] getAll(){
		Entity[] entsarray=new Entity[1];
		return entitylist.toArray(entsarray);
	}
	
	/**
	 * Returns the number of entities of a given classid
	 * @param classid classid you want to count
	 * @return number of ents
	 */
	public int countByClass(String classid){
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
	public Entity[] getByClass(String classid){
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
	public void addEnt(Entity ent){
		entitylist.add(ent); 
	}
	
	/**
	 * Returns true if there is an entity in the position, false otherwise
	 * @param x x pos
	 * @param y y pos
	 * @return boolean
	 */
	public boolean isEntInPos(int x,int y){
		Entity[] entsarray=getAll();
		for(int i=0;i<entsarray.length;i++){
			if(entsarray[i].getX()==x && entsarray[i].getY()==y){
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
	public Entity getEntInPos(int x,int y){
		Entity[] entsarray=getAll();
		for(int i=0;i<entsarray.length;i++){
			if(entsarray[i].getX()==x && entsarray[i].getY()==y){
				return entsarray[i];
			}
		}
		return null;
	}
	
	/**
	 * Calls Entity.think() for all entities in the entity manager.
	 */
	public void allThink(){
		Entity[] entsarray=getAll();
		for(int i=0;i<entsarray.length;i++){
			entsarray[i].think(0);
		}
	}
};