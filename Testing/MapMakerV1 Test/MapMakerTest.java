public class MapMakerTest
{
	public static void main(String[] args)
	{
		MapMakerV1 map = new MapMakerV1();
		map.makeblankmap();

		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				System.out.print(map.mapGrid[i][j]);
			}
			System.out.println();
		}

		MapMakerV1 map2 = new MapMakerV1();
		map2.makerowmap();

		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				System.out.print(map2.mapGrid[i][j]);
			}
			System.out.println();
		}
	}
}