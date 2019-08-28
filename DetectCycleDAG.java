package myalgos;

import InputReader.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayList;

public class DetectCycleDAG {

    ArrayList<Integer>[] list;
    boolean[] vis;
    boolean[] recStack;

    boolean detect(int u)
    {
        if(recStack[u])
            return true;
        if(vis[u])
            return false;
        vis[u]=true;
        recStack[u]=true;
        for(int temp:list[u])
        {
            if(detect(temp))
                return true;
        }
        recStack[u]=false;

        return false;
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int v=in.nextInt();
        int m=in.nextInt();
        list=new ArrayList[v];
        vis=new boolean[v];
        recStack=new boolean[v];
        for(int i=0;i<v;i++)
        {
            list[i]=new ArrayList<>();
        }
        for(int i=0;i<m;i++)
        {
            int u=in.nextInt()-1;
            int vv=in.nextInt()-1;
            list[u].add(vv);
        }

        boolean ans=false;
        for(int i=0;i<v;i++)
        {
            if(!vis[i])
                if(detect(i))
                    ans=true;
        }
        out.printLine(ans);

    }
}
