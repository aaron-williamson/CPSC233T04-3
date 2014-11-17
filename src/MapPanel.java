import java.awt.*;
import javax.swing.*;

public class MapPanel extends JComponent{
	public int tileSize=32;
	static private String[] mapImagePaths = {"../img/water.png" ,"../img/grass.png"};
	private Image[] mapImages=new Image[MapPanel.mapImagePaths.length];
	private long timestamp=0;//timestamp for animations and interpolation
	
	
	MapPanel(){
		for(int i=0;i<MapPanel.mapImagePaths.length;i++){
			mapImages[i]=Toolkit.getDefaultToolkit().getImage(MapPanel.mapImagePaths[i]);
		}
	}
	
	/**
	 * Draws the map onto the map panel
	 * @param g Graphics object
	 * @param offsetX X offset for the camera position
	 * @param offsetY Y offset for the camera position
	 */
	private void drawMap(Graphics g,int offsetX,int offsetY){
		RPGMap rpgmap=Game.getGame().getMap();
		
		for(int i=0;i<rpgmap.mapwidth;i++){
			for(int j=0;j<rpgmap.mapheight;j++){
				g.drawImage(mapImages[rpgmap.mapGrid[j][i]],i*tileSize-offsetX,j*tileSize-offsetY,tileSize,tileSize,this);
			}
		}
	}
	
	private void drawEntities(Graphics g,int offsetX,int offsetY){
		Entity[] entityArray=Game.getGame().getEntities().getAll();
		
		for(int i=0;i<entityArray.length;i++){
			int xpos=entityArray[i].getX()*tileSize;
			int ypos=entityArray[i].getY()*tileSize;
			Image img=entityArray[i].getImage(timestamp);
			//g.drawString(entityArray[i].debuggraphic(),xpos-offsetX,ypos-offsetY);
			g.drawImage(img,xpos-offsetX,ypos-offsetY,tileSize,tileSize,this);
		}
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		timestamp=(timestamp+1)%(Long.MAX_VALUE-1);
		Entity player=Game.getGame().getEntities().getByClass("player")[0];
		
		int offsetX=player.getX()*tileSize-getSize().width/2+tileSize/2;
		int offsetY=player.getY()*tileSize-getSize().height/2+tileSize/2;
		
		drawMap(g,offsetX,offsetY);
		drawEntities(g,offsetX,offsetY);
	}
	
}

