import java.awt.*;

public class EntityStairs extends Entity{
	public String getClassID(){return "stairs";}
	
	private Image  image=Toolkit.getDefaultToolkit().getImage("../img/entity.png");
	//private Image  image=Toolkit.getDefaultToolkit().getImage("../img/invisible.png");
	
	public Image getImage(){
		return image;
	}
	
	public boolean isPassable(){
		return false;
	}
	
	public void onCollide(Entity ent){
		Game.getGame().nextMap();
	};
	
	
	public EntityStairs(int xcoord, int ycoord) {
		super(xcoord, ycoord);
	}
};