import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class EntityCombat extends EntityMovable{
	protected int combatEndurance;
	protected int combatStrength;
	protected int combatLuck;
	protected double combatDefense;
	protected String combatName;
	protected int combatXP;
	private int combatHealth;
	
	protected String combatAttackMessage;
	protected String combatMissMessage;
	protected String combatCriticalMessage;
	protected String combatDefendMessage;
	
	private Random rand;
	
	public EntityCombat(int xcoord, int ycoord){
		super(xcoord, ycoord);
		combatEndurance=5;
		combatStrength=5;
		combatLuck=5;
		combatDefense=0;
		combatName="Unnamed Combat Entity";
		combatXP=5;
		
		combatAttackMessage="hits you with a stick!";
		combatMissMessage="It missed!";
		combatCriticalMessage="It was a critical hit!";
		combatDefendMessage="takes a defensive stance.";
		
		setHealth(getMaxHealth());
		
		rand=new Random();
	}
	
	/**
	 * get the combat name for the entity
	 * @return
	 */
	public String getCombatName(){
		return combatName;
	}
	
	/**
	 * Get the maximum health value of this entity
	 * @return
	 */
	public int getMaxHealth(){
		return combatEndurance*10;
	}
	
	/**
	 * Get the current health of this entity
	 * @return
	 */
	public int getHealth(){
		return combatHealth;
	}
	
	/**
	 * Sets the health of this entity
	 * @param health
	 */
	public void setHealth(int health){
		if(health>getMaxHealth()){
			combatHealth=getMaxHealth();
		}else if(health<0){
			combatHealth=0;
		}else{
			combatHealth=health;
		}
	}
	
	public int getRewardedXP(){
		return combatXP;
	}
	
	/**
	 * inflicts damage to this entity
	 * @param damage
	 */
	public void inflictDamage(int damage){
		setHealth(getHealth()-damage);
	}
	
	/**
	 * Gets the chance this entity has to hit
	 * @return
	 */
	private double getHitChance(){
		return 0.8+combatLuck*0.5-0.01;
	}
	
	/**
	 * Gets the chance this entity has to get a critical hit
	 * @return
	 */
	private double getCriticalChance(){
		return combatLuck*0.01;
	}
	
	/**
	 * Get this entity's defense value, 0 is no defense, 1 is full defense
	 * @return
	 */
	public double getDefense(){
		return combatDefense;
	}
	
	/**
	 * Get the base damage that this entity would do to another combat entity
	 * @param defender
	 * @return
	 */
	public int getDamage(EntityCombat defender){
		int damage=combatStrength;
		damage+=rand.nextInt(5+combatLuck);
		damage*=1-defender.getDefense();
		
		if(damage<1){
			damage=1;
		}
		
		return damage;
	}
	
	/**
	 * Attack another combat entity
	 * @param defender
	 * @return message
	 */
	public void attack(EntityCombat defender){
		String message=new String(combatAttackMessage);
		if(rand.nextDouble()<=getHitChance()){
			
			int damage=getDamage(defender);
			
			if(rand.nextDouble()<=getCriticalChance()){
				damage*=2;
				message+=" "+combatCriticalMessage;
			}
			
			damage+=1;
			
			defender.inflictDamage(damage);
			message+=" "+defender.getCombatName()+" takes "+damage+" Damage!";
		}else{
			message+=" "+combatMissMessage;
		}
		
		Game.getGame().getGUI().printLine(getCombatName()+" "+message);
	}
	
	/**
	 * defend on this turn
	 * @return message
	 */
	public void defend(){
		combatDefense=0.8;
		Game.getGame().getGUI().printLine(getCombatName()+" "+combatDefendMessage);
	}
	
	/**
	 * attacks or defend, the most basic enemy ai
	 * @param defender
	 */
	public void attackOrDefend(EntityCombat defender){
		double defendChance=0.2;
		if(getHealth()<getMaxHealth()/2){
			defendChance*=2;
		}
		
		if(rand.nextDouble()<=defendChance){
			defend();
		}else{
			attack(defender);
		}
	}
	
	/**
	 * Called to do the turn of the entity, the ai for combat goes in here
	 * @param defender
	 */
	public void doTurn(EntityCombat defender){
		//reset defense to zero incase the last move was defend
		combatDefense=0;
		
		attackOrDefend(defender);
	}
	
	/**
	 * Draws the health bar for a EntityCombat
	 * @param g
	 * @param xpos
	 * @param ypos
	 * @param width
	 * @param height
	 * @param entity
	 * @param color R
	 * @param color G
	 * @param color B
	 */
	public void drawHealthBar(Graphics g,int xpos,int ypos,int width,int height, float colR, float colG, float colB){
		double r=(double)getHealth()/(double)getMaxHealth();
		
		g.setColor(Color.BLACK);
		g.fillRect(xpos,ypos,width,height);
		
		xpos+=2;
		ypos+=2;
		width-=4;
		height-=4;
		
		g.setColor(new Color((float)(colR*0.5),(float)(colG*0.5),(float)(colB*0.5)));
		g.fillRect(xpos,ypos,width,height);
		
		g.setColor(new Color(colR,colG,colB));
		g.fillRect(xpos, ypos, (int)(r*width), height);
		
		g.setColor(Color.WHITE);
		g.drawString(getHealth()+"/"+getMaxHealth(), xpos+2, ypos+10);
	}
};
