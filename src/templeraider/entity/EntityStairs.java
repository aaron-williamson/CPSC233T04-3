package templeraider.entity;
import java.awt.*;

import templeraider.Game;

public class EntityStairs extends Entity{
	public String getClassID(){return "stairs";}

	private Image  image=Toolkit.getDefaultToolkit().getImage("templeraider/img/invisible.png");
	
	public Image getImage(){
		return image;
	}
	
	public boolean isPassable(){
		return false;
	}
	
	public void onCollide(Entity ent){
		Game.getInstance().nextMap();
	};
	
	
	public EntityStairs(int xcoord, int ycoord) {
		super(xcoord, ycoord);
	}
};