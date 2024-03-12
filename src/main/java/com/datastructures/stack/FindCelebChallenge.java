package com.datastructures.stack;


/*
Problem Statement #
In this problem, you have to implement findCelebrity() method
to find the celebrity in a party (matrix) using a stack.
A celebrity is someone that everyone knows, but he/she doesn’t know anyone at the party.

Method Prototype: #
int findCelebrity(int[][] party,int numPeople);
Where the party is a reference variable storing a 2D matrix,
which has stored all the information about acquaintances, numPeople and the number of people present in the party.

In the party matrix, a particular [row][col] stores acquaintance information for row and col.
 In other words, if [row][col] == 1, then it means row knows col,
 and if it’s zero, then it means row doesn’t know col.
Remember that everyone knows a celebrity, but the celebrity doesn’t know the people at the party.

return - 1 if there is no celebrity in the party.
Otherwise, it will return the ID or number of celebrities from the party matrix.

Although this method can be solved by brute force using nested loops,
a stack can do it much more efficiently.

First of all, we push all the people to the stack.
 Then we pop 2 people from the stack and check if they know each other or not.
  If someone doesn’t know, then we push back that person into the stack.

This step repeats until we have only one person in the stack. For the last person,
 we check if he/she is a celebrity or not at line number 39 - 43.

Time Complexity #
The time complexity of this problem is O(n).

 */
class FindCelebChallenge {
   //returns true if x knows y else returns false
    private static boolean aqStatus(int[][] party, int x, int y) {
        if (party[x][y] == 1) return true;
        return false;
    }

    public static int findCelebrity(int[][] party, int numPeople) {
        Stack<Integer> stack = new Stack<>(numPeople);
        int celebrity = -1;

        //Push all people in stack
        for (int i = 0; i < numPeople; i++) {
            stack.push(i);
        }

        while (!stack.isEmpty()) {

            //Take two people out of stack and check if they know each other
            //One who doesn't know the other, push it back in stack.
            int x = stack.pop();

            if (stack.isEmpty()) {
                celebrity = x;
                break;
            }

            int y = stack.pop();

            if (aqStatus(party, x, y)) {
                //x knows y , discard x and push y in stack
                stack.push(y);
            } else stack.push(x);

        } //end of while

        //At this point we will have last element of stack as celebrity
        //Check it to make sure it's the right celebrity
        for (int j = 0; j < numPeople; j++) {

            //Celebrity knows no one while everyone knows celebrity
            if (celebrity != j && (aqStatus(party, celebrity, j) || !(aqStatus(party, j, celebrity)))) return -1;
        }
        return celebrity;
    }//end of findCelebrity()

    public static void main(String args[]) {
        
        int [][] party1 = {
          {0,1,1,0},
          {1,0,1,1},
          {0,0,0,0},
          {0,1,1,0},   
        };

        int [][] party2 = {
          {0,1,1,0},
          {1,0,1,1},
          {0,0,0,1},
          {0,1,1,0},   
        };

        int [][] party3 = {
          {0,0,0,0},
          {1,0,0,1},
          {1,0,0,1},
          {1,1,1,0},   
        };
        
        System.out.println(findCelebrity(party1,4));
        System.out.println(findCelebrity(party2,4));
        System.out.println(findCelebrity(party3,4));
    }
}