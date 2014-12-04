package templeraider.entity;
import java.awt.*;

import templeraider.Game;
import templeraider.combat.EntityPlayer;

/**
 * The Health Fountain entity for Temple Raider. 
 * Will heal the player to full health after touching it (The collision).
 */
public class EntityHealthFountain extends Entity{
	//Limits the user to 3 uses, will decrease each time it is used
	private int fountainUses = 3;
	
	/**
	 * Function for getting the class id used to identify a Health Fountain
	 * 
	 * @return the class id for HealthFountain
	 */
	public String getClassID(){return "fountain";}
	
	/**
	 * The HealthFountain's physical form - will restrict entities from walking
	 * though the fountain
	 *
	 * @return the boolean false so it is not passable by entities
	 */
	public boolean isPassable(){return false;}
	
	//This is the image file used for the HealthFountain
	private Image  image=Toolkit.getDefaultToolkit().getImage("templeraider/img/fountain.png");
	
	/**
	 * The HealthFountain's image - will return the image which will be
	 * used in the gui
	 *
	 * @return the image for the fountain
	 */
	public Image getImage(){
		return image;
	}
	
	/**
	 * The function that will be called when there is a collision with the fountain
	 * Will heal the player to full health
	 *
	 * @param ent the entity which has collided with this fountain
	 */
	public void onCollide(Entity ent){
		//Checks if it was the player that touched the fountain and that there
		//are uses left
		if (ent.getClassID() == "player" && fountainUses > 0) {
			//Will heal the player to full health and will subtract 1 from the uses
			//Will print out a message in the message panel
			((EntityPlayer)ent).healPlayer();
			ent.setNextThink(Game.getInstance().getTime()+1599);
			--fountainUses;
			Game.getInstance().getGUI().printLine("You take a drink from the health fountain, you have been healed to full health!");
			Game.getInstance().getGUI().printLine(fountainUses + " health fountain uses remaining!");
			if (fountainUses == 0) {
				//Sets a different image to show the fountain is out of uses
				image = Toolkit.getDefaultToolkit().getImage("templeraider/img/depletedfountain.png");
			}
		}
		//will display a message if the player touched the fountain and there are no more uses
		else if (fountainUses == 0) {
			Game.getInstance().getGUI().printLine("This health fountain is depleted! No uses left!");
		}
	};
	
	/**
	 * Constructor for EntityHealthFountain, creates a health fountain at
	 * the given coordinates
	 * 
	 * @param xcoord desired x-coordinate for the fountain
	 * @param ycoord desired y-coordinate for the fountain
	 */
	public EntityHealthFountain(int xcoord, int ycoord) {
		super(xcoord, ycoord);
	}
};