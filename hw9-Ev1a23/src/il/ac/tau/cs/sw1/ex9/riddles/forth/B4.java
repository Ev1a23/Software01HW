package il.ac.tau.cs.sw1.ex9.riddles.forth;


import java.util.Arrays;
import java.util.Iterator;

public class B4 implements Iterator {
    private String[] strings;
    private int k;
    private int cur = 0;


    public B4(String[] strings, int k)
    {
        String[] lst = new String[strings.length *11];
        for(int i = 0; i< strings.length; i++)
        {
            for(int j = 0; j<10; j++)
            {
                lst[i+j* strings.length] = strings[i];
            }
        }
        this.k = k;
        this.strings = lst;

    }
    public String next()
    {
        String out = this.strings[cur];
        this.cur +=1;
        return out;
    }

    public boolean hasNext()
    {
        return this.cur <this.strings.length;
    }
	
}
