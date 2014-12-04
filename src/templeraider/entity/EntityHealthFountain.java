package templeraider.entity;
import java.awt.*;

import templeraider.Game;
import templeraider.combat.EntityPlayer;

public class EntityHealthFountain extends Entity{
	private int fountainUses = 3;
	public String getClassID(){return "fountain";}
	public boolean isPassable(){return false;}
	
	private Image  image=Toolkit.getDefaultToolkit().getImage("templeraider/img/fountain.png");
	
	public Image getImage(){
		return image;
	}
	
	public void onCollide(Entity ent){
		if (ent.getClassID() == "player" && fountainUses > 0) {
			((EntityPlayer)ent).healPlayer();
			ent.setNextThink(Game.getInstance().getTime()+1599);
			--fountainUses;
			Game.getInstance().getGUI().printLine("You take a drink from the health fountain, you have been healed to full health!");
			Game.getInstance().getGUI().printLine(fountainUses + " health fountain uses remaining!");
			if (fountainUses == 0) {
				image = Toolkit.getDefaultToolkit().getImage("templeraider/img/depletedfountain.png");
			}
		}
		else if (fountainUses == 0) {
			Game.getInstance().getGUI().printLine("This health fountain is depleted! No uses left!");
		}
	};
	
	
	public EntityHealthFountain(int xcoord, int ycoord) {
		super(xcoord, ycoord);
	}
};