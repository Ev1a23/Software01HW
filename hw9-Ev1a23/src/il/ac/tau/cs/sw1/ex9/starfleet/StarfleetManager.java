package il.ac.tau.cs.sw1.ex9.starfleet;


import java.util.*;


public class StarfleetManager {

	/**
	 * Returns a list containing string representation of all fleet ships, sorted in descending order by
	 * fire power, and then in descending order by commission year, and finally in ascending order by
	 * name
	 */
	public static List<String> getShipDescriptionsSortedByFirePowerAndCommissionYear (Collection<Spaceship> fleet) {
			class C1 implements Comparator<Spaceship>
			{

				@Override
				public int compare(Spaceship o1, Spaceship o2) {
					if(o1.getFirePower() == o2.getFirePower())
					{
						if(o1.getCommissionYear() == o2.getCommissionYear())
						{
							return o1.getName().compareTo(o2.getName());
						}
						else
						{
							if(o1.getCommissionYear() < o2.getCommissionYear())
							{
								return 1;
							}
							return -1;
						}
					}
					else
					{
						if(o1.getFirePower() < o2.getFirePower())
						{
							return 1;
						}
						return -1;
					}
				}
			}
			ArrayList<Spaceship> lst = new ArrayList<>();
			for(Spaceship s: fleet)
			{
				lst.add(s);
			}
			C1 comp = new C1();
			Collections.sort(lst,comp);
			List<String> out = new ArrayList<>();
			for(Spaceship s: lst)
			{
				out.add(s.toString());
			}
			return out;
	}

	/**
	 * Returns a map containing ship type names as keys (the class name) and the number of instances created for each type as values
	 */
	public static Map<String, Integer> getInstanceNumberPerClass(Collection<Spaceship> fleet)
	{
		HashMap<String, Integer> map = new HashMap<>();
		for(Spaceship s: fleet)
		{
			if(map.containsKey(String.valueOf(s.getClass().getSimpleName())))
			{
				map.replace(String.valueOf(s.getClass().getSimpleName()),map.get(String.valueOf(s.getClass().getSimpleName()))+1);
			}
			else
			{
				map.put(String.valueOf(s.getClass().getSimpleName()),1);
			}
		}
		return map;

	}


	/**
	 * Returns the total annual maintenance cost of the fleet (which is the sum of maintenance costs of all the fleet's ships)
	 */
	public static int getTotalMaintenanceCost (Collection<Spaceship> fleet) {
		int sum = 0;
		for(Spaceship s: fleet)
		{
			sum+= s.getAnnualMaintenanceCost();
		}
		return sum;

	}


	/**
	 * Returns a set containing the names of all the fleet's weapons installed on any ship
	 */
	public static Set<String> getFleetWeaponNames(Collection<Spaceship> fleet) {
		Set<String> set = new HashSet<>();
		for(Spaceship s: fleet)
		{

			if(!s.getClass().toString().equals("class il.ac.tau.cs.sw1.ex9.starfleet.TransportShip"))
			{
				myBattleShip ship = (myBattleShip)s;
				for(Weapon w: ship.getWeapon())
				{
					set.add(w.getName());
				}
			}
		}
		return set;

	}

	/*
	 * Returns the total number of crew-members serving on board of the given fleet's ships.
	 */
	public static int getTotalNumberOfFleetCrewMembers(Collection<Spaceship> fleet) {
		int sum = 0;
		for(Spaceship s: fleet)
		{
			sum += s.getCrewMembers().size();
		}
		return sum;

	}

	/*
	 * Returns the average age of all officers serving on board of the given fleet's ships. 
	 */
	public static float getAverageAgeOfFleetOfficers(Collection<Spaceship> fleet) {
		float sum = 0;
		float cnt = 0;
		for(Spaceship s: fleet)
		{
			for(CrewMember c: s.getCrewMembers())
			{
				if(c.getClass().toString().equals("class il.ac.tau.cs.sw1.ex9.starfleet.Officer"))
				{
					sum += c.getAge();
					cnt +=1;
				}
			}
		}
		return sum/cnt;
	}

	/*
	 * Returns a map mapping the highest ranking officer on each ship (as keys), to his ship (as values).
	 */
	public static Map<Officer, Spaceship> getHighestRankingOfficerPerShip(Collection<Spaceship> fleet) {
		class C2 implements Comparator<Officer>
		{

			@Override
			public int compare(Officer o1, Officer o2) {
				return  -o1.getRank().compareTo(o2.getRank());
			}
		}

		HashMap <Officer, Spaceship> map = new HashMap<>();
		for(Spaceship s: fleet)
		{
			ArrayList<Officer> lst = new ArrayList<>();
			for(CrewMember c: s.getCrewMembers())
			{
				if(c.getClass().toString().equals("class il.ac.tau.cs.sw1.ex9.starfleet.Officer"))
				{
					lst.add((Officer)c);
				}
			}
			if(lst.size()>0)
			{
				C2 comp = new C2();
				Collections.sort(lst, comp);
				map.put(lst.get(0), s);
			}
		}

		return map;

	}

	/*
	 * Returns a List of entries representing ranks and their occurrences.
	 * Each entry represents a pair composed of an officer rank, and the number of its occurrences among starfleet personnel.
	 * The returned list is sorted ascendingly based on the number of occurrences.
	 */
	public static List<Map.Entry<OfficerRank, Integer>> getOfficerRanksSortedByPopularity(Collection<Spaceship> fleet) {
		class C3 implements Comparator<Map.Entry<OfficerRank,Integer>>
		{

			@Override
			public int compare(Map.Entry<OfficerRank,Integer> o1, Map.Entry<OfficerRank,Integer> o2) {
				if(o1.getValue() == o2.getValue())
				{
					return -o1.getKey().compareTo(o2.getKey());
				}
				else
				{
					if(o1.getValue()< o2.getValue())
					{
						return 1;
					}
					else
					{
						return -1;
					}
				}

			}
		}

		List<Map.Entry<OfficerRank, Integer>> lst = new ArrayList<>();
		for(Spaceship s: fleet)
		{
			for(CrewMember c: s.getCrewMembers())
			{
				if(c.getClass().toString().equals("class il.ac.tau.cs.sw1.ex9.starfleet.Officer"))
				{
					Officer tmp = (Officer)c;
					boolean bool = false;
					for(Map.Entry<OfficerRank, Integer> entry: lst)
					{
						if(entry.getKey() == tmp.getRank())
						{
							bool = true;
							entry.setValue(entry.getValue()+1);
						}
					}
					if(!bool)
					{
						lst.add(new AbstractMap.SimpleEntry<OfficerRank, Integer>(tmp.getRank(),1));
					}
				}
			}
		}
		C3 comp = new C3();
		Collections.sort(lst, comp.reversed());
		return lst;

	}

}
