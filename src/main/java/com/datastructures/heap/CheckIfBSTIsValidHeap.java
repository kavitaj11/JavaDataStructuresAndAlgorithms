package com.datastructures.heap;

/*
Min Heap
Min heap is a complete binary tree in which the value of root element is
less than or equal to either of the child element.
Representation of min heap
Arr[(i-1) / 2]: this will return the parent node.
Arr[(2*i) + 1]: this will return the left child node.
Arr[(2*i) + 2]: this will return the right child node.

There are certain methods of Min Heap:
insert(): A new key at the end of the tree is added. In case the new key is larger then the parent, then there is no need to do anything, else, we have to traverse up to set up the heap property.
getMin(): this methods helps to return the root element.
extractMin(): this method returns the minimum element.
Moving on to Max heap now.

Max heap
Max heap is a complete binary tree in which the value of root element is greater than
or equal to either of the child element.
 */
public class CheckIfBSTIsValidHeap {
}
