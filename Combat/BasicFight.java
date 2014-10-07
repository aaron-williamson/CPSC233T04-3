import java.util.Scanner;
import java.util.List;

public class BasicFight
{
	public static void main(String[] args)
	{
		// Declare scanner
		Scanner input = new Scanner(System.in);

		// Ask for player name
		System.out.print("Hello! What is your name? ");

		// Add players and add them to player list
		Bandit enemy = new Bandit();
		Warrior user = new Warrior();



		user.name = input.nextLine();



		System.out.println("Prepare yourself, " + user.name + "! You will be facing a " + enemy.name + "!");

		System.out.println(user.STR + " " + user.HP + " " + user.critChance);
		System.out.println(enemy.STR + " " + enemy.HP + " " + enemy.critChance);
	}
}