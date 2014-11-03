import java.util.ArrayList;

public class CombatMGR {
	private static ArrayList<Ent_Combat> combatEnts = new ArrayList<Ent_Combat>();

	public static void addCombat (Ent_Combat ent) {
		combatEnts.add(ent);
	}

	public static Ent_Combat[] getAll() {
		Ent_Combat[] combatEntsArray = new Ent_Combat[combatEnts.size()];
		for (int i = 0; i < combatEnts.size(); ++i) {
			combatEntsArray[i] = combatEnts.get(i);
		}

		return combatEntsArray;
	}
}