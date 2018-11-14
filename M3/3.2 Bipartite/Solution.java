


/*************************************************************************
 *  Compilation:  javac Bag.java
 *  Execution:    java Bag < input.txt
 *
 *  A generic bag or multiset, implemented using a linked list.
 *
 *************************************************************************/
import java.util.*; 
import java.lang.*; 
import java.io.*;

class Graph 
{ 
   public int V; 
	int [][]matrix; 
    // Array  of lists for Adjacency List Representation 
   public  LinkedList<Integer> adj[]; 
  
    // Constructor 
    Graph(int v) 
    { 
        V = v; 
        adj = new LinkedList[v];
        matrix=new int[v][v];
        for (int i=0; i<v; ++i) 
            adj[i] = new LinkedList(); 
    } 
  
    //Function to add an edge into the graph 
    void addEdge(int v, int w) 
    { 
        adj[v].add(w); 
        if (v != w) {
	            if (!hasEdge(v, w)) {
	                matrix[v][w] = 1;
	                matrix[w][v] = 1;
	            }
	        } else {
	            return;
	        }
    } 
    public boolean hasEdge(int v,int w) {
    	if(matrix[v][w]==1) {
    		return true;
    	}
    	else
    		return false;
    }
  
    void DFSUtil(int v,boolean visited[]) 
    { 
         
        visited[v] = true; 
        System.out.print(v+" ");  
        Iterator<Integer> i = adj[v].listIterator(); 
        while (i.hasNext()) 
        { 
            int n = i.next(); 
            if (!visited[n]) 
                DFSUtil(n, visited); 
        } 
    } 
  
    // The function to do DFS traversal. It uses recursive DFSUtil() 
    void DFS(int v) 
    { 
        // Mark all the vertices as not visited(set as 
        // false by default in java) 
        boolean visited[] = new boolean[V]; 
  
        // Call the recursive helper function to print DFS traversal 
        DFSUtil(v, visited); 
    } 
    public void output()
	    {
	    	int count=0;
	    	
	    	for(int i=0;i<V;i++)
	    	{
	    		for(int j=0;j<V;j++)
	    		{
	    			if(matrix[i][j]==0) {
	    					count++;	
	    			}
	    		}
	    	}
	    	if(count!=matrix.length)
	    	{
	    		int i=0; 
	    	for(i=0;i<V-1;i++)
	    	{
	    		int j=0;
	    		for(;j<V;j++)
	    		{
	    			System.out.print(matrix[i][j]+" ");
	    		}
	    		//System.out.print(matrix[i][j]);
	    		System.out.println();
	    	}
	    	int k=0;
	    	for(k=0;k<V-1;k++)
 		{
 			System.out.print(matrix[i][k]+" ");
 		}
 		System.out.print(matrix[i][k]);
	    	}
	    	else
	    		System.out.println("No edges");
	    	
	    }
}
 public class Solution 
 { 
     // This function returns true if  
     // graph G[V][V] is Bipartite, else false 
     boolean isBipartite(int G[][],int src) 
     { 
         // Create a color array to store  
         // colors assigned to all veritces. 
         // Vertex number is used as index  
         // in this array. The value '-1' 
         // of colorArr[i] is used to indicate  
         // that no color is assigned 
         // to vertex 'i'. The value 1 is  
         // used to indicate first color 
         // is assigned and value 0 indicates  
         // second color is assigned. 
         int colorArr[] = new int[G.length]; 
         for (int i=0; i<G.length; ++i) 
             colorArr[i] = -1; 
   
         // Assign first color to source 
         colorArr[src] = 1; 
   
         // Create a queue (FIFO) of vertex numbers  
         // and enqueue source vertex for BFS traversal 
         LinkedList<Integer>q = new LinkedList<Integer>(); 
         q.add(src); 
   
         // Run while there are vertices in queue (Similar to BFS) 
         while (q.size() != 0) 
         { 
             // Dequeue a vertex from queue 
             int u = q.poll(); 
   
             // Return false if there is a self-loop  
             if (G[u][u] == 1) 
                 return false;  
   
             // Find all non-colored adjacent vertices 
             for (int v=0; v<G.length; ++v) 
             { 
                 // An edge from u to v exists  
                 // and destination v is not colored 
                 if (G[u][v]==1 && colorArr[v]==-1) 
                 { 
                     // Assign alternate color to this adjacent v of u 
                     colorArr[v] = 1-colorArr[u]; 
                     q.add(v); 
                 } 
   
                 // An edge from u to v exists and destination 
                 //  v is colored with same color as u 
                 else if (G[u][v]==1 && colorArr[v]==colorArr[u]) 
                     return false; 
             } 
         } 
         // If we reach here, then all adjacent vertices can 
         // be colored with alternate color 
         return true; 
     } 
   
     // Driver program to test above function 
     public static void main (String[] args) 
     { 
    	 Scanner scn=new Scanner(System.in);
    	 int n=Integer.parseInt(scn.nextLine());
    	 int m=Integer.parseInt(scn.nextLine());
    	Solution b = new Solution();
    	 Graph gp=new Graph(n);
    	 while(scn.hasNext()) {
    		 String s1=scn.nextLine();
    		 String s2[]=s1.split(" ");
    		 gp.addEdge(Integer.parseInt(s2[0]),Integer.parseInt(s2[1]));
    		 
    	 } 
    	 //System.out.println("hello");
         if (b.isBipartite(gp.matrix, 0)) 
        	 System.out.println("Graph is bipartite"); 
         else
        	 System.out.println("Graph is not a bipartite"); 
     } 
 } 

