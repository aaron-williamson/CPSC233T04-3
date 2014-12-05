package templeraider.combat;
import java.awt.*;

import javax.swing.JOptionPane;

import templeraider.Game;
import templeraider.entity.Entity;

public class EntityPlayer extends EntityCombat{
	private String playerName="Player";
	public String getClassID(){return "player";}
	private int playerXP=0;
	private int level = 1;
	private int expToLevel = 5;
	
	//player image variables/array
	private int animationDirection=1;
	private Image[][] animImage={{Toolkit.getDefaultToolkit().getImage("templeraider/img/player/north.png"),
								Toolkit.getDefaultToolkit().getImage("templeraider/img/player/north_m1.png"),
								Toolkit.getDefaultToolkit().getImage("templeraider/img/player/north_m2.png")},
								{Toolkit.getDefaultToolkit().getImage("templeraider/img/player/south.png"),
								Toolkit.getDefaultToolkit().getImage("templeraider/i"
										+ "mg/player/south_m1.png"),
								Toolkit.getDefaultToolkit().getImage("templeraider/img/player/south_m2.png")},
								{Toolkit.getDefaultToolkit().getImage("templeraider/img/player/east.png"),
								Toolkit.getDefaultToolkit().getImage("templeraider/img/player/east_m1.png"),
								Toolkit.getDefaultToolkit().getImage("templeraider/img/player/east_m2.png")},
								{Toolkit.getDefaultToolkit().getImage("templeraider/img/player/west.png"),
								Toolkit.getDefaultToolkit().getImage("templeraider/img/player/west_m1.png"),
								Toolkit.getDefaultToolkit().getImage("templeraider/img/player/west_m2.png")}};
	
	//set all player combat variables 
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
	
	/**
	*Get player's image
	*@return Corresponding players image 
	*/
	public Image getImage(){
	   //image in array matched with player movement
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
	/**
	*Checks if Entities collide in game, this will start combat mode.
	*@param Enity object
	*/
	public void onCollide(Entity ent){
		if(ent.getClassID().equals("enemy")){//if player collides with enemy
			Game.getInstance().getCombat().startCombat(this, (EntityCombat)ent, false);
		}
	};
	
	/**
	*increases int value that controls players strength level
	*@param Strength to be added to player
	*/
	public void giveXP(int XP){
	   //adds players rewards/strength
		playerXP+=XP;
		if (playerXP >= expToLevel) {
			levelUp();
			expToLevel = expToLevel * 2;
		}
	}
	
	/**
	* Gets players name
	*@return players name 
	*/
	public String getPlayerName(){
		return playerName;
	}
   
   /**
   * Increases players strength in combat according to rewards/strength points they gain
   */
	public void levelUp() {
	   //adds XP to combat endurance and prints corresponding messages
	   //in the gui
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
   
   /**
   * Get players current health score
   */
	public void healPlayer() {
		setHealth(getMaxHealth());
	}
	
	/**
	*Get name of entity in combat
	*@return Players name in combat
	*/
	public String getCombatName(){
		return getPlayerName();
	}
	
	/**
	*controls players movements/turns
	*@param long variable time
	*/
	public void think(long time){
		super.think(time);//inherited from EntityMovable class
		
		//uses key input to control player movements up, right, left down respectively
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
