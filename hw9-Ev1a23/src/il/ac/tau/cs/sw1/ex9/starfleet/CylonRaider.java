package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class CylonRaider extends myBattleShip{

	public CylonRaider(String name, int commissionYear, float maximalSpeed, Set<Cylon> crewMembers,
			List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
	}

	@Override
	public int getAnnualMaintenanceCost() {
		int sum = 3500;
		sum += super.calc_weapon_meintenance();
		sum += 500 * this.getCrewMembers().size();
		sum += (int)(1200 * this.getMaximalSpeed());
		return sum;
	}

	@Override
	public String toString() {
		String out = "CylonRaider";
		out += super.toString();
		return out;
	}
}
