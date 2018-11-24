import java.util.Scanner;

public class Solution {
	public static void main(String[] args)
	{
		
		Scanner sc=new Scanner(System.in);
		int ver=Integer.parseInt(sc.nextLine());
		int edg=Integer.parseInt(sc.nextLine());
		Graph odj=new Graph(ver,edg);
		while(sc.hasNext())
		{
			for(int i=0;i<edg;i++)
			{
				String s=sc.nextLine();
				String b[]=s.split(" ");
				odj.addedge(Integer.parseInt(b[0]),Integer.parseInt(b[1]),Double.parseDouble(b[2]));
			}
			String type=sc.nextLine();
			switch(type)	
			{
				case "Graph":
				{
					odj.printGraph();
					break;
				}
				case "DirectedPaths":
				{
					Floyd F=new Floyd(ver,edg);
					String sorce=sc.nextLine();
					String a[]=sorce.split(" ");
					F.floydWarshall(odj.matrix,Integer.parseInt(a[0]),Integer.parseInt(a[1]));
					break;					
				}
				case "ViaPaths":
				{
					Floyd1 F=new Floyd1(ver,edg);
					String sorce=sc.nextLine();
					String a[]=sorce.split(" ");
					F.floydWarshall(odj.matrix,Integer.parseInt(a[0]),Integer.parseInt(a[1]),Integer.parseInt(a[2]));
					break;	
				}
				default:
					break;
			}
		}
	}
}
class Floyd1 
{ 
	 final static double INF =Double.MAX_VALUE;
		int V;
		int E;
	    Floyd1(int v,int e)
	    {
	    	this.V=v;
	    	this.E=e;
	    }
  
    void floydWarshall(double[][] matrix,int sr,int via,int d) 
    { 
        double dist[][] = new double[V][V]; 
        int i, j, k; 
        int next[][]=new int[29][29];
        for (i = 0; i < V; i++) 
            for (j = 0; j < V; j++)
            {
                dist[i][j] = matrix[i][j];
                if (i == j)
                    next[i][j] = 0;
                else if (dist[i][j] != Integer.MAX_VALUE)
                    next[i][j] = i;
                else
                    next[i][j] = -1;
            }
        for (k = 0; k < V; k++) 
        { 
            for (i = 0; i < V; i++) 
            { 
                for (j = 0; j < V; j++) 
                { 
                    if (dist[i][k] + dist[k][j] < dist[i][j]) 
                    {
                        dist[i][j] = dist[i][k] + dist[k][j]; 
                        next[i][j]=next[k][j];
                        //System.out.println(k);
                    }
                } 
            } 
        }
       printSolution(dist,sr,via,d,next); 
    }
    static void printPath(int[][] path, int v, int u)
    {
    	
        if (path[v][u] == v)
            return;

        printPath(path, v, path[v][u]);
        System.out.print(path[v][u] + " ");
    }
    static void printSolution(double[][] cost,int v,int via,int u, int[][] path)
    {
    	if(cost[v][u]==Double.MAX_VALUE)
    	{
    		System.out.println("No Path Found.");
    	}
    	else
    	{
    		System.out.println(cost[v][via]+cost[via][u]);
                    System.out.print(v + " ");
                    printPath(path, v,via);
                    System.out.print(via+" ");
                    printPath(path, via,u);
                    System.out.println(u);
    	}
    }
}
class Floyd 
{ 
    final static Double INF =Double.MAX_VALUE;
	int V;
	int E;
    Floyd(int v,int e)
    {
    	this.V=v;
    	this.E=e;
    }
  
    void floydWarshall(double[][] matrix,int sr,int d) 
    { 
        Double dist[][] = new Double[V][V]; 
        int i, j, k;
        for (i = 0; i < V; i++) 
            for (j = 0; j < V; j++)
            {
                dist[i][j] = matrix[i][j];
            }
        for (k = 0; k < V; k++) 
        { 
            for (i = 0; i < V; i++) 
            { 
                for (j = 0; j < V; j++) 
                { 
                    if (dist[i][k] + dist[k][j] < dist[i][j]) 
                    {
                        dist[i][j] = dist[i][k] + dist[k][j]; 
                        
                    }
                } 
            } 
        }
       printSolution(dist,sr,d); 
    }

    static void printSolution(Double[][] cost,int v,int u)
    {
                    if(cost[v][u]==Double.MAX_VALUE)
                    {
                    	System.out.println("No Path Found.");
                    }
                    else
                    {
                    	System.out.println(cost[v][u]);
                    }
                   
    }
}
class Graph {
	int V;
	int E;
	double matrix[][];
	double i=Double.MAX_VALUE;
		Graph(int v,int e)
		{
			this.V=v;
			this.E=e;
			matrix=new double[v][v];
			for(int i=0;i<v;i++)
				for(int j=0;j<v;j++)
				{
					matrix[i][j]=this.i;
				}
		}
		public void addedge(int i ,int j, double weight )
		{
			matrix[i][j]=weight;
			matrix[j][i]=weight;
		}
		public void addedge1(int i ,int j, double weight )
		{
			matrix[i][j]=weight;
		}
		public void printGraph()
		{
			System.out.println(V +" vertices "+E +" edges");
			for(int i=0;i<V;i++)
			{
				System.out.print(i+": ");
				for(int j=0;j<V;j++)
				{
					if(matrix[i][j]<Double.MAX_VALUE)
					{
						if(i<j)
						{
						System.out.print(i+"-"+j+" "+String.format("%.5f", matrix[i][j])+"  ");
						}
						else
							System.out.print(j+"-"+i+" "+String.format("%.5f", matrix[i][j])+"  ");
					}
				}
				System.out.println();
			}
		}
}

   

  
