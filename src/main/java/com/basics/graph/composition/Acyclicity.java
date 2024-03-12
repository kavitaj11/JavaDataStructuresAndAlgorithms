package com.basics.graph.composition;//package com.basics.graph;

import java.util.ArrayList;
import java.util.Scanner;

//Check whether a given directed graph with 𝑛 vertices and 𝑚 edges contains a cycle.
public class Acyclicity {

    /**
     * Detect cycle in directed graph.
     * </br>
     * Example: [1->2, 2->3, 3->4, 4->2], 1.</br>
     * Example: [1->2, 1->3, 2->3], 0.</br>
     * Example: [1->2, 3->1, 2->3], 1.</br>
     */
    
    public static int acyclic(ArrayList<Integer>[] adj) {
        /**Directed graphs: if adj node is visited on recursion stack.*/
        int n = adj.length;
        boolean[] visited = new boolean[n];
        boolean[] onstack = new boolean[n];
        for (int v = 0; v < n; v++) {
            if (! visited[v]) {
                if (dfs(v, adj, visited, onstack) == 1) return 1;
            }
        }
        return 0;
    }

    private static int dfs(int vertex, ArrayList<Integer>[] adj,
                           boolean[] visited, boolean[] onstack) {
        visited[vertex] = true;
        onstack[vertex] = true;
        for (int w : adj[vertex]) {
            // If adjacent node is unvisited: recursively explore.
            if (! visited[w]) {
                if (dfs(w, adj, visited, onstack) == 1) return 1;
            }
            // BZ: adjacent node is visited but not on stack: continue.
            // BZ: adjacent node is visited and on stack: found cycle.
            if (visited[w] && onstack[w]) return 1;
        }
        // All neighbors of current v have been explored, pop v from stack.
        onstack[vertex] = false;
        return 0;
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
        System.out.println(acyclic(adj));
    }
}

