package templeraider.gui;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import templeraider.combat.enemies.BatLeftRight;
import templeraider.combat.enemies.BatUpDown;
import templeraider.combat.enemies.EnemyBarbarian;
import templeraider.combat.enemies.EnemyBoss;
import templeraider.combat.enemies.EnemyCultist;
import templeraider.combat.enemies.EnemyForestBandit;
import templeraider.combat.enemies.EnemyMummy;
import templeraider.combat.enemies.EnemyTempleBandit;
import templeraider.combat.enemies.EnemyTreeBandit;
import templeraider.combat.enemies.EnemyWolf;
import templeraider.entity.EntityHealthFountain;
import templeraider.entity.EntityPlayerSpawn;
import templeraider.entity.EntityStairs;

public class RPGMap {
	
	public int[][] mapGrid;
	/**
	 * Constructor: takes level as int and crates and moves the player to the new map
	 * @param int level
	 */
	public RPGMap(int level){
		switch(level){
		case 0:
			makeMap1();
			break;
		case 1:
			makeMap2();
			break;
		case 2:
			makeMap3();
			break;
		case 3:
			makeMap4();
			break;
		}
	}
	/**
	 * Returns true if a position on the map is passable or not
	 * @param String file's name
	 * @return String the map as a string
	 */
	private String readFile(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		}
		finally {
        br.close();
		}
	}

	
	/**
	 * converts the map from String to int and places it in the mapGrid
	 * @param String the map as a string
	 * @param boolean true if the map is an outside map
	 */
	private void stringToMap(String str , boolean outside){
		String[] lines= str.split("\n");
		
		mapGrid=new int[lines.length+65][lines[0].length()+65]; //the map is made extra large to add a boarder
		Random rand=new Random();
		
		//Note: Because of the expanted boarder the new corner of the map is 31X31
		for(int j=0;j<lines.length;j++){
			for(int i=0;i<lines[0].length();i++){
				if(outside){
					if(lines[j].charAt(i) == "0".charAt(0))
						mapGrid[j+31][i+31] = 30;//tree
					else if(lines[j].charAt(i) == "1".charAt(0))
						mapGrid[j+31][i+31] = 14;//rock
					else if(lines[j].charAt(i) == "5".charAt(0)){
						int grassTile=25;
						//these ramdomize the grass tiles to add some depth
						if(rand.nextDouble()<0.1){
							grassTile=26;
						}else if(rand.nextDouble()<0.4){
							grassTile=27;
						}
						
						mapGrid[j+31][i+31] = grassTile;//grass
					}else if(lines[j].charAt(i) == "6".charAt(0))
						mapGrid[j+31][i+31] = 5;//stone
					else if(lines[j].charAt(i) == "4".charAt(0))
						mapGrid[j+31][i+31] = 63;//temple
					else if(lines[j].charAt(i) == "9".charAt(0))
						mapGrid[j+31][i+31] = 9;//stairs
					else
						mapGrid[j+31][i+31] = 0;
				}
				else{ //if the map isn't outside it is assumed to be in the temple
					if(lines[j].charAt(i) == "0".charAt(0)){
						mapGrid[j+31][i+31] = 63;
					}else if(lines[j].charAt(i) == "3".charAt(0)){
						mapGrid[j+31][i+31] = 6;
					}else if(lines[j].charAt(i) == "5".charAt(0)){
						mapGrid[j+31][i+31] = 5;//floor
					}else if(lines[j].charAt(i) == "9".charAt(0)){
						mapGrid[j+31][i+31] = 9;//stairs
					}else if(lines[j].charAt(i) == "8".charAt(0)){
						mapGrid[j+31][i+31] = 8;//stairs
					}else if(lines[j].charAt(i) == "7".charAt(0)){
						mapGrid[j+31][i+31] = 7;//carpet
					}else if(lines[j].charAt(i) == "6".charAt(0)){
						mapGrid[j+31][i+31] = 6;//carpet
					}
				}
			}
		}
		// this calls the smoothing method
		if(outside){
			outsideImageAssigner();
		}else{
			insideImageAssigner();
		}
		
	}
	/**
	 * This fills in the special images like corners for the inside levels
	 */
	private void insideImageAssigner(){
		//copies the map so we can quickly assign the new images
		int[][] newMap=new int[mapGrid.length][mapGrid[0].length];
		for(int i=0;i<newMap.length;i++){
			for(int j=0;j<newMap[0].length;j++){
				newMap[i][j]=mapGrid[i][j];
			}
		}
		
		for(int i=31;i<newMap.length-31;i++){
			for(int j=31;j<newMap[0].length-31;j++){
				//walls
				if(mapGrid[i][j]==63){
					
					if(mapGrid[i-1][j]==63&&mapGrid[i][j+1]==63&&mapGrid[i+1][j]==63&&mapGrid[i][j-1]==63){
						newMap[i][j]=60;//uprightdownleft
					}else if(mapGrid[i-1][j]==63&&mapGrid[i][j+1]==63&&mapGrid[i+1][j]!=63&&mapGrid[i][j-1]!=63){
						newMap[i][j]=45;//bottomleft
					}else if(mapGrid[i-1][j]==63&&mapGrid[i][j+1]==63&&mapGrid[i+1][j]!=63&&mapGrid[i][j-1]==63){
						newMap[i][j]=46;//bottom
					}else if(mapGrid[i-1][j]==63&&mapGrid[i][j+1]!=63&&mapGrid[i+1][j]!=63&&mapGrid[i][j-1]==63){
						newMap[i][j]=47;//bottomright
					}else if(mapGrid[i-1][j]==63&&mapGrid[i][j+1]!=63&&mapGrid[i+1][j]==63&&mapGrid[i][j-1]==63){
						newMap[i][j]=44;//right
					}else if(mapGrid[i-1][j]!=63&&mapGrid[i][j+1]!=63&&mapGrid[i+1][j]==63&&mapGrid[i][j-1]==63){
						newMap[i][j]=42;//topright
					}else if(mapGrid[i-1][j]!=63&&mapGrid[i][j+1]==63&&mapGrid[i+1][j]==63&&mapGrid[i][j-1]==63){
						newMap[i][j]=41;//top
					}else if(mapGrid[i-1][j]!=63&&mapGrid[i][j+1]==63&&mapGrid[i+1][j]==63&&mapGrid[i][j-1]!=63){
						newMap[i][j]=40;//topleft
					}else if(mapGrid[i-1][j]==63&&mapGrid[i][j+1]==63&&mapGrid[i+1][j]==63&&mapGrid[i][j-1]!=63){
						newMap[i][j]=43;//left
					}else if(mapGrid[i-1][j]!=63&&mapGrid[i][j+1]!=63&&mapGrid[i+1][j]==63&&mapGrid[i][j-1]!=63){
						newMap[i][j]=56;//captop
					}else if(mapGrid[i-1][j]!=63&&mapGrid[i][j+1]==63&&mapGrid[i+1][j]!=63&&mapGrid[i][j-1]!=63){
						newMap[i][j]=57;//capleft
					}else if(mapGrid[i-1][j]==63&&mapGrid[i][j+1]!=63&&mapGrid[i+1][j]!=63&&mapGrid[i][j-1]!=63){
						newMap[i][j]=59;//capbottom
					}else if(mapGrid[i-1][j]!=63&&mapGrid[i][j+1]!=63&&mapGrid[i+1][j]!=63&&mapGrid[i][j-1]==63){
						newMap[i][j]=58;//capright
					}else if(mapGrid[i-1][j]!=63&&mapGrid[i][j+1]!=63&&mapGrid[i+1][j]!=63&&mapGrid[i][j-1]!=63){
						newMap[i][j]=23;//capsingle
					}else if(mapGrid[i-1][j]==63&&mapGrid[i][j+1]!=63&&mapGrid[i+1][j]==63&&mapGrid[i][j-1]!=63){
						newMap[i][j]=62;//verticalwall
					}else if(mapGrid[i-1][j]!=63&&mapGrid[i][j+1]==63&&mapGrid[i+1][j]!=63&&mapGrid[i][j-1]==63){
						newMap[i][j]=61;//horizontalwall
					}
				}
			}
		}	
		//switches the out line areas to black tiles
		for(int i=0;i<newMap.length;i++){
			for(int j=0;j<newMap[0].length;j++){
				if(newMap[i][j]==0){
					newMap[i][j]=60;
				}
			}
		}
		//over writes the mapGrid with the new smoothed map
		mapGrid=newMap;
		
	}
	/**
	 * This fills in the special images like corners for the out door levels
	 */
	private void outsideImageAssigner(){
		//copies the map so we can quickly assign the new images
		int[][] newMap=new int[mapGrid.length][mapGrid[0].length];
		for(int i=0;i<newMap.length;i++){
			for(int j=0;j<newMap[0].length;j++){
				newMap[i][j]=mapGrid[i][j];
			}
		}
		
		for(int i=31;i<newMap.length-31;i++){
			for(int j=31;j<newMap[0].length-31;j++){
				//trees
				if(mapGrid[i][j]==30){
					//uprughtdownleft
					if(mapGrid[i-1][j]==30&&mapGrid[i][j+1]==30&&mapGrid[i+1][j]!=30&&mapGrid[i][j-1]!=30){
						newMap[i][j]=33;//bottomleft
					}else if(mapGrid[i-1][j]==30&&mapGrid[i][j+1]==30&&mapGrid[i+1][j]!=30&&mapGrid[i][j-1]==30){
						newMap[i][j]=31;//bottom
					}else if(mapGrid[i-1][j]==30&&mapGrid[i][j+1]!=30&&mapGrid[i+1][j]!=30&&mapGrid[i][j-1]==30){
						newMap[i][j]=33;//bottomright
					}else if(mapGrid[i-1][j]==30&&mapGrid[i][j+1]!=30&&mapGrid[i+1][j]==30&&mapGrid[i][j-1]==30){
						newMap[i][j]=32;//right
					}else if(mapGrid[i-1][j]!=30&&mapGrid[i][j+1]!=30&&mapGrid[i+1][j]==30&&mapGrid[i][j-1]==30){
						newMap[i][j]=33;//topright
					}else if(mapGrid[i-1][j]!=30&&mapGrid[i][j+1]==30&&mapGrid[i+1][j]==30&&mapGrid[i][j-1]==30){
						newMap[i][j]=31;//top
					}else if(mapGrid[i-1][j]!=30&&mapGrid[i][j+1]==30&&mapGrid[i+1][j]==30&&mapGrid[i][j-1]!=30){
						newMap[i][j]=33;//topleft
					}else if(mapGrid[i-1][j]==30&&mapGrid[i][j+1]==30&&mapGrid[i+1][j]==30&&mapGrid[i][j-1]!=30){
						newMap[i][j]=32;//left
					}
				}
				//rock
				if(mapGrid[i][j]==14){
					//uprughtdownleft
					if(mapGrid[i-1][j]==14&&mapGrid[i][j+1]==14&&mapGrid[i+1][j]!=14&&mapGrid[i][j-1]!=14){
						newMap[i][j]=16;//bottomleft
					}else if(mapGrid[i-1][j]==14&&mapGrid[i][j+1]==14&&mapGrid[i+1][j]!=14&&mapGrid[i][j-1]==14){
						newMap[i][j]=17;//bottom
					}else if(mapGrid[i-1][j]==14&&mapGrid[i][j+1]!=14&&mapGrid[i+1][j]!=14&&mapGrid[i][j-1]==14){
						newMap[i][j]=18;//bottomright
					}else if(mapGrid[i-1][j]==14&&mapGrid[i][j+1]!=14&&mapGrid[i+1][j]==14&&mapGrid[i][j-1]==14){
						newMap[i][j]=15;//right
					}else if(mapGrid[i-1][j]!=14&&mapGrid[i][j+1]!=14&&mapGrid[i+1][j]==14&&mapGrid[i][j-1]==14){
						newMap[i][j]=12;//topright
					}else if(mapGrid[i-1][j]!=14&&mapGrid[i][j+1]==14&&mapGrid[i+1][j]==14&&mapGrid[i][j-1]==14){
						newMap[i][j]=11;//top
					}else if(mapGrid[i-1][j]!=14&&mapGrid[i][j+1]==14&&mapGrid[i+1][j]==14&&mapGrid[i][j-1]!=14){
						newMap[i][j]=10;//topleft
					}else if(mapGrid[i-1][j]==14&&mapGrid[i][j+1]==14&&mapGrid[i+1][j]==14&&mapGrid[i][j-1]!=14){
						newMap[i][j]=13;//left
					}else if(mapGrid[i-1][j]!=14&&mapGrid[i][j+1]!=14&&mapGrid[i+1][j]==14&&mapGrid[i][j-1]!=14){
						newMap[i][j]=19;//captop
					}else if(mapGrid[i-1][j]!=14&&mapGrid[i][j+1]==14&&mapGrid[i+1][j]!=14&&mapGrid[i][j-1]!=14){
						newMap[i][j]=20;//capleft
					}else if(mapGrid[i-1][j]==14&&mapGrid[i][j+1]!=14&&mapGrid[i+1][j]!=14&&mapGrid[i][j-1]!=14){
						newMap[i][j]=22;//capbottom
					}else if(mapGrid[i-1][j]!=14&&mapGrid[i][j+1]!=14&&mapGrid[i+1][j]!=14&&mapGrid[i][j-1]==14){
						newMap[i][j]=21;//capright
					}else if(mapGrid[i-1][j]!=14&&mapGrid[i][j+1]!=14&&mapGrid[i+1][j]!=14&&mapGrid[i][j-1]!=14){
						newMap[i][j]=23;//capsingle
					}else if(mapGrid[i-1][j]==14&&mapGrid[i][j+1]!=14&&mapGrid[i+1][j]==14&&mapGrid[i][j-1]!=14){
						newMap[i][j]=28;//verticalwall
					}else if(mapGrid[i-1][j]!=14&&mapGrid[i][j+1]==14&&mapGrid[i+1][j]!=14&&mapGrid[i][j-1]==14){
						newMap[i][j]=29;//horizontalwall
					}
				}
			}
		}
		//fills in the boarder with trees so it looks like the forest goes on forever
		for(int i=0;i<newMap.length;i++){
			for(int j=0;j<newMap[0].length;j++){
				if(newMap[i][j]==0){
					newMap[i][j]=30;
				}
			}
		}
		//over writes the mapGrid with the new smoothed map
		mapGrid=newMap;
		
	}

	/**
	 * makes map 1 when called
	 */
	private void makeMap1(){
		try{
			String map1=readFile("templeraider/maps/map1.txt");
			//calls the converter from string to 2D int array
			stringToMap(map1 , true);
			//adds the Entities to the map at the (x,Y) coordinates
			new EntityPlayerSpawn(33,33);
			new EntityStairs(130,44);
			new EnemyForestBandit(34,55);
			new EnemyForestBandit(38,52);
			new EnemyForestBandit(58,48);
			new EnemyForestBandit(71,45);
			new EnemyForestBandit(57,57);
			new EnemyForestBandit(73,57);
			new EnemyForestBandit(79,43);
			new EnemyForestBandit(62,32);
			new EnemyForestBandit(58,39);
			new EnemyForestBandit(121,57);
			new EnemyForestBandit(115,44);
			new EnemyForestBandit(115,39);
			new EnemyWolf(100,39);
			new EnemyWolf(108,46);
			new EnemyWolf(70,51);
			new EnemyWolf(67,36);
			new EnemyTreeBandit(89,33);
			new EnemyTreeBandit(125,36);
			new EnemyTreeBandit(122,45);
			new EnemyTreeBandit(107,55);
			new EntityHealthFountain(37,32);
			new EntityHealthFountain(64,32);
			new EntityHealthFountain(98,55);
		 }
		 catch(IOException e){
			System.out.println("invalid map name....");
		 }
	
	}
	/**
	 * makes map 2 when called
	 */
	private void makeMap2(){
		try{
			String map2=readFile("templeraider/maps/map2.txt");
			//calls the converter from string to 2D int array
			stringToMap(map2 , false);
			//adds the Entities to the map at the (x,Y) coordinates
			new EntityPlayerSpawn(35,58);
			new EntityStairs(57,32);
			new EnemyMummy(56,60);
			new EnemyMummy(48,79);
			new BatUpDown(71,53);
			new BatLeftRight(66,50);
			new BatLeftRight(81,58);
			new BatLeftRight(81,61);
			new EnemyTempleBandit(62,43);
			new EnemyTempleBandit(42,35);
			new EnemyTempleBandit(79,46);
			new EnemyTempleBandit(77,61);
			new EnemyTempleBandit(78,80);
			new EntityHealthFountain(34,34);
			new EntityHealthFountain(81,47);
		 }
		 catch(IOException e){
			System.out.println("invalid map name....");
		 }
		
	}
	/**
	 * makes map 3 when called 
	 */
	private void makeMap3(){
		try{
			String map3=readFile("templeraider/maps/map3.txt");
			//calls the converter from string to 2D int array
			stringToMap(map3 , false);
			//adds the Entities to the map at the (x,Y) coordinates
			new EntityPlayerSpawn(46,61);
			new EntityStairs(46,32);
			new EnemyCultist(52,58);
			new EnemyCultist(40,52);
			new EnemyCultist(52,46);
			new EnemyCultist(40,40);
			new EnemyCultist(52,34);
			new EnemyCultist(40,34);
			new EnemyBarbarian(32,32);
			new EnemyBarbarian(35,40);
			new EnemyBarbarian(35,46);
			new EnemyBarbarian(35,59);
			new EnemyBarbarian(57,57);
			new EnemyBarbarian(57,46);
			new EnemyBarbarian(60,32);
			new EntityHealthFountain(46,34);
			new EntityHealthFountain(32,61);
		 }
		 catch(IOException e){
			System.out.println("invalid map name....");
		 }
	
	}
	/**
	 * makes map 4 when called
	 */
	private void makeMap4(){
		try{
			String map4=readFile("templeraider/maps/map4.txt");
			//calls the converter from string to 2D int array
			stringToMap(map4 , false);
			//adds the Entities to the map at the (x,Y) coordinates
			new EntityPlayerSpawn(37,41);
			new EnemyBoss(37,32);
		}
		catch(IOException e){
			System.out.println("invalid map name....");
		}

	}

	/**
	 * Returns true if a position on the map is passable or not
	 * @param x coordinate
	 * @param y coordinate
	 * @return boolean true if position on map is passable
	 */
	public boolean isPassable(int x,int y){
		if((5 <= mapGrid[y][x] && mapGrid[y][x] <= 9) || (mapGrid[y][x] >= 25 && mapGrid[y][x]<=27))
			return true;
		else
			return false;
	}
}
