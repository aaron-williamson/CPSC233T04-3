package templeraider.entity;
import java.util.List;
import java.util.ArrayList;

import templeraider.Game;

public class Entities{
	private List<Entity> entityList=new ArrayList<Entity>();
	
	/**
	 * returns an array of all entities in the ent manager.
	 * @return Entity[] containing all ents
	 */
	public Entity[] getAll(){
		Entity[] entityArray=new Entity[0];
		return entityList.toArray(entityArray);
	}
	
	/**
	 * Returns the number of entities of a given classid
	 * @param classid classid you want to count
	 * @return number of ents
	 */
	public int countByClass(String classid){
		int n=0;
		int size=entityList.size();
		
		//increment n if the entity class matches the supplied classid
		for(int i=0;i<size;i++){
			if(classid.equalsIgnoreCase(entityList.get(i).getClassID())){
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
		//size our output array to fit
		Entity[] entsarray=new Entity[countByClass(classid)];
		
		int size=entityList.size();
		
		//add entity to the output array if it matches the supplied classid
		for(int i=0;i<size;i++){
			Entity ent=entityList.get(i);
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
	public void addEntity(Entity ent){
		entityList.add(ent); 
	}
	
	/**
	 * Remove an entity from the entity manager
	 * @param ent
	 */
	public void removeEntity(Entity ent){
		for(int i=0;i<entityList.size();i++){
			if(ent==entityList.get(i)){
				entityList.remove(i);
				break;
			}
		}
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
				//no reason to continue iterating if we've found an entity,
				//using a return will be slightly faster than a break and a boolean var.
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
				//no reason to continue iterating if we've found an entity
				return entsarray[i];
			}
		}
		
		//return null since there is no entity in the supplied position
		return null;
	}
	
	/**
	 * Removes every entity
	 */
	public void removeAll(){
		Entity[] entityArray=getAll();
		
		for(int i=0;i<entityArray.length;i++){
			entityArray[i].remove();
			entityArray[i]=null;
		}
	}
	
	/**
	 * Removes every entity of the specified classid
	 * @param classid
	 */
	public void removeAll(String classid){
		Entity[] entityArray=getAll();
		
		for(int i=0;i<entityArray.length;i++){
			//check if the entity has the same classid as specified in argument
			if(!entityArray[i].getClassID().equals(classid)){
				entityArray[i].remove();
				entityArray[i]=null;
			}
		}
	}
	
	/**
	 * Calls Entity.think() for all entities in the entity manager.
	 */
	public void allThink(){
		Entity[] entityArray=getAll();
		long time=Game.getInstance().getTime();
		for(int i=0;i<entityArray.length;i++){
			//only call the think method if we're at the entity's next think time
			if(entityArray[i].getNextThink()<=time){
				entityArray[i].think(time);
			}
		}
	}
};