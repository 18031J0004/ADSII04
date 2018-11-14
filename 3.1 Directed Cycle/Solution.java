import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;
class Bag<Item> implements Iterable<Item> {
    private int N;         // number of elements in bag
    private Node first;    // beginning of bag

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
    }

   /**
     * Create an empty stack.
     */
    public Bag() {
        first = null;
        N = 0;
    }

   /**
     * Is the BAG empty?
     */
    public boolean isEmpty() {
        return first == null;
    }

   /**
     * Return the number of items in the bag.
     */
    public int size() {
        return N;
    }

   /**
     * Add the item to the bag.
     */
    public void add(Item item) {
    	
    	
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }


   /**
     * Return an iterator that iterates over the items in the bag.
     */
    public Iterator<Item> iterator()  {
        return new ListIterator();  
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }

}

class Graph 
{ 
    private int V;
    private Bag<Integer> adj[];
    Graph(int v) 
    { 
       this.V = v; 
        adj = (Bag<Integer>[])new Bag[V]; 
        for (int i=0; i<v; ++i) 
            adj[i] = new Bag(); 
    }
    void addEdge(int v, int w) 
    { 
    	
    		int count=0;
    			Iterator<Integer> itr = adj[v].iterator(); 
                
                while (itr.hasNext())  
                { 
                    int x = itr.next(); 
                    if(x==w)
                    {
                    	count++;break;
                    }
                    	
                                     
                }
    		if(count==0)
    		    adj[v].add(w);
    }
    	
     
    
    boolean DFS(int s) 
    { 
        ArrayList<Boolean> visited = new ArrayList<Boolean>(V); 
        for (int i = 0; i < V; i++) 
            visited.add(i,false); 
        Stack<Integer> stack = new Stack<>(); 
        stack.push(s); 
          
        while(stack.empty() == false) 
        { 
        	s = stack.peek(); 
        	stack.pop(); 
            if(visited.get(s) == false) 
            { 
                //System.out.print(s + " "); 
                visited.set(s, true); 
            
            
            Iterator<Integer> itr = adj[s].iterator(); 
              
            while (itr.hasNext())  
            { 
                int v = itr.next(); 
                if(!visited.get(v)) 
                    stack.push(v); 
               
                
            }
            }
            else
            {
            	return true;
            }
              
        }
		return false; 
    } 
} 
class Solution
{
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		int ver=Integer.parseInt(sc.nextLine());
		int edge=Integer.parseInt(sc.nextLine());
		Graph obj=new Graph(ver);
		int count1=0;
		for(int i=0;i<edge;i++)
		{
			String a=sc.nextLine();
			String[] b=a.split(" ");
			if(Integer.parseInt(b[0])==Integer.parseInt(b[1]))
			{
				System.out.println("Cycle exists.");
				count1++;
				break;
			}	
			else
			{
			obj.addEdge(Integer.parseInt(b[0]),Integer.parseInt(b[1]));
			
			}
		}
		if(count1==0) {
		boolean a=obj.DFS(0);
		if(a==true)
		{
		System.out.println("Cycle exists.");	
		}
		else
			System.out.println("Cycle doesn't exists.");
	}
	}
}




