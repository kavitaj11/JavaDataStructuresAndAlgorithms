package com.datastructures.graph.basic;



import java.util.LinkedList;

/*
A graph is an abstract notation used to represent the connection between pairs of objects.
It can be used to represent networks: systems of roads, airline flights from city to city,
how the Internet is connected, or social connectivity on facebook, twitter etc.

 We use some standard graph algorithms to solve otherwise difficult problems in these domains.

Graphs represent pairwise relationships between objects.
 Graphs are mathematical structures and consist of two basic components; nodes and edges.

A node, also known as a vertex, is a fundamental part of a graph.
It is the entity that has a name, known as the key, and other information related to that entity.
The relationship between nodes is expressed using edges.
An edge between two nodes expresses a one-way or two-way relationship between the nodes.
 In general, there can be more than one edge between a given pair of vertices called parallel edges,
 and there can be an edge from a vertex to itself, called self loop.

Graphs can be represented as adjacency matrix and adjacency list.

we will be using adjacency lists
 because algorithms can be performed more efficiently using this form of representation.
The graph class with adjacency lists consists of two data members:
The total number of vertices in the graph
A list of linked lists to store adjacent vertices
 */

public class Graph{
 int vertices; //Total number of vertices in graph
 DoublyLinkedList<Integer> adjacencyList[]; //An array of linked lists to store adjacent vertices.

 @SuppressWarnings("unchecked")
 public Graph(int vertices) {
  this.vertices = vertices;
  adjacencyList = new DoublyLinkedList[vertices];

  for (int i = 0; i < vertices; i++) {
   adjacencyList[i] = new DoublyLinkedList<>();
  }
 }

 public void addEdge(int source, int destination){
  if (source < vertices && destination < vertices){
   this.adjacencyList[source].insertAtEnd(destination);

   //for undirected graph uncomment the line below
   //this.adjacencyList[destination].insertAtEnd(source);
  }
 }
 public void printGraph()
 {
  System.out.println(">>Adjacency List of Directed Graph<<");
  for (int i = 0; i < vertices; i++)
  {
   if(adjacencyList[i]!=null){
    System.out.print("|" + i + "| => ");

    DoublyLinkedList<Integer>.Node temp = adjacencyList[i].getHeadNode();
    while (temp != null)
    {
     System.out.print("[" + temp.data + "] -> ");
     temp = temp.nextNode;
    }
    System.out.println("null");
   }
   else{

    System.out.println("|" + i + "| => "+ "null");
   }
  }
 }
}

class Main {
 public static void main(String args[]) {
  Graph g = new Graph(4);
  g.addEdge(0, 1);
  g.addEdge(0, 2);
  g.addEdge(1, 3);
  g.addEdge(2, 3);
  g.addEdge(3, 0);
 }
}


class DoublyLinkedList<T> {

 //Node inner class for DLL
 public class Node {
  public T data;
  public Node nextNode;
  public Node prevNode;
 }

 public Node headNode;
 public Node tailNode;
 public int size;

 public DoublyLinkedList() {
  this.headNode = null;
  this.tailNode = null;
 }

 public boolean isEmpty() {
  if (headNode == null && tailNode == null)
   return true;
  return false;
 }

 public Node getHeadNode() {
  return headNode;
 }

 public Node getTailNode() {
  return tailNode;
 }

 public int getSize() {
  return size;
 }

 public void insertAtHead(T data) {
  Node newNode = new Node();
  newNode.data = data;
  newNode.nextNode = this.headNode; //Linking newNode to head's nextNode
  newNode.prevNode = null;
  if (headNode != null)
   headNode.prevNode = newNode;
  else
   tailNode = newNode;
  this.headNode = newNode;
  size++;
 }

 public void insertAtEnd(T data) {
  if(isEmpty()) {
   insertAtHead(data);
   return;
  }
  Node newNode = new Node();
  newNode.data = data;
  newNode.nextNode = null;
  newNode.prevNode = tailNode;
  tailNode.nextNode = newNode;
  tailNode = newNode;
  size++;
 }

 public void printList() {
  if (isEmpty()) {
   System.out.println("List is Empty!");
   return;
  }

  Node temp = headNode;
  System.out.print("List : null <- ");

  while (temp.nextNode != null) {
   System.out.print(temp.data.toString() + " <-> ");
   temp = temp.nextNode;
  }

  System.out.println(temp.data.toString() + " -> null");
 }

 public void printListReverse() {
  if (isEmpty()) {
   System.out.println("List is Empty!");
  }

  Node temp = tailNode;
  System.out.print("List : null <- ");

  while (temp.prevNode != null) {
   System.out.print(temp.data.toString() + " <-> ");
   temp = temp.prevNode;
  }

  System.out.println(temp.data.toString() + " -> null");
 }

 public void deleteAtHead() {
  if (isEmpty())
   return;

  headNode = headNode.nextNode;
  if (headNode == null)
   tailNode = null;
  else
   headNode.prevNode = null;
  size--;
 }

 public void deleteAtTail() {
  if (isEmpty())
   return;
  tailNode = tailNode.prevNode;
  if (tailNode == null)
   headNode = null;
  else
   tailNode.nextNode = null;
  size--;
 }

}