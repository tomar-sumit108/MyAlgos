package myalgos;

public class ModularInverse {
      public static long Inverse(long a,long mod)
      {
            long g= mGCD.GCD(a,mod);
            if(g!=1){
                  return -1;
                  }
            else
                  return power(a,mod-2,mod);
      }
      static long power(long x,long y,long m)
      {
            if(y==0)
                  return 1;
            long p=power(x,y/2,m)%m;
            p=(p*p)%m;
            if(y%2==0)
            {
                  return p;
            }
            else
                  return (x*p)%m;
      }

      public static long InverseCoPrime(long a,long m)
      {
            long m0=m;
            long y=0;
            long x=1;
            if(m==1)
                  return 0;
            while(a>1)
            {
                  long q=a/m;
                  long t=m;
                  m=a%m;
                  a=t;
                  t=y;
                  y=x-q*y;
                  x=t;
            }
            if(x<0)
                  x+=m0;
            return x;
      }


}
