package myalgos;

import InputReader.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;

public class Kruskak {



    class Edge implements Comparable<Edge>{
        int u;
        int v;
        int w;
        Edge(int u,int v,int w)
        {
            this.v=v;
            this.u=u;
            this.w=w;
        }

        @Override
        public int compareTo(Edge edge) {
            return Integer.compare(this.w,edge.w);
        }
    }

    class Subset{
        int parent;
        int rank;
    }

    Edge[] arr;
    Subset[] set;


    int find(int x)
    {
        if(set[x].parent!=x)
        {
            set[x].parent=find(set[x].parent);
        }
        return set[x].parent;
    }

    void union(int x,int y)
    {
        int xroot=find(x);
        int yroot=find(y);

        if(set[xroot].rank<set[yroot].rank)
        {
            set[xroot].parent=yroot;
        }
        else if(set[xroot].rank>set[yroot].rank)
        {
            set[yroot].parent=xroot;
        }
        else {
            set[xroot].parent=yroot;
            set[yroot].rank++;
        }
    }
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n=in.nextInt();
        int m=in.nextInt();
        arr=new Edge[m];
        for(int i=0;i<m;i++)
        {
            int u=in.nextInt()-1;
            int v=in.nextInt()-1;
            int w=in.nextInt();
            arr[i]=new Edge(u,v,w);

        }
        Arrays.sort(arr);
        set=new Subset[n];
        for(int i=0;i<n;i++)
        {
            set[i]=new Subset();
            set[i].parent=i;
            set[i].rank=0;
        }
        Edge[] ans=new Edge[m];
        int index=0;
        for(int i=0;i<m;i++)
        {
            int x=find(arr[i].u);
            int y=find(arr[i].v);

            if(x!=y)
            {
                ans[index++]=arr[i];
                union(x,y);
            }
        }

        for(int i=0;i<index;i++)
        {
            out.printLine(ans[i].u+"  "+ans[i].v);
        }



    }
}
