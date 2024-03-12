package com.applications.googleCalender;

//we want to program a function that will let User A know if
// it is possible to schedule a meeting with User B or not.
// This decision will be made based on User B’s meeting schedule.
// If the new meeting’s time overlaps with an existing meeting in User B’s schedule,
// then the new meeting can not be scheduled.

//You will be given a list of start and end times of User B’s scheduled meetings, which are non-overlapping.
// Additionally,
// you will be given the start and end times for the proposed meeting, which we need to verify is schedulable.

/*
This feature can be implemented by brute force traversal.
 However, we can make it more efficient using a Binary Search Tree (BST).
 The main advantage is that we can insert all the meetings in a BST first,
 and then check if the new meeting can also be inserted without any clash.
 Placing the meetings in a BST in sorted order lets us verify whether the new meeting can be added in O(log(n)) time.
 */

class BST {
    class Node{
        int start;
        int end;
        Node leftChild;
        Node rightChild;
        Node(int start, int end){
            this.start = start;
            this.end = end;
        }
    }

    Node root;

    public BST(){
        this.root = null;
    }

    public boolean insert(int start, int end) {

        if (this.root == null) {
            root = new Node(start, end);
            return true;
        }
        Node newNode = new Node(start, end);
        return addNode(this.root, newNode);
    }

    public boolean addNode(Node currentNode, Node newNode) {
        if(newNode.start >= currentNode.end){
            if(currentNode.rightChild == null){
                currentNode.rightChild = newNode;
                return true;
            }
            return addNode(currentNode.rightChild, newNode);
        }
        else if (newNode.end <= currentNode.start){
            if(currentNode.leftChild == null){
                currentNode.leftChild = newNode;
                return true;
            }
            return addNode(currentNode.leftChild, newNode);
        }
        else{
            return false;
        }
    }
}


public class CheckMeetingPossible {

    public static boolean checkMeeting(int[][] meetingTimes, int[] newMeeting){
        BST tree = new BST();
        for(int[] meeting: meetingTimes){
            tree.insert(meeting[0], meeting[1]);
        }
        return tree.insert(newMeeting[0], newMeeting[1]);
    }

    public static void main( String args[] ) {
        int[][] meetingTimes = {{1, 3}, {4, 6}, {8, 10}, {10, 12}, {13, 15}};
        int[] newMeeting = {7, 8};
        System.out.println(checkMeeting(meetingTimes, newMeeting));
        newMeeting = new int[] {9, 11};
        System.out.println(checkMeeting(meetingTimes, newMeeting));
    }
}

/*
Here is how we will implement this feature:

BST class: First, we will implement a simple BST class with a constructor, an insert function,
and an addNode function.

constructor: The default constructor initializes the root of the node as null.
Later on, this root will be of type Node.

insert() function:
This function takes in the start and end timing of meetings and creates a new node.
If the root is null, then the new node will become the root.
Otherwise, the recursive helper function addNode will be called.
The return type of this function is Boolean, as we will use it to determine if the node was added successfully.

addNode() function:
 This recursive helper function has two inputs:
 currentNode, which is initially the root node, and the newNode to be added.
 We will check if the newNode starts before the currentNode ends;
 this shows that there is no conflict up to this point.
 This means we will have to call the recursive function again to check for
  a conflict in the right child of the currentNode.
  If the previous check does not pass, we will check if the newNode starts after the currentNode
   and similarly call the recursive function for the left subtree if the check passes.
   If both of these conditions fail, this means that the new meeting overlaps with an existing meeting.
    Therefore, we will return false.

checkMeeting() function:
 We initialize the BST with the user’s scheduled meetings in this function.
 Then, we will add the new meeting and return the result.

 */
