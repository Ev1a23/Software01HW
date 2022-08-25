package il.ac.tau.cs.sw1.ex9.riddles.third;

public class B3 extends A3 {
    private static String s;
    public B3(String s)
    {
        super(s+"bye");
        B3.s = s;
    }

    public String getMessage()
    {
        return B3.s;
    }

    public void foo(String s) throws Exception {
        if (s.equals(this.s)) {
            throw new B3(B3.s);
        }

    }
}