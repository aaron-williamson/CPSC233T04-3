import java.awt.*;
import javax.swing.*;

public class MapPanel extends JComponent{
	public int tileSize=32;
	private String[] mapImagePaths = {"../img/water.png" ,"../img/grass.png"};
	private Image[] mapImages=new Image[mapImagePaths.length];
	
	MapPanel(){
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
		RPGMap rpgmap=Game.getGame().getMap();
		
		for(int i=0;i<rpgmap.mapGrid[0].length;i++){
			for(int j=0;j<rpgmap.mapGrid.length;j++){
				g.drawImage(mapImages[rpgmap.mapGrid[j][i]],i*tileSize-offsetX,j*tileSize-offsetY,tileSize,tileSize,this);
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
		Entity[] entityArray=Game.getGame().getEntities().getAll();
		
		for(int i=0;i<entityArray.length;i++){
			int xpos=entityArray[i].getX()*tileSize;
			int ypos=entityArray[i].getY()*tileSize;
			Image img=entityArray[i].getImage();
			
			if(entityArray[i].isMovable()){
				EntityMovable movable=(EntityMovable)entityArray[i];
				xpos+=movable.getInterpolationX()*tileSize;
				ypos+=movable.getInterpolationY()*tileSize;
			}
			g.drawImage(img,xpos-offsetX,ypos-offsetY,tileSize,tileSize,this);
		}
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);

		int offsetX=0;
		int offsetY=0;
		
		Entity player=Game.getGame().getEntities().getByClass("player")[0];
		
		if(player!=null){
			offsetX=player.getX()*tileSize-getSize().width/2+tileSize/2;
			offsetY=player.getY()*tileSize-getSize().height/2+tileSize/2;
			
			EntityMovable movable=(EntityMovable)player;
			offsetX+=movable.getInterpolationX()*tileSize;
			offsetY+=movable.getInterpolationY()*tileSize;
		}
		
		drawMap(g,offsetX,offsetY);
		drawEntities(g,offsetX,offsetY);
	}
	
}

