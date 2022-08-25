package il.ac.tau.cs.sw1.ex9.starfleet;
import java.util.Set;
public abstract class myStarfleetShips implements Spaceship  {
    private String name;

    private int commissionYear;

    private float maximalSpeed;

    protected int firePower;

    private Set<? extends CrewMember> crewMembers;


    public myStarfleetShips(String name, int commissionYear, float maximalSpeed, Set<? extends CrewMember> crewMembers) {
        this.name = name;
        this.commissionYear = commissionYear;
        this.maximalSpeed = maximalSpeed;
        this.firePower = 10;
        this.crewMembers = crewMembers;

    }

    public String getName()
    {
        return this.name;
    }

    public int getCommissionYear()
    {
        return this.commissionYear;
    }

    public float getMaximalSpeed()
    {
        return this.maximalSpeed;
    }

    public int getFirePower()
    {
        return this.firePower;
    }

    public Set<? extends CrewMember> getCrewMembers() {
        return this.crewMembers;
    }

    public int getAnnualMaintenanceCost()
    {
        return 0;
    }

    @Override
    public String toString() {
        String out = "\n";
        out += "\tName="+this.getName()+"\n\t";
        out += "CommissionYear="+this.getCommissionYear()+"\n\t";
        out += "MaximalSpeed="+this.getMaximalSpeed()+"\n\t";
        out += "FirePower="+this.getFirePower()+"\n\t";
        out += "CrewMembers="+this.getCrewMembers().size()+"\n\t";
        out+= "AnnualMaintenanceCost="+this.getAnnualMaintenanceCost();
        return out;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 0;
        for(int i = 1; i<this.name.length(); i++)
        {
            result += i*prime*this.name.charAt(i);
        }
        return result;
    }
    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
        {
            return true;
        }
        if(obj == null)
        {
            return false;
        }
        if(this.getClass() != obj.getClass())
        {
            return false;
        }
        if(this.getName() != ((myStarfleetShips) obj).getName())
        {
            return false;
        }
        return true;
    }
}
