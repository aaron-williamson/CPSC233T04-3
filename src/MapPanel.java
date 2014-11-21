import java.awt.*;
import javax.swing.*;

public class MapPanel extends JComponent{
	public int tileSize=32;
	private String[] mapImagePaths = new String[64];
	private Image[] mapImages=new Image[mapImagePaths.length];
	private boolean titleScreenShown = false;
	
	MapPanel(){
		mapImagePaths[0]="../img/water.png";
		mapImagePaths[1]="../img/water.png";
		mapImagePaths[2]="../img/water.png";
		mapImagePaths[3]="../img/water.png";
		mapImagePaths[4]="../img/water.png";
		mapImagePaths[5]="../img/floormain.png";
		mapImagePaths[6]="../img/redfloor.png";
		mapImagePaths[7]="../img/yellowfloor.png";
		mapImagePaths[8]="../img/stairsdown.png";
		mapImagePaths[9]="../img/stairsup.png";
		mapImagePaths[10]="../img/rockdownright.png";
		mapImagePaths[11]="../img/rockdownleftright.png";
		mapImagePaths[12]="../img/rockdownleft.png";
		mapImagePaths[13]="../img/rockupdownright.png";
		mapImagePaths[14]="../img/rockmain.png";
		mapImagePaths[15]="../img/rockupdownleft.png";
		mapImagePaths[16]="../img/rockupright.png";
		mapImagePaths[17]="../img/rockupleftright.png";
		mapImagePaths[18]="../img/rockupleft.png";
		mapImagePaths[19]="../img/rockdown.png";
		mapImagePaths[20]="../img/rockright.png";
		mapImagePaths[21]="../img/rockleft.png";
		mapImagePaths[22]="../img/rockup.png";
		mapImagePaths[23]="../img/rocksolo.png";
		mapImagePaths[24]="../img/water.png";
		mapImagePaths[25]="../img/grass.png";
		mapImagePaths[26]="../img/grass2.png";
		mapImagePaths[27]="../img/grass3.png";
		mapImagePaths[28]="../img/rockupdown.png";
		mapImagePaths[29]="../img/rockleftright.png";
		mapImagePaths[30]="../img/maintress.png";
		mapImagePaths[31]="../img/treesnoupdown.png";
		mapImagePaths[32]="../img/treesnoleftright.png";
		mapImagePaths[33]="../img/treeslone.png";
		mapImagePaths[34]="../img/water.png";
		mapImagePaths[35]="../img/water.png";
		mapImagePaths[36]="../img/water.png";
		mapImagePaths[37]="../img/water.png";
		mapImagePaths[38]="../img/water.png";
		mapImagePaths[39]="../img/water.png";
		mapImagePaths[40]="../img/walldownright.png";
		mapImagePaths[41]="../img/walldownleftright.png";
		mapImagePaths[42]="../img/walldownleft.png";
		mapImagePaths[43]="../img/wallupdownright.png";
		mapImagePaths[44]="../img/wallupdownleft.png";
		mapImagePaths[45]="../img/wallupright.png";
		mapImagePaths[46]="../img/wallupleftright.png";
		mapImagePaths[47]="../img/wallupleft.png";
		mapImagePaths[48]="../img/wallcornerTL.png";
		mapImagePaths[49]="../img/water.png";
		mapImagePaths[50]="../img/wallcornerTR.png";
		mapImagePaths[51]="../img/water.png";
		mapImagePaths[52]="../img/water.png";
		mapImagePaths[53]="../img/wallcornerBL.png";
		mapImagePaths[54]="../img/water.png";
		mapImagePaths[55]="../img/wallcornerBR.png";
		mapImagePaths[56]="../img/walldown.png";
		mapImagePaths[57]="../img/wallright.png";
		mapImagePaths[58]="../img/wallleft.png";
		mapImagePaths[59]="../img/wallup.png";
		mapImagePaths[60]="../img/black.png";
		mapImagePaths[61]="../img/wallleftright.png";
		mapImagePaths[62]="../img/wallupdown.png";
		mapImagePaths[63]="../img/wallfull.png";
		
		loadTileImages();
	}
	/**
	 * reload the tile images, use if you've changed the tileset
	 */
	public void loadTileImages(){
		for(int i=0;i<mapImagePaths.length;i++){
			mapImages[i]=Toolkit.getDefaultToolkit().getImage(mapImagePaths[i]);
		}
	}
	
	/**
	 * Sets a specific tileid to a new image path, use if you want to change the tileset
	 * @param tileId
	 * @param str
	 */
	public void setTileImage(int tileId,String str){
		mapImagePaths[tileId]=str;
	}
	
	/**
	 * Draws the map onto the map panel
	 * @param g Graphics object
	 * @param offsetX X offset for the camera position
	 * @param offsetY Y offset for the camera position
	 */
	private void drawMap(Graphics g,int offsetX,int offsetY){
		RPGMap rpgmap=Game.getInstance().getMap();
		
		for(int i=0;i<rpgmap.mapGrid[0].length;i++){
			for(int j=0;j<rpgmap.mapGrid.length;j++){
				int xpos=i*tileSize-offsetX;
				int ypos=j*tileSize-offsetY;
				
				if(xpos>-tileSize&&xpos<getWidth()&&ypos>-tileSize&&ypos<getHeight()){
					g.drawImage(mapImages[rpgmap.mapGrid[j][i]],xpos,ypos,tileSize,tileSize,this);
				}
			}
		}
	}
	
	/**
	 * Draw all entities onto the map panel
	 * @param g Graphics
	 * @param offsetX X camera position
	 * @param offsetY Y camera position
	 */
	private void drawEntities(Graphics g,int offsetX,int offsetY){
		Entity[] entityArray=Game.getInstance().getEntities().getAll();
		
		for(int i=0;i<entityArray.length;i++){
			int xpos=entityArray[i].getX()*tileSize;
			int ypos=entityArray[i].getY()*tileSize;
			Image img=entityArray[i].getImage();
			
			if(entityArray[i].isMovable()){
				EntityMovable movable=(EntityMovable)entityArray[i];
				xpos+=movable.getInterpolationX()*tileSize;
				ypos+=movable.getInterpolationY()*tileSize;
			}
			
			xpos-=offsetX;
			ypos-=offsetY;
			if(xpos>-tileSize&&xpos<getWidth()&&ypos>-tileSize&&ypos<getHeight()){
				g.drawImage(img,xpos,ypos,tileSize,tileSize,this);
			}
		}
	}
	
	/**
	 * darkens the entire map
	 */
	private void drawBlackMask(Graphics g){
		g.setColor(new Color((float)0.0,(float)0.0,(float)0.0,(float)0.5));
		g.fillRect(0, 0, getWidth(), getHeight());
	}
	
	private void drawTitleScreen(Graphics g){
		Game.getInstance().getGUI().disableButton(3);
		drawBlackMask(g);
		g.setColor(Color.RED);
		g.setFont(new Font("default", Font.BOLD, 40));
		g.drawString("    Welcome to "+Game.GAME_TITLE, 5, 100);
		g.setFont(new Font("default", Font.BOLD, 16));
		g.drawString("Controls:", 5, 200);
		g.drawString("Move Up: (up arrow)(w)", 5, 220);
		g.drawString("Move Down: (down arrow)(s)", 5, 240);
		g.drawString("Move Left: (left arrow)(a)", 5, 260);
		g.drawString("Move Right: (right arrow)(d)", 5, 280);
		g.drawString("Press Start Game to continue...", 5, 300);
	}

	private void drawPauseScreen(Graphics g){
		drawBlackMask(g);
		g.setColor(Color.RED);
		g.setFont(new Font("default", Font.BOLD, 80));
		g.drawString("        Paused", 5, 220);
	}
	
	private void drawHealthBars(Graphics g){
		drawBlackMask(g);
		Game.getInstance().getCombat().getPlayer().drawHealthBar(g,16,getHeight()-24,getWidth()/3,16,(float)0.9,(float)0.0,(float)0.9);
		Game.getInstance().getCombat().getEnemy().drawHealthBar(g,getWidth()-32-getWidth()/3,getHeight()-24,getWidth()/3,16,(float)0.9,(float)0.0,(float)0.0);
	}
	
	public boolean titleScreenShown() {
		return titleScreenShown;
	}
	
	public void setTitleScreenShown(boolean a){
		titleScreenShown = a;

		Game.getInstance().getGUI().enableButton(3);
	}
	
	
	private void drawEndScreen(Graphics g){
		drawBlackMask(g);
		g.setColor(Color.RED);
		g.setFont(new Font("default", Font.BOLD, 20));
		g.drawString(Game.getInstance().getEndmessage(), 220, 220);
	}	
	
	@Override
	public void paint(Graphics g){
		super.paint(g);

		int offsetX=0;
		int offsetY=0;
		
		Entity player=Game.getInstance().getEntities().getByClass("player")[0];
		
		if(player!=null){
			offsetX=player.getX()*tileSize-getSize().width/2+tileSize/2;
			offsetY=player.getY()*tileSize-getSize().height/2+tileSize/2;
			
			EntityMovable movable=(EntityMovable)player;
			offsetX+=movable.getInterpolationX()*tileSize;
			offsetY+=movable.getInterpolationY()*tileSize;
		}
		
		drawMap(g,offsetX,offsetY);
		drawEntities(g,offsetX,offsetY);
		
		//Draws the title screen
		if(titleScreenShown == false){
			drawTitleScreen(g);
		}else if(Game.getInstance().getCombat().isInCombat()){
			drawHealthBars(g);
		}else if(Game.getInstance().getGUI().isGamePaused() == true){
			drawPauseScreen(g);
		}else if(Game.getInstance().isGameEnd() == true){
			drawEndScreen(g);
		}
	}
	
}