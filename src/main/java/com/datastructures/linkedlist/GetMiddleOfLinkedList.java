package com.datastructures.linkedlist;

public class GetMiddleOfLinkedList {

 //get the middle element of linked list in one pass, lets see how,

 //In this approach, we take 2 pointers, fastPointer and slowPointer and initialize both to list head.
 //Iterate through list and move slowPointer 1 node at a time and fastPointer 2 nodes at a time.
 //When fastPointer reaches end of list, slowPointer will be pointing to middle element of list.
 private static Node findMiddleNodeOfLinkedList(Node startNode) {
  if(startNode==null){
   return startNode;
  }
 
  Node slowPointer = startNode;
  Node fastPointer = startNode;
  while(fastPointer!=null && fastPointer.getNext()!=null && fastPointer.getNext().getNext()!=null){
   slowPointer = slowPointer.getNext();
   fastPointer = fastPointer.getNext().getNext();
 
  }
  return slowPointer;
 }



 public static void main(String[] args) {

  Node startNode = new Node(10);
  Node temp2 = new Node(20);
  Node temp3 = new Node(30);
  Node temp4 = new Node(40);
  Node temp5 = new Node(50);
  Node temp6 = new Node(60);
  Node temp7 = new Node(70);
  Node temp8 = new Node(80);

  startNode.setNext(temp2);
  temp2.setNext(temp3);
  temp3.setNext(temp4);
  temp4.setNext(temp5);
  temp5.setNext(temp6);
  temp6.setNext(temp7);
  temp7.setNext(temp8);

  Node temp = findMiddleNodeOfLinkedList(startNode);
  System.out.println(temp.getData());
 }
}

class Node{
 private int data;
 private Node next;
 public Node(int data, Node next) {
  this.data = data;
  this.next = next;
 }
 public Node(int data) {
  this.data = data;
 }
 public int getData() {
  return data;
 }
 public void setData(int data) {
  this.data = data;
 }
 public Node getNext() {
  return next;
 }
 public void setNext(Node next) {
  this.next = next;
 }
}