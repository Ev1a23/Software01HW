package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class Bomber extends myBattleShip{

	private int numberOfTechnicians;
	public Bomber(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, List<Weapon> weapons, int numberOfTechnicians)
	{
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
		this.numberOfTechnicians = numberOfTechnicians;
	}

	public int getNumberOfTechnicians()
	{
		return this.numberOfTechnicians;
	}

	@Override
	public int getAnnualMaintenanceCost()
	{
		int sum = 5000;
		int wm = super.calc_weapon_meintenance();
		double factor = 1-(0.1)*this.numberOfTechnicians;
		sum += (factor*wm);
		return sum;
	}

	@Override
	public String toString() {
		String out = "Bomber";
		out += super.toString();
		out += "\n\tNumberOfTechnicians="+this.numberOfTechnicians;
		return out;
	}
}
