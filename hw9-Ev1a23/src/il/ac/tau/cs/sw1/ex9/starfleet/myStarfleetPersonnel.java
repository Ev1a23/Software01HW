package il.ac.tau.cs.sw1.ex9.starfleet;

public abstract class myStarfleetPersonnel implements CrewMember{
    private String name;
    private int age;
    private int yearsInService;

    public myStarfleetPersonnel(int age, int yearsInService, String name)
    {
        super();
        this.name = name;
        this.age = age;
        this.yearsInService = yearsInService;
    }

    public String getName()
    {
        return this.name;
    }

    public int getAge()
    {
        return this.age;
    }

    public int getYearsInService()
    {
        return this.yearsInService;
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
        if(this.getName() != ((CrewWoman) obj).getName())
        {
            return false;
        }
        return true;
    }


}
