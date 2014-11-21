import java.awt.*;

import java.util.Random;

public class EnemyForestBandit extends EntityEnemy {
	private boolean chargeStatus=false;
	private Random rand=new Random();
	
	public EnemyForestBandit(int xcoord, int ycoord) {
		super(xcoord, ycoord);
		combatEndurance = 6;
		combatStrength = 12;
		combatLuck = 0;
		setHealth(getMaxHealth());
		combatName = "Forest Bandit";
		combatXP = 1;
		combatAttackMessage="stabs at you with a butter knife!";
		combatCriticalMessage="He buttered your bread!";
		defaultImage = Toolkit.getDefaultToolkit().getImage("../img/forestbandit/south.png");
	}
	
	private void chargeAttack(EntityCombat defender){
		chargeStatus=false;
		int damage=getDamage(defender)*3;
		defender.inflictDamage(damage);
		Game.getInstance().getGUI().printLine(getCombatName()+" powerfully slashes! You're toast. "+defender.getCombatName()+" takes "+damage+" Damage!");
	}
	
	private void charge(){
		chargeStatus=true;
		Game.getInstance().getGUI().printLine(getCombatName()+" charges a powerful blow.");
	}
	
	public void doTurn(EntityCombat defender){
		//reset defense to zero incase the last move was defend
		combatDefense=0;
		
		
		if(chargeStatus){
			chargeAttack(defender);
		}else if(rand.nextDouble()<0.3){
			charge();
		}else{
			attackOrDefend(defender);
		}
	}
}
