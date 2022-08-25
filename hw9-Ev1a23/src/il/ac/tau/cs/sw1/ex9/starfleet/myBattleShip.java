package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public abstract class myBattleShip extends myStarfleetShips{

    protected List<Weapon> weaponList;
    public myBattleShip(String name, int commissionYear, float maximalSpeed, Set<? extends CrewMember> crewMembers, List<Weapon> weaponList) {
        super(name, commissionYear, maximalSpeed, crewMembers);
        this.weaponList = weaponList;
        this.firePower = calc_fire_power();
    }

    public List<Weapon> getWeapon()
    {
        return this.weaponList;
    }

    public int calc_fire_power()
    {
        int sum = 10;
        for (Weapon w: this.weaponList)
        {
            sum +=  w.getFirePower();
        }
        return sum;
    }

    public int calc_weapon_meintenance()
    {
        int sum = 0;
        for (Weapon w: this.weaponList)
        {
            sum += w.getAnnualMaintenanceCost();
        }
        return sum;
    }

    @Override
    public String toString() {
        String out = super.toString();
        out += "\n\tWeaponArray="+this.weaponList.toString();
        return out;
    }
}
