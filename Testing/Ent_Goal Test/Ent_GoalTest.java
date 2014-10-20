public class Ent_GoalTest
{
	public static void main(String[] args)
	{
		Ent_Goal tester = new Ent_Goal();

		System.out.println("Entity Class ID: " + tester.getClassID());

		System.out.println("Entity Debug Graphic: " + tester.debuggraphic());

		System.out.println("On Collision: ");

		tester.onCollide();
	}
}