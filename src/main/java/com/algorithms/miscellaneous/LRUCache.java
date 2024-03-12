package com.algorithms.miscellaneous;
//Implement a Least Recently Used (LRU) Cache.
/*
Caching is a technique to store data in faster storage (usually RAM) to serve future requests faster.
 Below are some common examples where the cache is used:
A processor cache is used to read data faster from the main memory (RAM).
Cache in RAM can be used to store part of disk data in RAM and serve future requests faster.
Network responses can be cached in RAM to avoid too many network calls.

However, the cache store is usually not big enough to store the full data set.
So we need to evict data from the cache whenever it becomes full.
 There are a number of caching algorithms to implement a cache eviction policy.
 LRU is a very simple and commonly used algorithm.
 The core concept of the LRU algorithm is to evict the oldest data from the cache to accommodate more data.

To implement an LRU cache we use two data structures:
a hashmap and a doubly linked list. A
 doubly linked list helps in maintaining the eviction order
 and a hashmap helps with O(1) lookup of cached keys.
  Here goes the algorithm for the LRU cache:
  If the element exists in hashmap
    move the accessed element to the tail of the linked list
  Otherwise,
    if eviction is needed i.e. cache is already full
        Remove the head element from the doubly linked list and delete its hashmap entry
    Add the new element at the tail of linked list and in hashmap
  Get from Cache and Return


  Note that the doubly linked list is used to keep track of the most recently accessed elements.
  The element at the tail of the doubly linked list is the most recently accessed element.
   All newly inserted elements (in put) go the tail of the list.
    Similarly, any element accessed (in get operation) goes to the tail of the list.


 */
/*
Least Recently Used (LRU) is a common caching strategy.
It defines the policy to evict elements from the cache to make room for new elements
 when the cache is full, meaning it discards the least recently used items first.
 */
// Linked list operations
// LinkedListNode(int data) 
// LinkedListNode(int key, int data)
// LinkedListNode(int data, LinkedListNode next)
// LinkedListNode(int data, LinkedListNode next, LinkedListNode arbitrary_pointer)
/*
Hints #
Doubly linked list
Hashing
Think about evictions
 */

/*
Runtime complexity #
The runtime complexity of this solution is linear,O(n) based on these collective complexities:
get (HashSet): O(1) in the average case, O(n) in the worst case
set (HashSet): Constant, O(1)
deletion at the head when adding a new element (linked list): Constant, O(1)
search for deleting and adding to tail (linked list): O(n)
Memory complexity #
The memory complexity of this solution is linear, O(n) where n is the size of the cache
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;

class LRUCache {
  int capacity;
  
  //LinkedListNode holds key and value pairs
  Set<Integer> cache;
  LinkedList<LinkedListNode> cacheVals;
  public LRUCache(int capacity) {
    this.capacity = capacity;
    cache = new HashSet<Integer>(capacity);
    cacheVals = new LinkedList<LinkedListNode>();
  }

  LinkedListNode get(int key) {
    if(!cache.contains(key)){
      
      return null;
    }
    else {
     
    ListIterator<LinkedListNode> iterator1 = cacheVals.listIterator(0);
    while(iterator1.hasNext()){
      LinkedListNode node = iterator1.next(); 
      if (node.key == key){
          return node;
        }
      }
      return null;
    }
  }

  void set(int key, int value) {
    LinkedListNode node = get(key);

    if(node == null){
      evictIfNeeded();
      node = new LinkedListNode(key, value);
      cacheVals.addLast(node);
      cache.add(key);
    }
    else {
      cacheVals.remove(node);
      cacheVals.addLast(node);
    }
  }

  void evictIfNeeded(){
    if(cacheVals.size() >= capacity) {
      LinkedListNode node = cacheVals.remove();
      cache.remove(node.key);
      
    }
  }

  void print() {
    ListIterator<LinkedListNode> iterator = cacheVals.listIterator(0);
    while(iterator.hasNext()){
      LinkedListNode node = iterator.next();
      System.out.print("(" + node.key + "," + node.data + ")");  
    }
    System.out.println("");
  }
  public static void main(String[] args){
    LRUCache cache = new LRUCache(2);
    
    cache.set(10,20);
    cache.print();

    cache.set(15,25);
    cache.print();

    cache.set(20,30);
    cache.print();

    cache.set(25,35);
    cache.print();

    cache.set(5,25);
    cache.print();
  }
}

class LinkedListNode {
  public int key;
  public int data;
  public LinkedListNode next;
  public LinkedListNode arbitrary_pointer;

  public LinkedListNode(int data) {
    this.data = data;
    this.next = null;
  }

  public LinkedListNode(int key, int data) {
    this.key = key;
    this.data = data;
    this.next = null;
  }

  public LinkedListNode(int data, LinkedListNode next) {
    this.data = data;
    this.next = next;
  }

  public LinkedListNode(int data, LinkedListNode next, LinkedListNode arbitrary_pointer) {
    this.data = data;
    this.next = next;
    this.arbitrary_pointer = arbitrary_pointer;
  }
}