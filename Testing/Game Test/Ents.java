import java.util.List;

import java.util.ArrayList;
//this is mostly entity utility functions
public class Ents{
	public static List<Entity> entitylist=new ArrayList<Entity>();
	
	//get an array of all entities
	public static Entity[] getAll(){
		Entity[] entsarray=new Entity[1];
		return entitylist.toArray(entsarray);
	}
	
	//this function returns the amount of entities with a given classid
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
	
	//this function returns all entities of a specified class id
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
	
	//add an entity to the entity array
	public static void addEnt(Entity ent){
		entitylist.add(ent); 
	}
	
	//returns true if there is any entity in pos (x,y)
	public static boolean isEntInPos(int x,int y){
		Entity[] entsarray=Ents.getAll();
		for(int i=0;i<entsarray.length;i++){
			if(entsarray[i].xpos==x && entsarray[i].ypos==y){
				return true;
			}
		}
		return false;
	}
	
	//get Entity in pos (x,y)
	//can use getEntInPos(x,y)!=null instead of isEntInPos(x,y)
	public static Entity getEntInPos(int x,int y){
		Entity[] entsarray=Ents.getAll();
		for(int i=0;i<entsarray.length;i++){
			if(entsarray[i].xpos==x && entsarray[i].ypos==y){
				return entsarray[i];
			}
		}
		return null;
	}
	
	//calls think() on all entities
	public static void allThink(){
		Entity[] entsarray=Ents.getAll();
		for(int i=0;i<entsarray.length;i++){
			entsarray[i].think();
		}
	}
};