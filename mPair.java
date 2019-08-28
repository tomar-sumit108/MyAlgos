package myalgos;

public class mPair<U, V> implements Comparable<mPair<U,V>> {
    public  U first;
    public  V second;


    public static <U,V> mPair<U,V> makePair(U first,V second)  {
        return new mPair(first, second);
    }
    private mPair(U first,V second)
    {
        this.first=first;
        this.second=second;
    }

    @Override
    public int compareTo(mPair<U,V> o) {
        int value = ((Comparable<U>)first).compareTo(o.first);
        if (value != 0) {
            return value;
        }
        return ((Comparable<V>) second).compareTo(o.second);
    }

}

