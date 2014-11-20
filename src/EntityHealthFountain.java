import java.awt.*;

public class EntityHealthFountain extends Entity{
	private int fountainUses = 3;
	public String getClassID(){return "fountain";}
	public boolean isPassable(){return false;}
	
	private Image  image=Toolkit.getDefaultToolkit().getImage("../img/fountain.png");
	
	public Image getImage(){
		return image;
	}
	
	public void onCollide(Entity ent){
		if (ent.getClassID() == "player" && fountainUses > 0) {
			((EntityPlayer)ent).healPlayer();
			ent.setNextThink(Game.getGame().getTime()+1599);
			--fountainUses;
			Game.getGame().getGUI().printLine("You take a drink from the health fountain, you have been healed to full health!");
			Game.getGame().getGUI().printLine(fountainUses + " health fountain uses remaining!");
			if (fountainUses == 0) {
				image = Toolkit.getDefaultToolkit().getImage("../img/depletedfountain.png");
			}
		}
		else if (fountainUses == 0) {
			Game.getGame().getGUI().printLine("This health fountain is depleted! No uses left!");
		}
	};
	
	
	public EntityHealthFountain(int xcoord, int ycoord) {
		super(xcoord, ycoord);
	}
};