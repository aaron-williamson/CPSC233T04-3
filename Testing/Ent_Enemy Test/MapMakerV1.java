public class MapMakerV1 {
	
	static String[] debuggraphics = {"X"," "};
	static int mapwidth = 15;
	static int mapheight = 11;
	int[][] mapGrid = new int [mapheight][mapwidth];
	
	void makeblankmap(){
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
	void makerowmap(){
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
	
	void drawdebug(){
		for (int i = 0; i < mapheight ; i++){
			for(int j = 0; j < mapwidth ; j++){
				System.out.print(debuggraphics[mapGrid[i][j]]);
				if(j >= 10){
					System.out.print("\n");
				}
			}
		}

	}
	boolean isPassable(int x,int y){
		return mapGrid[y][x]==1;
	}

	
	/*public static void main (String[] args){
		MapMakerV1 RPGmap = new MapMakerV1();
		RPGmap.makerowmap();
		
		RPGmap.drawdebug();
	
	
	
	}*/
}
