package myalgos;

import InputReader.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayList;
import java.util.Stack;

import static java.lang.System.out;

public class StronglyConnectedComponentKosaraju {



    class Edge{
        int u;
        int v;
        Edge(int u,int v)
        {
            this.u=u;
            this.v=v;
        }
    }


    int n,m;
    Edge[] edge;
    ArrayList<Integer>[] list1;
    ArrayList<Integer>[] list2;

    void dfs(int u,boolean[] vis){
        if(vis[u])
            return;
        vis[u]=true;
        out.print(u+" ");
        for(int temp:list2[u])
        {
            dfs(temp,vis);
        }
    }

    void fill(int u, boolean[] vis, Stack s)
    {
        if(vis[u])
            return ;
        vis[u]=true;
        for(int temp:list1[u])
        {
            fill(temp,vis,s);
        }
        s.push(new Integer(u));
    }

    ArrayList<Integer>[] getList(boolean reverse)
    {
        ArrayList<Integer> list[]=new ArrayList[n];
        for(int i=0;i<n;i++)
            list[i]=new ArrayList<>();

        for(int i=0;i<m;i++)
        {
            if(reverse)
                list[edge[i].v].add(edge[i].u);
            else
                list[edge[i].u].add(edge[i].v);
        }
        return list;
    }

    void printSCC()
    {
        Stack stack=new Stack();
        boolean vis[]=new boolean[n];
        list1=getList(false);
        list2=getList(true);
        for(int i=0;i<n;i++) {
            if (!vis[i])
                fill(i,vis,stack);
        }

        vis=new boolean[n];


        while(!stack.empty())
        {
            int v=(int)stack.pop();
            if(!vis[v])
            {
                dfs(v,vis);
                out.println();
            }
        }

    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n=in.nextInt();
        m=in.nextInt();
        edge=new Edge[m];


        for(int i=0;i<n;i++)
        {
            edge[i]=new Edge(in.nextInt()-1,in.nextInt()-1);
        }
        printSCC();


    }
}
