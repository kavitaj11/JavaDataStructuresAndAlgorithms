package com.basics.graph.path;

import java.util.*;

//Computing the Minimum Cost of a Flight
//Given an directed graph with positive edge weights and with 𝑛 vertices and 𝑚 edges as well as two vertices 𝑢 and 𝑣,
// compute the weight of a shortest path between 𝑢 and 𝑣
// (that is, the minimum total weight of a path from 𝑢 to 𝑣).
// This requires minimizing not the number of segments, but the total cost of a flight.
// For this you construct a weighted graph:
// the weight of an edge from one city to another one is the cost of the corresponding flight.
public class Dijkstra {

    /**
     * Runtime if using array: O(|V|^2).</br>
     * Runtime if using PriorityQueue: O((|V| + |E|) * log|V|).</br>
     * BZ: Priority Queue stores same integer?<ul>
     *     <li>when comparing dist[v], will stop by same v...
     *     <li>=> Wrap {v, dist-value}; sort PQ on dist-value.
     *     <li>=> Even offering same v, the dist-value differs.</ul>
     */


    public static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        int n = adj.length;
        int[] dist = new int[n];
        int[] prev = new int[n];
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
        }
        dist[s] = 0;

        for (int j = 0; j < n; j++) {
            int u = minDistVertex(dist, visited);
            if (u == -1) continue;
            visited[u] = true;
            for (int i = 0; i < adj[u].size(); i++) {
                int v = adj[u].get(i), w = cost[u].get(i);
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    prev[v] = u;
                }
            }
        }
        return dist[t] == Integer.MAX_VALUE ? -1 : dist[t];
    }

    public static int minDistVertex(int[] dist, boolean[] visited) {
        /**Find the vertex with min dist and not yet visited.*/
        int minDist = Integer.MAX_VALUE, minVertex = -1;
        for (int v = 0; v < dist.length; v++) {
            if (visited[v]) continue;
            if (dist[v] < minDist) minVertex = v;
            minDist = Math.min(minDist, dist[v]);
        }
        return minVertex;
    }

    public static class DistNode {
        int v;
        int dist;
        public DistNode(int v, int d) {
            this.v = v;
            this.dist = d;
        }
    }

    private static long distanceUsingQueue(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
            int n = adj.length;
            int[] dist = new int[n];
            boolean[] visited = new boolean[n];
            PriorityQueue<DistNode> pq = new PriorityQueue<>(new Comparator<DistNode>(){
                @Override
                public int compare(DistNode v1, DistNode v2) {
                    if(!(v1.dist == v2.dist))
                    return v1.dist < v2.dist ? -1 : 1;
                    else return 0;
                }
            });
            for (int i = 1; i < n; i++) {
                dist[i] = Integer.MAX_VALUE;
                pq.offer(new DistNode(i, Integer.MAX_VALUE));
            }
            dist[0] = 0;
            pq.offer(new DistNode(0, 0));
            while (! pq.isEmpty()) {
                DistNode u = pq.poll();
                if (visited[u.v]) continue;
                visited[u.v] = true;
                for (int i = 0; i < adj[u.v].size(); i++) {
                    int next = adj[u.v].get(i), w = cost[u.v].get(i);
                    if (dist[next] > dist[u.v] + w) {
                        dist[next] = dist[u.v] + w;
                        pq.offer(new DistNode(next, dist[next]));
                    }
                }
            }
            return dist[t] == Integer.MAX_VALUE ? -1 : dist[t];
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}

