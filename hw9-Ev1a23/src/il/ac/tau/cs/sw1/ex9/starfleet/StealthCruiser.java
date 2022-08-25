package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StealthCruiser extends myBattleShip {
	public static int NUM_STEALTH_CRUISER = 0;
	
	public StealthCruiser(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
		this.firePower = this.calc_fire_power();
		NUM_STEALTH_CRUISER +=1;
	}

	public StealthCruiser(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers){
		super(name, commissionYear, maximalSpeed, crewMembers, new ArrayList<Weapon>());
		Weapon w = new Weapon("Laser Cannons", 10, 100);
		List<Weapon> lst = new ArrayList<>();
		lst.add(w);
		this.weaponList = lst;
		this.firePower = this.calc_fire_power();
		NUM_STEALTH_CRUISER +=1;
	}

	@Override
	public int getAnnualMaintenanceCost() {
		int sum = 2500;
		sum += super.calc_weapon_meintenance();
		sum += (int)(1000*this.getMaximalSpeed());
		sum += 50 * NUM_STEALTH_CRUISER;
		return sum;
	}

	@Override
	public String toString() {
		String out = "StealthCruiser";
		out += super.toString();
		return out;
	}
}
