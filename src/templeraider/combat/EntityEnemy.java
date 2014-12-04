package templeraider.combat;
import java.util.Random;

import templeraider.Game;
import templeraider.entity.Entity;

/**
 * The Enemy for Temple Raider. Hold's the movement AI as well as the enemies position and
 * what happens uppon collision with this entity
 */
public class EntityEnemy extends EntityCombat{
	/**
	 * Function for getting the class id used to identify an entity
	 * 
	 * @return the class id for enemy
	 */
	public String getClassID(){return "enemy";}
	
	// The distance from which the enemy will begin to attack the player
	protected int aggroDistance = 5;
	
	/**
	 * Constructor for EntityEnemy, creates an enemy at the given coordinates
	 * 
	 * @param xcoord desired x-coordinate for enemy
	 * @param ycoord desired y-coordinate for enemy
	 */
	public EntityEnemy(int xcoord, int ycoord){
		super(xcoord, ycoord);
		setMoveSpeed(16);
	}
	
	/**
	 * The function that get's called when there is a collision with this enemy
	 * @param ent the entity which has collided with this enemy
	 */
	public void onCollide(Entity ent){
		// If the enemy has collided with a player, begin combat
		if(ent.getClassID().equals("player")){
			Game.getInstance().getCombat().startCombat((EntityPlayer)ent, (EntityCombat)this, true);
		}
	};
	
	/**
	 * The think function, used to handle the turns as well as movement and AI
	 * 
	 * @param time the current game time
	 */
	public void think(long time){
		// Call think for the parent class
		super.think(time);
		
		// Get all of the players
		Entity[] playersarray=Game.getInstance().getEntities().getByClass("player");
		Entity target=null;
		
		// Set the appropriate chase distance
		double chaseDist=aggroDistance;
		
		// Chase the closest player
		for(int i=0;i<playersarray.length;i++){
			double dist=this.distance(playersarray[i]);
			if(dist<chaseDist){
				target=playersarray[i];
				chaseDist=dist;
			}
		}

		// If there is no target within range, do nothing
		if (target == null)
			return;
		
		// Get the distance between the player and enemy
		int difx=target.getX()-this.getX();
		int dify=target.getY()-this.getY();
		
		// If the enemy needs to move up or down to get to the player, choose one direction randomly
		if(difx!=0 && dify!=0){
			boolean rand=new Random().nextBoolean();
			if(rand){
				difx=0;
			}else{
				dify=0;
			}
		}
		
		// Move according to previous calculations
		if(difx>0){
			this.moveRight();
		}else if(difx<0){
			this.moveLeft();
		}else if(dify>0){
			this.moveDown();
		}else if(dify<0){
			this.moveUp();
		}
	}
};