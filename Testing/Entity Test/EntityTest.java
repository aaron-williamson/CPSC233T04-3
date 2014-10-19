public class EntityTest
{
	public static void main(String[] args)
	{
		Entity testEnt = new Entity();
		System.out.println("Class: " + testEnt.getClassID());
		if (testEnt.isPassable())
			System.out.println(testEnt.getClassID() + " is passable.");
		else
			System.out.println(testEnt.getClassID() + " is not passable.");

		System.out.println("Is represented by: " + testEnt.debuggraphic());

		testEnt.setPos(5,4);

		System.out.println("x-position is: " + testEnt.xpos);

		System.out.println("y-position is: " + testEnt.ypos);

		Entity test2 = new Entity();

		test2.setPos(10,20);

		System.out.printf("Distance is: " +"%.2f", testEnt.distance(test2));
		System.out.println();
	}
}