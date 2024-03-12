package com.applications.facebook;

class FindNumberOfFriendCircles {

/*
we need to find all the people that are in each user’s friends circle on Facebook.
Your individual friend circle is defined as a group of users who are directly or indirectly friends with you.
 Assume there are a total of n users on Facebook.
  Some of them are your friends and others are not.
  The friendship/connection is transitive in nature.
  For example, if Shaw is a direct friend of Andy, and Andy is a direct friend of Noah,
  then Shaw is an indirect friend of Noah.
  Getting the total number of friend circles that exist on Facebook helps facebook
  suggest connections on Instagram for every user.

  We’ll be provided with an n x n square matrix,
  where n is the number of users on Facebook.
  A cell [i,j] will hold the value 1 if user i and user j are friends.
  Otherwise, the cell will hold the value 0.
 */
    //Basic Idea:
    // We can think of the symmetric input matrix as an undirected graph.
    // All the friends (both direct and indirect) who should be in one friend circle
    // are also in one connected component​ in the graph.
    // So, the number of connected components in the graph will give us the number of friend circles.
    // We can treat our input matrix as an adjacency matrix;
    // our task is to find the number of connected components.

//Time complexity #
//The time complexity will be O(n^2) because we traverse the complete matrix of size n^2

//Space complexity #
//The space complexity is O(n) because the visited array used to store the visited users is of size n.

  public static void DFS (boolean[][] friends, int n, boolean[] visited, int v) {
    for (int i = 0; i < n; ++i) {

        // A user is in the friend circle if he/she is friends with the user represented by
        // user index and if he/she is not already in a friend circle
        if (friends[v][i] == true && !visited[i] && i != v) {
            visited[i] = true;
            DFS(friends, n, visited, i);
        }
    }
  }

  public static int friendCircles(boolean[][] friends, int n) {
    if (n == 0) {
        return 0;
    }
 
    int numCircles = 0;     //Number of friend circles
    
    //Keep track of whether a user is already in a friend circle
    boolean visited[] = new boolean[n];

    for (int i=0;i < n; i++){
      visited[i] = false;
    }
    
    //Start with the first user and recursively find all other users in his/her
    //friend circle. Then, do the same thing for the next user that is not already
    //in a friend circle. Repeat until all users are in a friend circle. 
    for (int i = 0; i < n; ++i) {
        if (!visited[i]) {
            visited[i] = true;
            DFS(friends, n, visited, i); //Recursive step to find all friends
            numCircles = numCircles + 1;
        }
    }
    
    return numCircles;
  }

  public static void main(String args[]) 
  { 
      int n = 4;
      boolean[][] friends = {
        {true,true,false,false},
        {true,true,true,false},
        {false,true,true,false},
        {false,false,false,true}
      };
     System.out.println("Number of friends circles: " + friendCircles(friends, n)); 
  } 

}

/*
Here is how we will implement this feature.

Initialize an array, called visited,
to keep track of the visited vertices of size n with 0 as the initial value at each index.

For every vertex v,
traverse the graph using DFS if visited[v] is 0.
Otherwise, move to the next v.

Set visited[v] to 1 for every v that the DFS traversal encounters.

When the DFS traversal terminates, increment the friend circles counter by 1.
This means that​ one whole connected component has been traversed.

 */