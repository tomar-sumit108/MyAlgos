package myalgos;

import InputReader.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayList;

public class TopologicalSortProgramming {

    ArrayList<Integer>[] list;
    ArrayList<Integer> ans;
    boolean[] vis;
    void topsort(int v)
    {
        if(vis[v])
            return ;
        vis[v]=true;
        for(int temp:list[v])
        {
            topsort(temp);
        }
        ans.add(v);
    }
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n=in.nextInt();
        int m=in.nextInt();
        list=new ArrayList[n];
        vis=new boolean[n];
        ans=new ArrayList<>();
        for(int i=0;i<n;i++)
            list[i]=new ArrayList<>();
        for(int i=0;i<m;i++) {
            list[in.nextInt()-1].add(in.nextInt()-1);
        }
        for(int i=0;i<n;i++)
        {
            if(!vis[i])
            {
                topsort(i);
            }
        }
        out.printLine(ans);


    }
}
