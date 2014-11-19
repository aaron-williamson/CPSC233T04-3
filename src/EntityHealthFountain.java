import java.awt.*;

public class EntityHealthFountain extends Entity{
	public String getClassID(){return "stairs";}
	
	private Image  image=Toolkit.getDefaultToolkit().getImage("../img/entity.png");
	//private Image  image=Toolkit.getDefaultToolkit().getImage("../img/invisible.png");
	
	public Image getImage(){
		return image;
	}
	
	public void onCollide(Entity ent){
		if (ent.getClassID() == "player") {
			//ent.healPlayer();
		}
	};
	
	
	public EntityHealthFountain(int xcoord, int ycoord) {
		super(xcoord, ycoord);
	}
};