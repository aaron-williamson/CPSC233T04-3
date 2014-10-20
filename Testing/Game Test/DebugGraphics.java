public class DebugGraphics {
	
	static String[] Mapgraphics = {"Û" , " "};
	
	public static void printmap(MapMakerV1 rpgmap){
		for(int i = 0; i < rpgmap.mapheight; i++){
			for(int j = 0; j < rpgmap.mapwidth; j++){
					System.out.print(Mapgraphics[rpgmap.mapGrid[i][j]]);
				
			if(j >= rpgmap.mapwidth - 1){
					System.out.print("\n");
			}
			}
		}		
	} 
	
	public static void printall(MapMakerV1 rpgmap){
		String[][] printbuffer=new String[rpgmap.mapheight][rpgmap.mapwidth];
		
		//add the map to the print buffer
		for(int i = 0; i < rpgmap.mapheight; i++){
			for(int j = 0; j < rpgmap.mapwidth; j++){
					printbuffer[i][j]=Mapgraphics[rpgmap.mapGrid[i][j]];
			}
		}	
		
		//add the entities to the print buffer, overwriting the map graphics
		Entity[] entsarray=Ents.getAll();

		for(int i=0;i<entsarray.length;i++){
			Entity ent=entsarray[i];
			printbuffer[ent.ypos][ent.xpos]=ent.debuggraphic();
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
