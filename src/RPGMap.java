import java.util.Random;

public class RPGMap {
	
	static String[] debuggraphics = {"X"," "};
	//boolean[] passableMap={false,false,false,false,false,true,true,true,true,true};
	static public int mapwidth = 100;
	static public int mapheight = 100;
	
	
	//passablrMap[1]=true;
	public int[][] mapGrid;
	
	RPGMap(){
		makeMap2();
	}
	
	private void makeblankmap(){
		for(int i = 0; i < mapheight ; i++){
			for(int j = 0; j < mapwidth ; j++){
				if(i == 0 || i == mapheight-1 || j == 0 || j == mapwidth-1){
					mapGrid[i][j] =0;
				}
				else{
					mapGrid[i][j] = 1;
				}
			}
		}
	}
	private void makerowmap(){
		for(int i = 0; i < mapheight ; i++){
			for(int j = 0; j < mapwidth ; j++){
				if(i == 0 || i == mapheight-1 || j == 0 || j == mapwidth-1){
					mapGrid[i][j] =0;
				}
				else if(i%2 == 0 && j > 1 && j < mapwidth - 2){
					mapGrid[i][j] =0;
				}
				else{
					mapGrid[i][j] = 1;
				}
			}
		}
	}
	//converts the map from String to int and places it in the mapGrid
	private void stringToMap(String str , boolean outside){
		String[] lines= str.split("\n");
		
		mapGrid=new int[lines.length+65][lines[0].length()+65];
		Random rand=new Random();
		
		
		for(int j=0;j<lines.length;j++){
			for(int i=0;i<lines[0].length();i++){
				if(outside){
					if(lines[j].charAt(i) == "0".charAt(0))
						mapGrid[j+31][i+31] = 30;//tree
					else if(lines[j].charAt(i) == "1".charAt(0))
						mapGrid[j+31][i+31] = 14;//rock
					else if(lines[j].charAt(i) == "5".charAt(0)){
						int grassTile=25;
						
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
				else{
					if(lines[j].charAt(i) == "0".charAt(0)){
						mapGrid[j+31][i+31] = 63;
					}else if(lines[j].charAt(i) == "3".charAt(0)){
						mapGrid[j+31][i+31] = 0;
					}else if(lines[j].charAt(i) == "5".charAt(0)){
						mapGrid[j+31][i+31] = 5;//floor
					}else if(lines[j].charAt(i) == "9".charAt(0)){
						mapGrid[j+31][i+31] = 9;//stairs
					}else if(lines[j].charAt(i) == "8".charAt(0)){
						mapGrid[j+31][i+31] = 8;//stairs
					}
				}
			}
		}
		if(outside){
			outsideImageAssigner();
		}else{
			insideImageAssigner();
		}
		
	}
	//fills in the proper images
	private void insideImageAssigner(){
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
		
		for(int i=0;i<newMap.length;i++){
			for(int j=0;j<newMap[0].length;j++){
				if(newMap[i][j]==0){
					newMap[i][j]=60;
				}
			}
		}
		
		mapGrid=newMap;
		
	}
	
	private void outsideImageAssigner(){
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
		
		for(int i=0;i<newMap.length;i++){
			for(int j=0;j<newMap[0].length;j++){
				if(newMap[i][j]==0){
					newMap[i][j]=30;
				}
			}
		}
		
		mapGrid=newMap;
		
	}

//makes map 1
	private void makeMap1(){
		String map1=
		"0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000\n"
	   +"0555555000000000011111000000000555555500000000000005555550000000000000000000000000000000000000000000\n"
	   +"0555555550000000011110000000000000005500000000055555555555500100000000555555555000000000000000000000\n"
	   +"0555555555000000000111110000000000005500000055555555555000001115555555550000000000000000000000000000\n"
	   +"0555555555500000000000000000000000005500000055555555500000000100000555555555000000000000000000000000\n"
	   +"0555555555550000000000555555550000005500000055555500000000000000000555555550000000000000055555500000\n"
	   +"0555555555550000000000000555555555555555555555555555555550000000000555555550000000000055555555000000\n"
	   +"0555555555555000000000000005555555555555555555555555555555000000005555555555555555555555555500000000\n"
	   +"0555555555555500000000000005555555555555555555555555555555000000555555555555555555555555500000000000\n"
	   +"0555555555555500000000000000000555555555555555555555555555000000005555555555500000555550000000000444\n"
	   +"0555555555555550000011111110000000055555555555555555555555500005555555000000000055555110000000444444\n"
	   +"0555555555555550000000111111111100055555555555555555550000000011111550000000005555111110000004444444\n"
	   +"0555111115555550000000011111111110055555555555555111100000000011111000000005555511111106660044444444\n"
	   +"0555511115555555500001111111000000005555555555500011110000000000000000000555555111116666666666666669\n"
	   +"0555111111111555555555555555555555555551555555555511111555555555555555055555000001111111666544444444\n"
	   +"0555111155555555555555555555555555555551155555555555555555555555555555555555555551111111666554444444\n"
	   +"0555111155555555555555555555555555555555555555555555555555555555555555555555555551111100666555444444\n"
	   +"0555551111155555555555555555555555555555555555555555551111115555555555555555555501111111166555550444\n"
	   +"0555111111555555555555551111155555555550005555555555511111155555555550000000000011111111666555500000\n"
	   +"0555111111555555555555511111115555555550011555555555551111155555555500000000000000001111555550000000\n"
	   +"0555111111155555555551111111111555555555555555555055555555555555555000000000000000000005555555000000\n"
	   +"0551111555555555555555111111115555555555555555555555555555555551111100000000000000000005555555500000\n"
	   +"0551111155555555555555511111115555555551155555155555555555555555111111555555555555555555555000000000\n"
	   +"0551111155555555555555555511155555555555555555555555555555555555555555555555555555555555555555000000\n"
	   +"0555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555500000\n"
	   +"0555555555555555555555555555555555555555551155555555555555555555555555555555555555555555511115000000\n"
	   +"0555555555555555555555555155555555555555555155555555555555511555555555555555555555555555555111110000\n"
	   +"0555555555555555555555555555555555555555555555555550555555111110000000000000000000000000000000000000\n"
	   +"0555555555111111115555555555555555555555555555555555555551111100000000000000000000000000000000000000\n"
	   +"0000000000011111111100000000000000000000000000000000000001111100000000000000000000000000000000000000\n"
		;
		 
		stringToMap(map1 , true);
	
	}
	//makes map 2
	private void makeMap2(){
		String map2=
		"55555555555555555555555559500000005000000005555555\n"
	   +"50000000000000000000000055500000005055555000000550\n"
	   +"50555555555550000000500555555555555550005055555550\n"
	   +"50000055555555555000500500000500000050000050000050\n"
	   +"50005000005000000000500500555555500050000050000055\n"
	   +"50055000055555555555500500000000005555555555555555\n"
	   +"50050000055000000500000005555555555000000500000050\n"
	   +"50055555555005555555555555000000055000055500055555\n"
	   +"50000000055000000000000000000000000000000000000000\n"
	   +"50555000055555555555555555555555555555555555555550\n"
	   +"50005555555555555555555555555555555555555555555550\n"
	   +"50005000000000000000000000000055000000000000000550\n"
	   +"55005555500000000000000000000005005555555555555550\n"
	   +"50000000505555555555555555555505005555555555555550\n"
	   +"50555550505555555555555555555505505500000000000500\n"
	   +"50000050505000000000050000005500505500005555000505\n"
	   +"50000050505555555000050000005500505500555005000505\n"
	   +"50000050000000055000055550005500505500000005000505\n"
	   +"50555555555555050005550055005500505555555555000505\n"
	   +"50500000000000050000000005005505505500000005000505\n"
	   +"50500555555555555555555505005505005500000000000505\n"
	   +"50500500000000000000005005505505005555555555550505\n"
	   +"50500500055555555500000005005505005555555555550005\n"
	   +"50500500050000000005555555005505500005000000050055\n"
	   +"50505000050000055555000500055000555505500000050050\n"
	   +"50555000050000000000000000005500000000000000050050\n"
	   +"55585555555555555555555555555555555555555500050555\n"
	   +"00555000000050000005500000000050000005000000050005\n"
	   +"00500000000050000005555550000050000005000000050005\n"
	   +"00500000000050000000000000555550000000555555550555\n"
	   +"00500005555550555555555555500000000000000000050005\n"
	   +"00500000005500500500500000500000000000000000050005\n"
	   +"00500000005500500500550000555555555555555555550005\n"
	   +"00555555505500555500055550000500000000000000000005\n"
	   +"00500050000000500000000050000500000505555500000005\n"
	   +"05500055555550555555555055550500000505000000000005\n"
	   +"05000000000000550000005000050555555505555555555555\n"
	   +"05555550005555550000005000000000000000000000000005\n"
	   +"00000050000000050000005555555555555555555555555505\n"
	   +"00000050000000055555005000050000000000005500000505\n"
	   +"00555555555555000005005550050000005555000555055505\n"
	   +"00500500000005000005000550055555555005500000000505\n"
	   +"00500000555555000000050550000050000000000000005505\n"
	   +"00555005500000005555050055555055000000555555555005\n"
	   +"00000055000000000005000000000005500000050000000005\n"
	   +"00000550000000000005555555555555000000050000000055\n"
	   +"00000500000000000000000000000500000555550005555550\n"
	   +"00055555555555555555555550000500000000050005000000\n"
	   +"05550000005000000000000050000000000000055555555000\n"
	   +"55050000005555555550000055555555555555550000005555\n"
		;
		
		stringToMap(map2 , false);
	}
	//makes map 3
	private void makeMap3(){
		String map3=
		"55555555555766866755555555555\n"
	   +"55550005555766666755550005555\n"
	   +"55550005555766666755550005555\n"
	   +"55550005555766666755550005555\n"
	   +"55555555555766666755555555555\n"
	   +"55555555555766666755555555555\n"
	   +"55555555555766666755555555555\n"
	   +"55550005555766666755550005555\n"
	   +"55550005555766666755550005555\n"
	   +"55550005555766666755550005555\n"
	   +"55555555555766666755555555555\n"
	   +"55555555555766666755555555555\n"
	   +"55555555555566666555555555555\n"
	   +"55550005555766666755550005555\n"
	   +"55550005555766666755550005555\n"
	   +"55550005555766666755550005555\n"
	   +"55555555555766666755555555555\n"
	   +"55555555555766666755555555555\n"
	   +"55555555555766666755555555555\n"
	   +"55550005555766666755550005555\n"
	   +"55550005555766666755550005555\n"
	   +"55550005555766666755550005555\n"
	   +"55555555555766666755555555555\n"
	   +"55555555555766666755555555555\n"
	   +"55555555555766666755555555555\n"
	   +"55550005555766666755550005555\n"
	   +"55550005555766666755550005555\n"
	   +"55550005555766666755550005555\n"
	   +"55555555555766666755555555555\n"
	   +"55555555555000900055555555555\n"
		;
		
		stringToMap(map3 , false);	
	}
	//makes map 4
	private void makeMap4(){
			String map4=
		"00003330000\n"
	   +"57663636675\n"
	   +"57666666675\n"
	   +"57666666675\n"
	   +"57666666675\n"
	   +"57666666675\n"
	   +"57666666675\n"
	   +"57666666675\n"
	   +"55766666755\n"
	   +"05576867550\n"
		;
	
		stringToMap(map4 , false);	
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
