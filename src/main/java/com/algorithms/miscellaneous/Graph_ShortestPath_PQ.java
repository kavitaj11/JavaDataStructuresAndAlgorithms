package com.algorithms.miscellaneous;

import java.util.*;

/*
Given a weighted graph and a starting (source) vertex in the graph,
Dijkstra’s algorithm is used to find the shortest distance
from the source node to all the other nodes in the graph.

As a result of the running Dijkstra’s algorithm on a graph,
we obtain the shortest path tree (SPT) with the source vertex as root.

Dijkstra is a greedy algorithm.
Similar to Prim’s algorithm of finding the minimum spanning tree (MST)
these algorithms also start from a root vertex
and always chooses the most optimal vertex with the minimum path.
It is used mostly in routing protocols as it helps to find the shortest path from one node to another node.

In Dijkstra’s algorithm, we maintain two sets or lists.
One contains the vertices that are a part of the shortest-path tree (SPT)
and the other contains vertices that are being evaluated to be included in SPT.
Hence for every iteration, we find a vertex from the second list that has the shortest path.

The pseudocode for the Dijkstra’s shortest path algorithm is given below.

Pseudocode
Given below is the pseudocode for this algorithm.

procedure dijkstra(G, S)
     G-> graph; S->starting vertex
begin
    for each vertex V in G          //initialization; initial path set to infinite
        path[V] <- infinite
        previous[V] <- NULL
        If V != S, add V to Priority Queue PQueue
    path [S] <- 0

    while PQueue IS NOT EMPTY
        U <- Extract MIN from PQueue
        for each unvisited adjacent_node  V of U
            tempDistance <- path [U] + edge_weight(U, V)
            if tempDistance < path [V]
                path [V] <- tempDistance
                previous[V] <- U
    return path[], previous[]
end

Implementation Of Dijkstra’s Algorithm In Java
Implementation of Dijkstra’s shortest path algorithm in Java can be achieved using two ways.
We can either use priority queues and adjacency list
or we can use adjacency matrix and arrays.

We usually implement Dijkstra’s algorithm using a Priority queue as we have to find the minimum path.

Time Complexity of Dijkstra’s Algorithm is O (V^2).
When implemented with the min-priority queue, the time complexity of this algorithm comes down to O (V + E l o g V).

Implementation Using A Priority Queue
In this implementation, we use the priority queue to store the vertices with the shortest distance.
The graph is defined using the adjacency list.
 */

public class Graph_ShortestPath_PQ {
    int dist[]; 
    Set<Integer> visited; 
    PriorityQueue<Node> pqueue; 
    int V; // Number of vertices 
    List<List<Node> > adj_list; 
    //class constructor
    public Graph_ShortestPath_PQ(int V) {
        this.V = V; 
        dist = new int[V]; 
        visited = new HashSet<Integer>(); 
        pqueue = new PriorityQueue<Node>(V, new Node()); 
    } 
   
    // Dijkstra's Algorithm implementation 
    public void algo_dijkstra(List<List<Node> > adj_list, int src_vertex) 
    { 
        this.adj_list = adj_list; 
   
        for (int i = 0; i < V; i++) 
            dist[i] = Integer.MAX_VALUE; 
   
        // first add source vertex to PriorityQueue 
        pqueue.add(new Node(src_vertex, 0)); 
   
        // Distance to the source from itself is 0 
        dist[src_vertex] = 0; 
        while (visited.size() != V) { 
 
        // u is removed from PriorityQueue and has min distance
            int u = pqueue.remove().node; 
   
            // add node to finalized list (visited)
            visited.add(u); 
            graph_adjacentNodes(u); 
        } 
    } 
    // this methods processes all neighbours of the just visited node
    private void graph_adjacentNodes(int u)   { 
        int edgeDistance = -1; 
        int newDistance = -1; 
   
        // process all neighbouring nodes of u 
        for (int i = 0; i < adj_list.get(u).size(); i++) { 
            Node v = adj_list.get(u).get(i); 
   
            //  proceed only if current node is not in 'visited'
            if (!visited.contains(v.node)) { 
                edgeDistance = v.cost; 
                newDistance = dist[u] + edgeDistance; 
   
                // compare distances 
                if (newDistance < dist[v.node]) 
                    dist[v.node] = newDistance; 
   
                // Add the current vertex to the PriorityQueue 
                pqueue.add(new Node(v.node, dist[v.node])); 
            } 
        } 
    } 


    public static void main(String arg[])   { 
        int V = 6; 
        int source = 0; 
        // adjacency list representation of graph
        List<List<Node> > adj_list = new ArrayList<List<Node> >(); 
        // Initialize adjacency list for every node in the graph 
        for (int i = 0; i < V; i++) { 
            List<Node> item = new ArrayList<Node>(); 
            adj_list.add(item); 
        } 
 
   
        // Input graph edges 
        adj_list.get(0).add(new Node(1, 5)); 
        adj_list.get(0).add(new Node(2, 3)); 
        adj_list.get(0).add(new Node(3, 2)); 
        adj_list.get(0).add(new Node(4, 3));
        adj_list.get(0).add(new Node(5, 3));
        adj_list.get(2).add(new Node(1, 2)); 
        adj_list.get(2).add(new Node(3, 3)); 
        // call Dijkstra's algo method  
        Graph_ShortestPath_PQ dpq = new Graph_ShortestPath_PQ(V);
        dpq.algo_dijkstra(adj_list, source); 
   
        // Print the shortest path from source node to all the nodes 
        System.out.println("The shorted path from source node to other nodes:"); 
        System.out.println("Source\t\t" + "Node#\t\t" + "Distance");
        for (int i = 0; i < dpq.dist.length; i++) 
            System.out.println(source + " \t\t " + i + " \t\t "  + dpq.dist[i]); 
    } 
} 
   
// Node class  
class Node implements Comparator<Node> { 
    public int node; 
    public int cost; 
    public Node() { } //empty constructor 
   
    public Node(int node, int cost) { 
        this.node = node; 
        this.cost = cost; 
    } 
    @Override
    public int compare(Node node1, Node node2) 
    { 
        if (node1.cost < node2.cost) 
            return -1; 
        if (node1.cost > node2.cost) 
            return 1; 
        return 0; 
    } 
}