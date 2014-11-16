public class DebugGraphics {
	
	static String[] Mapgraphics = {"X" , " "};
	
	public static void printmap(RPGMap rpgmap){
		for(int i = 0; i < rpgmap.mapheight; i++){
			for(int j = 0; j < rpgmap.mapwidth; j++){
					System.out.print(Mapgraphics[rpgmap.mapGrid[i][j]]);
				
			if(j >= rpgmap.mapwidth - 1){
					System.out.print("\n");
			}
			}
		}		
	} 
	
	public static void printall(RPGMap rpgmap){
		String[][] printbuffer=new String[rpgmap.mapheight][rpgmap.mapwidth];
		
		//add the map to the print buffer
		for(int i = 0; i < rpgmap.mapheight; i++){
			for(int j = 0; j < rpgmap.mapwidth; j++){
					printbuffer[i][j]=Mapgraphics[rpgmap.mapGrid[i][j]];
			}
		}	
		
		//add the entities to the print buffer, overwriting the map graphics
		Entity[] entityArray=Game.getGame().getEntities().getAll();

		for(int i=0;i<entityArray.length;i++){
			Entity ent=entityArray[i];
			printbuffer[ent.getY()][ent.getX()]=ent.debuggraphic();
		}
		
		//super cheap way to clear screen for demo
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		
		//draw the whole thing
		for(int i = 0; i < rpgmap.mapheight; i++){
			for(int j = 0; j < rpgmap.mapwidth; j++){
				System.out.print(printbuffer[i][j]);
			}
			System.out.print("\n");
		}	
		
	}

}
