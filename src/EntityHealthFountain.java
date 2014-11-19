import java.awt.*;

public class EntityHealthFountain extends Entity{
	private int fountainUses = 3;
	public String getClassID(){return "fountain";}
	
	private Image  image=Toolkit.getDefaultToolkit().getImage("../img/entity.png");
	//private Image  image=Toolkit.getDefaultToolkit().getImage("../img/invisible.png");
	
	public Image getImage(){
		return image;
	}
	
	public void onCollide(Entity ent){
		if (ent.getClassID() == "player" && fountainUses > 0) {
			((EntityPlayer)ent).healPlayer();
			--fountainUses;
			Game.getGame().getGUI().printLine(fountainUses + " health fountain uses remaining!");
		}
		else if (fountainUses == 0) {
			Game.getGame().getGUI().printLine("This health fountain is depleted! No uses left!");
		}
	};
	
	
	public EntityHealthFountain(int xcoord, int ycoord) {
		super(xcoord, ycoord);
	}
};