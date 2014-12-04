package templeraider.entity;
import java.awt.*;

/**
 * The PlayerSpawn entity for Temple Raider.  Will spawn a player on this area in
 * the map.
 */
public class EntityPlayerSpawn extends Entity{
	/**
	 * Function for getting the class id used to identify an entity
	 * 
	 * @return the class id for PlayerSpawn
	 */
	public String getClassID(){return "playerspawn";}
	
	//this is the image file for the PlayerSpawn
	private Image  image=Toolkit.getDefaultToolkit().getImage("templeraider/img/invisible.png");
	
	/**
	 * The PlayerSpawn image - will return the image which will be used in the gui
	 *
	 * @return the image for the PlayerSpawn
	 */
	public Image getImage(){
		return image;
	}
	
	/**
	 * Constructor for EntityPlayerSpawn, creates a spawn location on the map
	 * at the given coordinates
	 * 
	 * @param xcoord desired x-coordinate for the spawn point
	 * @param ycoord desired y-coordinate for the spawn point
	 */
	public EntityPlayerSpawn(int xcoord, int ycoord) {
		super(xcoord, ycoord);
	}
};