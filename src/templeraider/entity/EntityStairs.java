package templeraider.entity;
import java.awt.*;

import templeraider.Game;

/**
 * The stairs entity for Temple Raider. Will bring you to the EntityPlayerSpawn on 
 * the next map.
 */
public class EntityStairs extends Entity{
	/**
	 * Function for getting the class id used to identify an entity
	 * 
	 * @return the class id for stairs
	 */
	public String getClassID(){return "stairs";}

	//this is the image file used for stairs
	private Image  image=Toolkit.getDefaultToolkit().getImage("templeraider/img/invisible.png");
	
	/**
	 * The stairs image - will return the image which will be used in the gui
	 *
	 * @return the image for stairs
	 */
	public Image getImage(){
		return image;
	}
	
	/**
	 * The stairs physical form - will restrict entities from walking
	 * though the stairs
	 *
	 * @return the boolean false so it is not passable by entities
	 */
	public boolean isPassable(){
		return false;
	}
	
	/**
	 * The function that get's called when there is a collision with the stairs
	 *
	 * @param ent the entity which has collided with the stairs
	 */
	public void onCollide(Entity ent){
		//will change to the next map
		Game.getInstance().nextMap();
	};
	
	/**
	 * Constructor for EntityStairs, creates stairs at the given coordinates
	 * 
	 * @param xcoord desired x-coordinate for stairs
	 * @param ycoord desired y-coordinate for stairs
	 */
	public EntityStairs(int xcoord, int ycoord) {
		super(xcoord, ycoord);
	}
};