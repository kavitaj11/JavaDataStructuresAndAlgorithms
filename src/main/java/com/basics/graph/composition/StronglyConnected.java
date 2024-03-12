package com.basics.graph.composition;//package com.basics.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

//Checking Whether Any Intersection in a City is Reachable from Any Other
//Task. Check whether a given directed graph with 𝑛 vertices and 𝑚 edges contains a cycle.
public class StronglyConnected {

    /*
     * DFS for all source CCs in GR in postorder. (how?)
     * For each sink CC in G remained,
     * explore all connected vertices in CC to remove sink CC;
     * count a SCC after this removal.</pre>
     */

    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
        // How to DFS on reverse graph? How to get GR?
        ArrayList<Integer>[] adjR = reverseGraph(adj);
        // DFS on GR for all source CCs in postorder.
        Stack<Integer> post = new Stack<>();
        boolean[] visited = new boolean[adj.length];
        for (int v = 0; v < adj.length; v++) {
            if (! visited[v]) dfs(adjR, v, visited, post);
        }
        int res = 0;
        visited = new boolean[adj.length];
        // Reverse from max post source in GR, i.e. sink CC in G.
        while (! post.isEmpty()) {
            int v = post.pop();
            // BZ: continue if this v in source CC is visited.
            if (visited[v]) continue;
            // Explore all connected from v inside the sink CC.
            dfs(adj, v, visited, null);
            res++;
        }
        return res;
    }

    private static ArrayList<Integer>[] reverseGraph(ArrayList<Integer>[] adj) {
        /**Reverse G to GR: O(V+E)*/
        ArrayList<Integer>[] res = new ArrayList[adj.length];
        // Initialize empty adjacency list for each vertex. O(V)
        for (int v = 0; v < adj.length; v++) res[v] = new ArrayList<>();
        // for each vertex v, add v into reverse list of its neighbors.
        // BZ: O(V*E)? O(V+E)? traverse each vertex and each edge once.
        for (int v = 0; v < adj.length; v++) {
            for (int w : adj[v]) {
                res[w].add(v);
            }
        }
        return res;
    }

    private static void dfs(ArrayList<Integer>[] adj, int v,
                            boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        for (int w : adj[v]) {
            if (! visited[w]) {
                dfs(adj, w, visited, stack);
            }
        }
        // Postorder(): push all post vertex.
        if (stack != null) stack.push(v);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        System.out.println(numberOfStronglyConnectedComponents(adj));
    }
}

