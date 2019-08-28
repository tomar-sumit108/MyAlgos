package myalgos;


public abstract class mSegmentTree<T>{
    public T[] seg;
    public int size;
    public int nn;


    private final int StartIndex=0;
    private final int EndIndex;

   public mSegmentTree (int n,T[] arr)
    {

        int temp=(int)Math.ceil((Math.log(n)/Math.log(2)));
        this.nn=(int)Math.pow(2,temp);
          EndIndex=nn-1;

        this.size=2*(nn)-1;
        this.seg=(T[])new Object[this.size];
        for(int i=0;i<nn;i++)
        {
            if(i<n)
                this.seg[i+nn-1]=arr[i];
            else
                this.seg[i+nn-1]=getNullVal();
        }
        for(int i=nn-2;i>=0;i--)
        {
            this.seg[i]=getParentVal(this.seg[2*i+1],this.seg[2*i+2]);
        }

    }

    protected abstract T getParentVal(T a, T b);
    protected abstract T getNullVal();
    protected abstract T getSum(T a,T b);

    public T Query(int qs,int qe)
    {
          return Query(qs,qe,0,nn-1,0);
    }
   public T Query(int qs,int qe,int ss,int se,int index)
    {
        if(qs<=ss&&qe>=se){
            return seg[index];
        }

        if(se<qs || ss>qe)
            return getNullVal();
        int mid=(ss+se)/2;
        return getParentVal(Query(qs,qe,ss,mid,2*index+1),Query(qs,qe,mid+1,se,2*index+2));
    }

    public void update(T val,int index,boolean delta)
    {
        updateTree(StartIndex,EndIndex,index,index,val,0,delta);
    }

    public void update(T val,int start,int end,boolean delta)
    {
        updateTree(StartIndex,EndIndex,start,end,val,0,delta);
    }


    void updateTree(int ss,int se,int us,int ue, T v,int i,boolean delta)
    {
        int mid=ss+(se-ss)/2;
        if(ue<ss||us>se || ss>se)
            return;

        if(ss==se){
            if(delta)
                seg[ss+nn-1]=getSum(seg[ss+nn-1],v);
            else
                seg[ss+nn-1]=v;
            return;
        }

        updateTree(ss,mid,us,ue,v,2*i+1,delta);
        updateTree(mid+1,se,us,ue,v,2*i+2,delta);
        seg[i]=getParentVal(seg[2*i+1],seg[2*i+2]);
    }



    public T getSeg(int a)
    {
        return seg[a];
    }
}
