package myalgos;

public class Bit {
      public static int BitCount(int n)
      {
            return n==0?0:(1+BitCount(n&(n-1)));
      }
}
