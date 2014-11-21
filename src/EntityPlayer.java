import java.awt.Toolkit;
import java.awt.*;
import javax.swing.JOptionPane;

public class EntityPlayer extends EntityCombat{
	private String playerName="Player";
	public String getClassID(){return "player";}
	private int playerXP=0;
	private int level = 1;
	private int expToLevel = 5;
	
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
		
		setMoveSpeed(6);
		
		combatEndurance=10;
		combatStrength=15;
		combatLuck=10;
		combatXP=0;
		
		combatAttackMessage="attacks with their sword!";
		combatMissMessage="It missed!";
		combatCriticalMessage="It was a critical hit!";
		combatDefendMessage="raises their shield.";
		
		setHealth(getMaxHealth());
		
		playerName=JOptionPane.showInputDialog(null,"What is your name?",Game.GAME_TITLE,JOptionPane.QUESTION_MESSAGE);
	}
	
	public Image getImage(){
		Image displayImage=animImage[1][0];
		if(isMoving()){
			int offset=0;
			if(Game.getInstance().getTime()%200<100){
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
			Game.getInstance().getCombat().startCombat(this, (EntityCombat)ent, false);
		}
	};
	
	public void giveXP(int XP){
		playerXP+=XP;
		if (playerXP >= expToLevel) {
			levelUp();
			expToLevel = expToLevel * 2;
		}
	}
	
	public String getPlayerName(){
		return playerName;
	}

	public void levelUp() {
		Game.getInstance().getGUI().printLine("LEVEL UP!");
		++level;
		combatEndurance += level;
		Game.getInstance().getGUI().printLine("Endurance + " + level + "    Endurance now: " + combatEndurance);
		setHealth(getMaxHealth());
		Game.getInstance().getGUI().printLine("You have been healed to max health!" + "    Max health now: " + getMaxHealth());
		combatStrength += 2 * level;
		Game.getInstance().getGUI().printLine("Strength + " + (2 * level) + "    Strength now: " + combatStrength);
		combatLuck += level;
		Game.getInstance().getGUI().printLine("Luck + " + level + "    Luck now: " + combatLuck);
		setMoveSpeed(6 - (level / 5));
	}

	public void healPlayer() {
		setHealth(getMaxHealth());
	}
	
	public String getCombatName(){
		return getPlayerName();
	}
	
	public void think(long time){
		super.think(time);
		
		//I can't use a switch here, huah huah
		if(Game.getInstance().getGUI().isKeyDown(38) || Game.getInstance().getGUI().isKeyDown(87)){
			moveUp();
			animationDirection=0;
		}else if(Game.getInstance().getGUI().isKeyDown(39) || Game.getInstance().getGUI().isKeyDown(68)){
			moveRight();
			animationDirection=2;
		}else if(Game.getInstance().getGUI().isKeyDown(37) || Game.getInstance().getGUI().isKeyDown(65)){
			moveLeft();
			animationDirection=3;
		}else if(Game.getInstance().getGUI().isKeyDown(40) || Game.getInstance().getGUI().isKeyDown(83)){
			moveDown();
			animationDirection=1;
		}
		
	}
};