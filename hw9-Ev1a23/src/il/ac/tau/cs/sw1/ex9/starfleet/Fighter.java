package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class Fighter extends myBattleShip {
	
	
	public Fighter(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, List<Weapon> weapons){
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);

	}

	@Override
	public int getAnnualMaintenanceCost() {
		int sum = 2500;
		sum += super.calc_weapon_meintenance();
		sum += (int)(1000*this.getMaximalSpeed());
		return sum;
	}

	@Override
	public String toString() {
		String out = "Fighter";
		out += super.toString();
		return out;
	}
}
