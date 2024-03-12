package com.basics.graph.path;

import java.util.*;

public class ShortestPaths1 {

    private static void shortestPaths(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost,
                                      int s, long[] distance, int[] reachable, int[] shortest) {
        int n = adj.length;
        distance[s] = 0;
        // Run Bellman-Ford with V-1 relax().
        for (int i = 0; i < n; i++) {
            for (int u = 0; u < n; u++)
                relax(u, adj[u], cost[u], distance);
        }
        //for (int i = 0; i < n; i++) System.out.print((i + 1) + ": " + distance[i] + "\t");
        // System.out.println();
        // Run V-th iteration to relax all edges.
        // Record into queue all vertices that change distance.
        Queue<Integer> queue = new LinkedList<>();
        for (int u = 0; u < n; u++) {
            relax(u, adj[u], cost[u], distance, queue);
        }
        //System.out.println(queue);
        // BFS from the queue to mark no shortest path.
        Set<Integer> negativeCycle = bfs(queue, adj);
        //System.out.println(negativeCycle);
        for (int v : queue) distance[v] = -1;
        for (int v = 0; v < n; v++) {
            // If remaining vertex has +inf distance, unreachable case.
            if (distance[v] == Long.MAX_VALUE) continue;
            reachable[v] = 1;  // Else all other nodes are reachable.
            // shortest[S] is 0? will print S as '-'.
            // Mark all vertices in negative cycle as no shortest paths.
            if (v == s) shortest[v] = 1;
            else shortest[v] = negativeCycle.contains(v) ? 0 : 1;
        }
        // BZ: has shortest path but path length is negative?
        distance[s] = 0;
    }

    private static void relax(int u, List<Integer> adjU, List<Integer> costU,
                              long[] distance) {
        //Relax only, without recording changed dist[v].
        relax(u, adjU, costU, distance, null);
    }

    private static void relax(int u, List<Integer> adjU, List<Integer> costU,
                              long[] distance, Queue<Integer> queue) {
        //Relax on each adjacent edge from u; enqueue all vertices with changed distance.
        for (int i = 0; i < adjU.size(); i++) {
            int v = adjU.get(i), c = costU.get(i);
            if (distance[u] != Long.MAX_VALUE && distance[v] > distance[u] + c) {
                distance[v] = distance[u] + c;
                if (queue != null) queue.offer(v);
            }
        }
    }

    private static Set<Integer> bfs(Queue<Integer> queue, List<Integer>[] adj) {
        /**BFS to explore all vertices reachable from existing queue.*/
        Set<Integer> tmp = new HashSet<>();
        boolean[] visited = new boolean[adj.length];
        while (! queue.isEmpty()) {
            int u = queue.poll();
            tmp.add(u);
            visited[u] = true;
            for (int v : adj[u]) {
                if (! visited[v]) {
                    queue.offer(v);
                }
            }
        }
        return tmp;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        int s = scanner.nextInt() - 1;
        long distance[] = new long[n];
        int reachable[] = new int[n];
        int shortest[] = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] = Long.MAX_VALUE;
            reachable[i] = 0;
            shortest[i] = 1;
        }
        shortestPaths(adj, cost, s, distance, reachable, shortest);
        for (int i = 0; i < n; i++) {
            if (reachable[i] == 0) {
                System.out.println('*');
            } else if (shortest[i] == 0) {
                System.out.println('-');
            } else {
                System.out.println(distance[i]);
            }
        }
    }

}

