import java.util.Scanner;

interface Graph {
    public int V();
    public int E();
    public void addEdge(int v, int w);
    public Iterable<Integer> adj(int v);
    public boolean hasEdge(int v, int w);
}
//class GraphList implements Graph
//{
//	
//}
class GraphMatrix implements Graph
{
	int ver;
	int edg;
	int [][]matrix;
	String[] city;
	 public GraphMatrix(Scanner scan) {
		 this.ver = Integer.parseInt(scan.nextLine());
	        matrix =  new int[ver][ver];
	        int edge = Integer.parseInt(scan.nextLine());
	        city = scan.nextLine().split(",");
	        for (int i = 0; i < edge; i++) {
	            String[] inputs = scan.nextLine().split(" ");
	            addEdge(Integer.parseInt(inputs[0]),Integer.parseInt(inputs[1]));
	        }
	}
	public int V()
	 {
		return ver;
		 
	 }
	    public int E()
	    {
			return edg;
	    	
	    }
	    public void addEdge(int v, int w)
	    {
	    	if (v != w) {
	            if (!hasEdge(v, w)) {
	                matrix[v][w] = 1;
	                matrix[w][v] = 1;
	                edg++;
	            }
	        } else {
	            return;
	        }
	    }
	    public Iterable<Integer> adj(int v)
	    {
			return null;
	    	
	    }
	    public boolean hasEdge(int v, int w)
	    {
			if(matrix[v][w]==1)
			{
				return true;
			}
			return false;
	    	
	    }
	    public void output()
	    {
	    	int count=0;
	    	System.out.println(ver+" vertices, "+edg+" edges");
	    	for(int i=0;i<ver;i++)
	    	{
	    		int j=0;
	    		for(;j<ver;j++)
	    		{
	    			if(matrix[i][j]==0)
	    				count++;	
	    		}
	    	}
	    	//System.out.println("Matric"+matrix.length);
	    	if(count!=matrix.length)
	    	for(int i=0;i<ver;i++)
	    	{
	    		int j=0;
	    		for(;j<ver-1;j++)
	    		{
	    			System.out.print(matrix[i][j]+" ");
	    		}
	    		System.out.print(matrix[i][j]);
	    		System.out.println();
	    	}
	    	else
	    		System.out.println("No edges");
	    	
	    }
}
class Solution{
   
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String s  = scan.nextLine();
        switch (s) {
        case "List":
//            GraphList listObj = new GraphList(scan);
//            System.out.println(listObj);
            break;
        case "Matrix":
            GraphMatrix matObj = new GraphMatrix(scan);
            matObj.output();
            break;
        default:
            break;
        }
    }
}