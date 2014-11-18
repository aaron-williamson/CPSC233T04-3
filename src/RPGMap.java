public class RPGMap {
	
	static String[] debuggraphics = {"X"," "};
	static public int mapwidth = 32;
	static public int mapheight = 11;
	
	public int[][] mapGrid = new int [mapheight][mapwidth];
	
	RPGMap(){
		makeblankmap();
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
	
	/**
	 * Returns true if a position on the map is passable or not
	 * @param x coordinate
	 * @param y coordinate
	 * @return boolean true if position on map is passable
	 */
	public boolean isPassable(int x,int y){
		return mapGrid[y][x]==1;
	}
}
