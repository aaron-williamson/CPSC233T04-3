public class DebugGraphics {
	
	static String[] Mapgraphics = {"X" , " "};

	// For Testing:

	static int[] xpos = {17, 13, 1};
	static int[] ypos = {18, 5, 15};
	static String[] graphic = {"a", "b", "c"};
	
	public static void printmap(int mapheight, int mapwidth, int[][] mapGrid){
		for(int i = 0; i < mapheight; i++){
			for(int j = 0; j < mapwidth; j++){
					System.out.print(Mapgraphics[mapGrid[i][j]]);
				
			if(j >= mapwidth - 1){
					System.out.print("\n");
			}
			}
		}		
	} 
	
	public static void printall(int mapheight, int mapwidth, int[][] mapGrid){
		String[][] printbuffer=new String[mapheight][mapwidth];
		
		//add the map to the print buffer
		for(int i = 0; i < mapheight; i++){
			for(int j = 0; j < mapwidth; j++){
					printbuffer[i][j]=Mapgraphics[mapGrid[i][j]];
			}
		}	
		
		//add the entities to the print buffer, overwriting the map graphics
		//Entity[] entsarray=Ents.getAll();

		for(int i=0;i</*entsarray.length*/3;i++){
			//Entity ent=entsarray[i];
			printbuffer[xpos[i]][ypos[i]]=graphic[i];
		}
		
		//super cheap way to clear screen for demo
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		
		//draw the whole thing
		for(int i = 0; i < mapheight; i++){
			for(int j = 0; j < mapwidth; j++){
				System.out.print(printbuffer[i][j]);
			}
			System.out.print("\n");
		}	
		
	}

}
