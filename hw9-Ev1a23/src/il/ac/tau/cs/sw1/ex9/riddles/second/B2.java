package il.ac.tau.cs.sw1.ex9.riddles.second;

public class B2 extends A2 {
    private static boolean bool;
    public A2 getA(Boolean b)
    {
        B2.bool = b;
        return new B2();
    }


    public String foo(String s) {
        if(B2.bool)
        {
            return s.toUpperCase();
        }
        else
        {
            return s.toLowerCase();
        }
    }
}
