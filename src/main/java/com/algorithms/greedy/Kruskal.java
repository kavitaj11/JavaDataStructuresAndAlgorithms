package com.algorithms.greedy;

import java.util.Arrays;

class Kruskal {
//Given an undirected weighted graph, find its minimum spanning tree.
 /*
 A minimum spanning tree (MST), or minimum weight spanning tree,
 is a subset of the edges of a connected, edge-weighted, (un)directed graph
 that connects all the vertices together without any cycles
 and with the minimum possible total edge weight.
  That is, it is a spanning tree whose sum of edge weights is as small as possible.
  More generally, any edge-weighted, undirected graph (not necessarily connected)
  has a minimum spanning forest, which is a union of the minimum spanning trees, for its connected components.

  To implement a function to find the minimum spanning tree of a graph,
  we have to make certain changes in our Graph class.
  We have added a class that would represent an Edge between two vertices.
  This includes the addition of the weight property, which will be assigned to every edge.
   We have also added a class to represent a disjoint set.
  */

 //Kruskal’s algorithm is a minimum-spanning-tree algorithm
 // that finds an edge of the least possible weight.
 //It is a greedy algorithm, as it finds a minimum spanning tree
 // for a connected weighted graph and adds increasing cost arcs at each step.

 /*
 Kruskal’s algorithm does the following:
1. It sorts all the edges in non-decreasing order of their weight.
2. It chooses the smallest edge and checks if it forms a cycle with the spanning tree formed so far.
   It includes this edge if a cycle is not formed. Otherwise, it discards the edge.
   To detect a cycle, it iterates through all the edges of the graph to find a subset of both vertices of every edge.
   If both subsets are the same, there is a cycle in the graph.
3. It repeats step two until there are (V-1) edges in the spanning tree.

Time complexity #
The sorting of edges takes O(ELogE) time.
The value of E can be at most O(V^2),so O(LogV) is the same as O(LogE).
Therefore, the overall time complexity is O(ElogE) or O(ElogV).

 */
 public static void KruskalMST(Graph g) {

  Graph.Edge answer[] = new Graph.Edge[g.Vertex];
  int j = 0; //index for keeping track of asnwer[]
  int i = 0; //index for keeping track of sorted edges 
  for (i = 0; i < g.Vertex; ++i) {
   answer[i] = new Graph.Edge();
  }
  //sort all edges
  Arrays.sort(g.edge);
  //allocating memory to create subsets
  Graph.DisjointSets mySet[] = new Graph.DisjointSets[g.Vertex];
  for (i = 0; i < g.Vertex; ++i)
   mySet[i] = new Graph.DisjointSets();
  //creating subsets 
  for (int x = 0; x < g.Vertex; ++x) {
   mySet[x].p = x;
   mySet[x].r = 0;
  }
  i = 0;

  while (j < g.Vertex - 1) {
   
   //picking the smallest edge
   Graph.Edge nextEdge = new Graph.Edge();

   //incrementing the index for next iteration
   nextEdge = g.edge[i++];

   int temp1 = g.find(mySet, nextEdge.source);
   int temp2 = g.find(mySet, nextEdge.destination);

   //if cycle not formed, include edge in answer[]
   if (temp1 != temp2) {
    
    //incrementing the index for answer[] for next edge
    answer[j++] = nextEdge;
    g.merge(mySet, temp1, temp2);
   }
  }
  //printing contents of answer[] to display the MST
  for (i = 0; i < j; ++i)
   System.out.println(answer[i].source + " , " + answer[i].destination);

 }


 public static void main(String[] args) {

  int V = 4, E = 5;
  Graph graph = new Graph(V, E);

  // add edge 0-1
  graph.edge[0].source = 0;
  graph.edge[0].destination = 1;
  graph.edge[0].weight = 10;

  // add edge 0-2
  graph.edge[1].source = 0;
  graph.edge[1].destination = 2;
  graph.edge[1].weight = 6;

  // add edge 0-3
  graph.edge[2].source = 0;
  graph.edge[2].destination = 3;
  graph.edge[2].weight = 5;

  // add edge 1-3
  graph.edge[3].source = 1;
  graph.edge[3].destination = 3;
  graph.edge[3].weight = 15;

  // add edge 2-3
  graph.edge[4].source = 2;
  graph.edge[4].destination = 3;
  graph.edge[4].weight = 4;

  System.out.println("Minimum Spanning Tree of Graph 1");
  Kruskal.KruskalMST(graph);
  System.out.println();


  V = 6;
  E = 15;
  Graph graph1 = new Graph(V, E);


  graph1.edge[0].source = 0;
  graph1.edge[0].destination = 1;
  graph1.edge[0].weight = 4;

  graph1.edge[1].source = 0;
  graph1.edge[1].destination = 2;
  graph1.edge[1].weight = 3;

  graph1.edge[2].source = 1;
  graph1.edge[2].destination = 2;
  graph1.edge[2].weight = 1;


  graph1.edge[3].source = 1;
  graph1.edge[3].destination = 3;
  graph1.edge[3].weight = 2;

  graph1.edge[4].source = 2;
  graph1.edge[4].destination = 3;
  graph1.edge[4].weight = 4;

  graph1.edge[5].source = 3;
  graph1.edge[5].destination = 4;
  graph1.edge[5].weight = 2;

  graph1.edge[6].source = 4;
  graph1.edge[6].destination = 5;
  graph1.edge[6].weight = 6;

  System.out.println("Minimum Spanning Tree of Graph 2");
  Kruskal.KruskalMST(graph1);
 }
}



class Graph {
 //class for represting an edge of a graph
 public static class Edge implements Comparable < Edge > {
  int source, destination, weight;
  //sorting edges on weight
  public int compareTo(Edge var) {
   return this.weight - var.weight;
  }
 };

 //class for Disjoint sets
 public static class DisjointSets {
  int p, r;
 }

 int Vertex, Edges;
 Edge edge[];


 @SuppressWarnings("unchecked")

 public Graph(int v, int e) {
  Vertex = v;
  Edges = e;
  edge = new Edge[Edges];
  for (int i = 0; i < e; ++i)
   edge[i] = new Edge();
 }

 //function for finding set of a given element
 int find(DisjointSets mySet[], int v) {
  // find root and make root as parent of i
  if (mySet[v].p != v)
   mySet[v].p = find(mySet, mySet[v].p);

  return mySet[v].p;
 }

 //function for taking union of two sets
 void merge(DisjointSets mySet[], int i, int j) {
  int set_i = find(mySet, i);
  int set_j = find(mySet, j);

  if (mySet[set_i].r < mySet[set_j].r)
   mySet[set_i].p = set_j;
  else if (mySet[set_i].r > mySet[set_j].r)
   mySet[set_j].p = set_i;

  else {
   mySet[set_j].p = set_i;
   mySet[set_i].r++;
  }
 }
}