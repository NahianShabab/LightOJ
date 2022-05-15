package Drunk_1063;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int t=scanner.nextInt();
        for(int i=1;i<=t;i++){
            int m=scanner.nextInt();
            HashMap<String,Integer> vertexMap=new HashMap<String,Integer>();
            int vertices=0;
            Graph g=new Graph();
            DetectCycle dc=new DetectCycle();
            for(int j=1;j<=m;j++){
                String vertex1=scanner.next();
                String vertex2=scanner.next();
                if(vertexMap.containsKey(vertex1)==false){
                    vertexMap.put(vertex1,vertices++);
                }
                if(vertexMap.containsKey(vertex2)==false){
                    vertexMap.put(vertex2,vertices++);
                }
                int v1=vertexMap.get(vertex1),v2=vertexMap.get(vertex2);
                if(v1>=g.adjacencyLists.size()){
                    g.adjacencyLists.add(new ArrayList<>());
                }
                if(v2>=g.adjacencyLists.size()){
                    g.adjacencyLists.add(new ArrayList<>());
                }
                g.adjacencyLists.get(v2).add(v1);
            }
            // //print the graph
            // for(int k=0;k<g.adjacencyLists.size();k++){
            //     System.out.print(k+": ");
            //     for(var l:g.adjacencyLists.get(k)){
            //         System.out.print(l+" ");
            //     }
            //     System.out.println();
            // }
            boolean containsCycle=dc.hasCycle(g);
            System.out.println("Case "+i+": "+(containsCycle==true?"No":"Yes"));
        }
        scanner.close();

    }
}

class Graph{
    public List<List<Integer>> adjacencyLists=new ArrayList<List<Integer>>();
}

class DetectCycle{
    int vertices;
    int color[]=null;
    Graph graph=null;
    boolean containsCycle;
    public boolean hasCycle(Graph g){
        this.graph=g;
        containsCycle=false;
        vertices=g.adjacencyLists.size();
        // boolean visited[]=new boolean[vertices];
        // for(int i=0;i<vertices;i++)
        //     visited[i]=false;
        color=new int[vertices];
        for(int i=0;i<vertices;i++)
            color[i]=0;
        for(int i=0;i<vertices;i++){
            if(color[i]==0){
                DFS(i);
            }
        }
        return containsCycle; 
    }
    private void DFS(int u){
        System.out.println("here");
        color[u]=1;
        for (var i:graph.adjacencyLists.get(u)){
            if(color[i]==1){
                containsCycle=true;
            }
            if(color[i]==0){
                DFS(i);
            }
        }
        color[u]=2;
    }
}