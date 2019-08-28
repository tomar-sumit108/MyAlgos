package myalgos;


public abstract class mSegmentTreeLazy<T>{
      public T[] seg;
      public int[] lazy;
      public int size;
      public int nn;


      private final int StartIndex=0;
      private final int EndIndex;

      public mSegmentTreeLazy (int n,T[] arr)
      {

            int temp=(int)Math.ceil((Math.log(n)/Math.log(2)));
            this.nn=(int)Math.pow(2,temp);
            EndIndex=nn-1;

            this.size=2*(nn)-1;
            this.seg=(T[])new Object[this.size];
            lazy=new int[this.size];
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
      protected abstract T getSumLazy(T a,int b);

      public T Query(int qs,int qe)
      {
            return Query(qs,qe,0,nn-1,0);
      }
      public T Query(int qs,int qe,int ss,int se,int index)
      {
            if(lazy[index]!=0){
                  seg[index]=getSumLazy(seg[index],(se-ss+1)*lazy[index]);
                  if(ss!=se)
                  {
                        lazy[index*2+1]+=lazy[index];
                        lazy[index*2+2]+=lazy[index];
                  }
                  lazy[index]=0;
            }

            if(qs<=ss&&qe>=se){
                  return seg[index];
            }

            if(se<qs || ss>qe)
                  return getNullVal();
            int mid=(ss+se)/2;
            return getParentVal(Query(qs,qe,ss,mid,2*index+1),Query(qs,qe,mid+1,se,2*index+2));
      }

      public void update(int val,int index,boolean delta)
      {
            updateTree(StartIndex,EndIndex,index,index,val,0,delta);
      }

      public void update(int val,int start,int end,boolean delta)
      {
            updateTreeLazy(StartIndex,EndIndex,start,end,val,0);
      }

      void updateTreeLazy(int ss,int se,int us,int ue,int v,int i)
      {
            if(lazy[i]!=0) {
                  seg[i] = getSumLazy(seg[i], (se - ss + 1) * lazy[i]);

                  if (ss != se) {
                        lazy[i * 2 + 1] += lazy[i];
                        lazy[i * 2 + 2] += lazy[i];
                  }
                  lazy[i] = 0;
            }
            if(ss>se||ss>ue||se<us)
                  return ;
            if(ss>=us&&se<=ue)
            {
                  seg[i]=getSumLazy(seg[i],(se-ss+1)*v);
                  if(ss!=se)
                  {
                        lazy[2*i+1]+=v;
                        lazy[2*i+2]+=v;
                  }
                  return;
            }
            int mid=(ss+se)/2;
            updateTreeLazy(ss,mid,us,ue,v,2*i+1);
            updateTreeLazy(mid+1,se,us,ue,v,2*i+2);
            seg[i]=getSum(seg[2*i+1],seg[2*i+2]);
      }


      void updateTree(int ss,int se,int us,int ue, int v,int i,boolean delta)
      {
            int mid=ss+(se-ss)/2;
            if(ue<ss||us>se || ss>se)
                  return;

            if(ss==se){
                  //if(delta)
                        seg[ss+nn-1]=getSumLazy(seg[ss+nn-1],v);
                  //else
                    //    seg[ss+nn-1]=v;
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
