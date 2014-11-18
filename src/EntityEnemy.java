import java.util.Random;

public class EntityEnemy extends EntityCombat{
	public String getClassID(){return "enemy";}
	
	EntityEnemy(int xcoord, int ycoord){
		super(xcoord, ycoord);
		setMoveSpeed(16);
	}
	
	//lose the game if the player runs into an enemy
	public void onCollide(Entity ent){
		if(ent.getClassID().equals("player")){
			Game.getGame().getCombat().startCombat((EntityPlayer)ent, (EntityCombat)this, true);
		}
	};
	
	public void think(long time){
		//super cheap 'ai' here
		
		//get the nearest player
		Entity[] playersarray=Game.getGame().getEntities().getByClass("player");
		Entity target=playersarray[0];
		double windist=999999;
		for(int i=0;i<playersarray.length;i++){
			double dist=this.distance(playersarray[i]);
			if(dist<windist){
				target=playersarray[i];
				windist=dist;
			}
		}
		
		int difx=target.getX()-this.getX();
		int dify=target.getY()-this.getY();
		
		//if we need to move both horizontally and vertically, choose one randomly
		if(difx!=0 && dify!=0){
			boolean rand=new Random().nextBoolean();
			if(rand){
				difx=0;
			}else{
				dify=0;
			}
		}
		
		if(difx>0){
			this.moveRight();
		}else if(difx<0){
			this.moveLeft();
		}else if(dify>0){
			this.moveDown();
		}else if(dify<0){
			this.moveUp();
		}
		
	}
};