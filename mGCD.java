package myalgos;

public class mGCD {
    public static long GCD(long a, long b)
    {
        if(b==0)
        {
            return a;
        }
        return GCD(b,a%b);
    }

    public static int GCD(int a, int b)
    {
        if(b==0)
        {
            return a;
        }
        return GCD(b,a%b);
    }
}
