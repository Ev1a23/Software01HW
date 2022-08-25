package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class ColonialViper extends myBattleShip  {

	public ColonialViper(String name, int commissionYear, float maximalSpeed, Set<CrewWoman> crewMembers,
			List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);

	}

	@Override
	public int getAnnualMaintenanceCost() {
		int sum = 4000;
		sum += this.calc_weapon_meintenance();
		sum += 500 * this.getCrewMembers().size();
		sum += (int)(500 * this.getMaximalSpeed());
		return sum;
	}

	@Override
	public String toString() {
		String out = "ColonialViper";
		out += super.toString();
		return out;
	}
}
