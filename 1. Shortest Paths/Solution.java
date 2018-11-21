import javafx.util.Pair;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {
    static class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class Graph {
        int vertices;
        LinkedList<Edge>[] adjacencylist;

        Graph(int vertices) {
            this.vertices = vertices;
            adjacencylist = new LinkedList[vertices];
            //initialize adjacency lists for all the vertices
            for (int i = 0; i <vertices ; i++) {
                adjacencylist[i] = new LinkedList<>();
            }
        }

        public void addEdge(int source, int destination, int weight) {
            Edge edge = new Edge(source, destination, weight);
            adjacencylist[source].addFirst(edge);

            edge = new Edge(destination, source, weight);
            adjacencylist[destination].addFirst(edge); //for undirected graph
        }

        public void dijkstra_GetMinDistances(int sourceVertex,int desti){

            boolean[] SPT = new boolean[vertices];
            //distance used to store the distance of vertex from a source
            int [] distance = new int[vertices];

            //Initialize all the distance to infinity
            for (int i = 0; i <vertices ; i++) {
                distance[i] = Integer.MAX_VALUE;
            }
            //Initialize priority queue
            //override the comparator to do the sorting based keys
            PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(vertices, new Comparator<Pair<Integer, Integer>>() {
                @Override
                public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
                    //sort using distance values
                    int key1 = p1.getKey();
                    int key2 = p2.getKey();
                    return key1-key2;
                }
            });
            //create the pair for for the first index, 0 distance 0 index
            distance[sourceVertex] = 0;
            Pair<Integer, Integer> p0 = new Pair<>(distance[sourceVertex],sourceVertex);
            //add it to pq
            pq.offer(p0);

            //while priority queue is not empty
            while(!pq.isEmpty()){
                //extract the min
                Pair<Integer, Integer> extractedPair = pq.poll();

                //extracted vertex
                int extractedVertex = extractedPair.getValue();
                if(SPT[extractedVertex]==false) {
                    SPT[extractedVertex] = true;

                    //iterate through all the adjacent vertices and update the keys
                    LinkedList<Edge> list = adjacencylist[extractedVertex];
                   for (int i = 0; i < list.size(); i++) {
                        Edge edge = list.get(i);
                        int destination = edge.destination;
                        //only if edge destination is not present in mst
                        if (SPT[destination] == false) {
                            ///check if distance needs an update or not
                            //means check total weight from source to vertex_V is less than
                            //the current distance value, if yes then update the distance
                            int newKey =  distance[extractedVertex] + edge.weight ;
                            int currentKey = distance[destination];
                            if(currentKey>newKey){
                                Pair<Integer, Integer> p = new Pair<>(newKey, destination);
                                pq.offer(p);
                                distance[destination] = newKey;
                            }
                        }
                    }                   
                }
            }
            //print Shortest Path Tree
//            System.out.println(sourceVertex);
//            if(sourceVertex==desti)
            printDijkstra(distance, sourceVertex,desti);
        }

        public void printDijkstra(int[] distance, int sourceVertex,int desti){
           // System.out.println("Dijkstra Algorithm: (Adjacency List + Priority Queue)");
            for (int i = 0; i <vertices ; i++) {
            	if(i==desti)
            	{
            		//System.out.println("count"+1);
                System.out.println(distance[i]);
            	}
            }
        }

        public static void main(String[] args) {
           Scanner sc=new Scanner(System.in);
           while (sc.hasNext())
           {
        	String vered =sc.nextLine();
        	String b[]=vered.split(" ");
            Graph graph = new Graph(Integer.parseInt(b[0]));
            String names=sc.nextLine();
            String name[]=names.split(" ");
            int fr = 0;
            int las=0;
            for(int i=0;i<Integer.parseInt(b[1]);i++)
            {
            	String co=sc.nextLine();
            	String sp[]=co.split(" ");
            	for(int j=0;j<name.length;j++)
            	{
            		if(sp[0].equals(name[j]))
            			fr=j;
            		if(sp[1].equals(name[j]))
            			las=j;
            	}
            graph.addEdge(fr, las, Integer.parseInt(sp[2]));
            }
            int qua=Integer.parseInt(sc.nextLine());
            for(int i=0;i<qua;i++)
            {
            	String co=sc.nextLine();
            	String sp[]=co.split(" ");
            	for(int j=0;j<name.length;j++)
            	{
            		if(sp[0].equals(name[j]))
            			fr=j;
            		if(sp[1].equals(name[j]))
            			las=j;
            	}
            	//System.out.println(fr+" "+las);
            	//System.out.println(name[fr]+" "+name[las]);
            		 graph.dijkstra_GetMinDistances(fr,las);
            }
//            graph.dijkstra_GetMinDistances(0,fr);
           }
        }
    }
}