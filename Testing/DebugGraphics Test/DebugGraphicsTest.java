public class DebugGraphicsTest
{
	public static void main(String[] args)
	{
		int[][] mapGrid = new int [20][20];
		for(int i = 0; i < 20; i++)
		{
			for(int j = 0; j < 20; j++)
			{
				if(j == 0 || j == 19 || i == 0 || i == 19)
				{
					mapGrid[i][j] = 0;
				}
				else
					mapGrid[i][j] = 1;
			}
		}
		DebugGraphics.printmap(20, 20, mapGrid);
		// END Print Map Test
		DebugGraphics.printall(20, 20, mapGrid);
	}



}