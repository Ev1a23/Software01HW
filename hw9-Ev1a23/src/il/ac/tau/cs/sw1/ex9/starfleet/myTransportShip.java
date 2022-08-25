package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Set;

public abstract class myTransportShip extends myStarfleetShips{
    private int cargoCapacity;
    private int passengerCapacity;
    protected static int BASIC = 3000;
    protected static int PRICE_MEGATON = 5;
    protected static int PRICE_PASSENGER = 3;

    public myTransportShip(String name, int commissionYear, float maximalSpeed, Set<? extends CrewMember> crewMembers, int cargoCapacity, int passengerCapacity) {
        super(name, commissionYear, maximalSpeed, crewMembers);
        this.cargoCapacity = cargoCapacity;
        this.passengerCapacity = passengerCapacity;
        this.firePower = 10;
    }

    public int getCargoCapacity()
    {
        return this.cargoCapacity;
    }

    public int getPassengerCapacity()
    {
        return this.passengerCapacity;
    }

    public int getAnnualMaintenanceCost()
    {
        return BASIC+(PRICE_MEGATON*this.cargoCapacity)+(PRICE_PASSENGER*this.passengerCapacity);
    }

    @Override
    public String toString() {
        String out = "TransportShip";
        out += super.toString();
        out += "\n\tCargoCapacity="+this.cargoCapacity+"\n\t";
        out += "PassengerCapacity="+this.passengerCapacity;
        return out;
    }
}
