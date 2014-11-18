import java.awt.Toolkit;
import java.util.Random;
import java.awt.*;

public class EntityPlayer extends EntityCombat{
	private String playerName="Player";
	public String getClassID(){return "player";}
	private int playerXP=0;
	
	private int animationDirection=1;
	private Image[][] animImage={{Toolkit.getDefaultToolkit().getImage("../img/player/north.png"),
								Toolkit.getDefaultToolkit().getImage("../img/player/north_m1.png"),
								Toolkit.getDefaultToolkit().getImage("../img/player/north_m2.png")},
								{Toolkit.getDefaultToolkit().getImage("../img/player/south.png"),
								Toolkit.getDefaultToolkit().getImage("../img/player/south_m1.png"),
								Toolkit.getDefaultToolkit().getImage("../img/player/south_m2.png")},
								{Toolkit.getDefaultToolkit().getImage("../img/player/east.png"),
								Toolkit.getDefaultToolkit().getImage("../img/player/east_m1.png"),
								Toolkit.getDefaultToolkit().getImage("../img/player/east_m2.png")},
								{Toolkit.getDefaultToolkit().getImage("../img/player/west.png"),
								Toolkit.getDefaultToolkit().getImage("../img/player/west_m1.png"),
								Toolkit.getDefaultToolkit().getImage("../img/player/west_m2.png")}};
	
	public EntityPlayer(int xcoord, int ycoord){
		super(xcoord, ycoord);
		combatEndurance=8;
		combatStrength=8;
		combatLuck=8;
		combatXP=8;
		
		combatAttackMessage="attacks with her sword!";
		combatMissMessage="It missed!";
		combatCriticalMessage="It was a critical hit!";
		combatDefendMessage="raises her shield.";
		
		setHealth(getMaxHealth());
	}
	
	public Image getImage(){
		Image displayImage=animImage[1][0];
		if(isMoving()){
			int offset=0;
			if(Game.getGame().getTime()%200<100){
				offset=1;
			}
			displayImage=animImage[animationDirection][1+offset];
		}else{
			displayImage=animImage[animationDirection][0];
		}
		
		return displayImage;
	}
	
	public void onCollide(Entity ent){
		if(ent.getClassID().equals("enemy")){
			Game.getGame().getCombat().startCombat(this, (EntityCombat)ent, false);
		}
	};
	
	public void giveXP(int XP){
		playerXP+=XP;
	}
	
	public String getPlayerName(){
		return playerName;
	}
	
	public String getCombatName(){
		return getPlayerName();
	}
	
	public void think(long time){
		super.think(time);
		
		//I can't use a switch here, huah huah
		if(Game.getGame().getGUI().isKeyDown(38) || Game.getGame().getGUI().isKeyDown(87)){
			moveUp();
			animationDirection=0;
		}else if(Game.getGame().getGUI().isKeyDown(39) || Game.getGame().getGUI().isKeyDown(65)){
			moveRight();
			animationDirection=2;
		}else if(Game.getGame().getGUI().isKeyDown(37) || Game.getGame().getGUI().isKeyDown(68)){
			moveLeft();
			animationDirection=3;
		}else if(Game.getGame().getGUI().isKeyDown(40) || Game.getGame().getGUI().isKeyDown(83)){
			moveDown();
			animationDirection=1;
		}
	}
};