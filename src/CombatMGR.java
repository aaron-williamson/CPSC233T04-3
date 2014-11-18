import java.util.ArrayList;

public class CombatMGR {
	private static ArrayList<EntityCombat> combatEnts = new ArrayList<EntityCombat>();

	public static void addCombat (EntityCombat ent) {
		combatEnts.add(ent);
	}

	public static EntityCombat[] getAll() {
		EntityCombat[] combatEntsArray = new EntityCombat[combatEnts.size()];
		int i = 0;
		for (EntityCombat combatEnt : combatEnts) {
			combatEntsArray[i] = combatEnt;
			++i;
		}

		return combatEntsArray;
	}
}