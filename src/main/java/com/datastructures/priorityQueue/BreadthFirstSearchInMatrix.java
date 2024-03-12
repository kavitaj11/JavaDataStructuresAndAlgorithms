package com.datastructures.priorityQueue;

import java.util.*;

/**
 * Implement the BFS algorithm (revisited a little bit)
 * See: https://en.wikipedia.org/wiki/Breadth-first_search
 *
 * Breadth-first search will always find the shortest path in an unweighted graph.
 * The graph may be cyclic or acyclic. ...
 * This pseudocode assumes that you are using a queue to implement BFS.
 * It also assumes you can mark vertices as visited,
 * and that each vertex stores a distance parameter, which is initialized as infinity.
 *
 * Dijkstra's Algorithm has a running time for O(|E| + |V|log|V|)
 * but it can find shortest path between source and target node in a weighted graph.
 * BFS has a running time of O(|E| + |V|)
 * but it only finds shortest path between source and target node when all your edge have equal weight.
 * If all your edge have same weight, there is indeed no need to run Dijkstra.
 */
public final class BreadthFirstSearchInMatrix {

    public static void main(String[] args) throws Exception {
        //example graph is given below
        int graph[][] = new int[][] {
                { 7, 2, 0, 9, 0, 0},
                { 2, 0, 7, 5, 8, 4},
                { 1, 7, 0, 7, 0, 3},
                { 0, 0, 7, 0, 8, 4},
                { 0, 8, 0, 8, 0, 5},
                { 0, 4, 3, 4, 5, 0} };
        for(int[] arr: shortestPath(graph, 2,5, true)){
                System.out.println(Arrays.toString(arr));
            }

        System.out.println(shortestPathLength(graph, 2, 5 , 0, false));
    }

    private static List<int[]> path = new ArrayList<>();
    private static int[] row_moves;
    private static int[] col_moves;

    /**
     * Get the starting point inside a matrix.
     * If there is no starting point, then {0, 0} is considered to be the starting point
     *
     * @param matrix The matrix in which you want to find the starting point
     * @return Coordinates of the starting point (i.e.: [row, col])
     */
    public static int[] findStartingPoint(int[][] matrix, int source) throws Exception{
        int row = 0;
        int col = 0;
        boolean startFlag = false;
        // Iterate through rows and cols to find the position of the starting point
        for (row = 0; row < matrix.length; row++) {
            for (col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == source) {
                    startFlag = true;
                }
                if (startFlag) {
                    break;
                }
            }
            if (startFlag) {
                break;
            }
        }
        // if there is no starting point
        if (row >= matrix.length || col >= matrix[0].length) {
            throw new IllegalArgumentException("Starting point is not found for given source.");
        }
        return new int[]{row, col};
    }

    /**
     * Check if the given position is a valid one or not
     *
     * @param matrix Matrix in which you want to test the validity of the point
     * @param x      X coordinate
     * @param y      Y coordinate
     * @return True if position is valid (i.e.: you can move to this position); False if not valid
     */
    private static boolean isValid(int[][] matrix, int x, int y, int...obstacle) {
        boolean isOk = true;
        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length) {
            isOk = false;
           // return isOk;
        }
        if (obstacle.length!=0 && matrix[x][y] == obstacle[0]) {
            isOk = false;
        }
        return isOk;
    }

    /**
     * Return the shortest path coordinates in the given matrix
     *
     * @param matrix  Matrix that you want to test
     * @param useDiag Boolean to indicate if we can move in diagonal (true: yes; false: no)
     * @return A list of coordinates of the shortest path inside the matrix
     */
    public static List<int[]> shortestPath(int[][] matrix, int source, int dest, boolean useDiag) throws Exception {
        path = new ArrayList<>();
        if (useDiag) {
            row_moves = new int[]{-1, -1, -1, 0, 1, 1, 1, 0}; // allow to move in diag
            col_moves = new int[]{-1, 0, 1, 1, 1, 0, -1, -1}; // allow to move in diag
        } else {
            row_moves = new int[]{-1, 1, 0, 0};
            col_moves = new int[]{0, 0, -1, 1};
        }
        int[] start = findStartingPoint(matrix, source);
        Node node = findPath(matrix, start[0], start[1], dest);
        if (node != null) {
            int len = buildPath(node);
        }
        return path;
    }

    /**
     * Build a list with all path coordinates and return the list length
     *
     * @param node Node "path"
     * @return Length of the shortest path
     */
    private static int buildPath(Node node) {
        if (node == null) {
            return 0;
        }
        int len = buildPath(node.getParent());
        path.add(new int[]{node.getX(), node.getY()});
        return len + 1;
    }

    /**
     * Find the shortest path inside the given matrix between the starting point and the ending point
     *
     * @param matrix   Matrix inside which you want to find the shortest path
     * @param startRow X coordinate of the starting point
     * @param startCol Y coordinate of the starting point
     * @return Node object of the shortest path
     */
    private static Node findPath(int[][] matrix, int startRow, int startCol, int dest) {
        // create a queue and add first node/cell
        Queue<Node> queue = new ArrayDeque<>();
        Node src = new Node(startRow, startCol, null); // starting node
        queue.add(src);
        // Create a set of visited cells
        Set<String> visited = new HashSet<>();

        String key = src.getX() + "," + src.getY();
        visited.add(key);
        // Run until the queue is not empty
        while (!queue.isEmpty()) {
            // process front node
            Node current = queue.poll();
            int x = current.getX();
            int y = current.getY();
            // Reach the end cell
            if (matrix[x][y] == dest) {
                return current;
            }
            // Go through all move possibilities and recur for each valid move
            for (int k = 0; k < row_moves.length; k++) {
                int x_next = x + row_moves[k];
                int y_next = y + col_moves[k];
                // check if it is a valid move
                if (isValid(matrix, x_next, y_next)) {
                    Node next = new Node(x_next, y_next, current);
                    key = next.getX() + "," + next.getY();
                    if (!visited.contains(key)) {
                        queue.add(next);
                        visited.add(key);
                    }
                }
            }
        }
        // return null if the path is not possible
        return null;
    }

    /**
     * Find the shortest path length between a starting point end an ending point in a matrix with obstacles
     *
     * @param matrix  Matrix in which you want to find the shortest path length
     * @param useDiag Boolean to indicate if we can move in diagonal (true: yes; false: no)
     * @return The shortest path length between the starting point and the ending point
     */
    public static int shortestPathLength(int[][] matrix, int source, int dest, int obstacle,  boolean useDiag) throws Exception {
        int[] start = findStartingPoint(matrix, source);
        int startrow = start[0];
        int startcol = start[1];
        return shortestPathLength(matrix, startrow, startcol, dest, obstacle, useDiag);
    }

    /**
     * Find the shortest path length between a starting point end an ending point in a matrix with obstacles,
     * with possibilities to move in diagonals
     *
     * @param matrix   Matrix in which you want to find the shortest path length
     * @param startRow X coordinate of the starting point
     * @param startCol Y coordinate of the starting point
     * @param useDiag  Boolean to indicate if we can move in diagonal (true: yes; false: no)
     * @return The shortest path length between the starting point and the ending point
     */
    private static int shortestPathLength(int[][] matrix, int startRow, int startCol, int dest, int obstacle, boolean useDiag) {
        int count = 0; // length of the shortest path
        Queue<int[]> nextToVisit = new LinkedList<>(); // Position list of next elements to visit
        nextToVisit.offer(new int[]{startRow, startCol});
        Set<int[]> visited = new HashSet<>(); // Position set of visited elements
        Queue<int[]> temp = new LinkedList<>();

        while (!nextToVisit.isEmpty()) {
            int[] position = nextToVisit.poll();
            int row = position[0];
            int col = position[1];

            // If we are at the end point
            if (matrix[row][col] == dest) {
                // +1 to include the ending point
                return count + 1;
            }
            // Upper element
            if (row > 0 && !visited.contains(new int[]{row - 1, col}) && matrix[row - 1][col] != obstacle) {
                temp.offer(new int[]{row - 1, col});
            }
            if (useDiag) {
                // Upper left element
                if (row > 0 && col > 0 && !visited.contains(new int[]{row - 1, col - 1}) && matrix[row - 1][col - 1] != obstacle) {
                    temp.offer(new int[]{row - 1, col - 1});
                }
                // Upper right element
                if (row > 0 && col < matrix[0].length - 1 && !visited.contains(new int[]{row - 1, col + 1}) && matrix[row - 1][col + 1] != obstacle) {
                    temp.offer(new int[]{row - 1, col + 1});
                }
                // Lower left element
                if (row < matrix.length - 1 && col > 0 && !visited.contains(new int[]{row + 1, col - 1}) && matrix[row + 1][col - 1] != obstacle) {
                    temp.offer(new int[]{row + 1, col - 1});
                }
                // Lower right element
                if (row < matrix.length - 1 && col < matrix[0].length - 1 && !visited.contains(new int[]{row + 1, col + 1}) && matrix[row + 1][col + 1] != obstacle) {
                    temp.offer(new int[]{row + 1, col + 1});
                }
            }
            // Lower element
            if (row < matrix.length - 1 && !visited.contains(new int[]{row + 1, col}) && matrix[row + 1][col] != obstacle) {
                temp.offer(new int[]{row + 1, col});
            }
            // Right element
            if (col < matrix[0].length - 1 && !visited.contains(new int[]{row, col + 1}) && matrix[row][col + 1] != obstacle) {
                temp.offer(new int[]{row, col + 1});
            }
            //Left element
            if (col > 0 && !visited.contains(new int[]{row, col - 1}) && matrix[row][col - 1] != obstacle) {
                temp.offer(new int[]{row, col - 1});
            }
            if (nextToVisit.isEmpty() && !temp.isEmpty()) {
                nextToVisit = temp;
                temp = new LinkedList<>();
                count++;
            }
            visited.add(new int[]{row, col});
        }
        return count;
    }
}

/**
 * Implement a Node (i.e. a cell in the matrix
 */
class Node {
    private final int x;
    private final int y; // coordinates of the node in the matrix
    private final Node parent; // Parent of the node

    /**
     * Default constructor
     *
     * @param x      X coordinate
     * @param y      Y coordinate
     * @param parent Parent node
     */
    public Node(int x, int y, Node parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    /**
     * Override the "toString" function
     *
     * @return String representation of the node. e.g.: Node{x=3, y=2, parent=Node{...}}
     */
    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                ", parent=" + parent +
                '}';
    }

    /**
     * Return a string representation of the node coordinates
     *
     * @return String representation of the node coordinates (e.g.: Node{x=3, y=2})
     */
    public String toStringCoordinates() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * Get the X coordinate
     *
     * @return X coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Get the Y coordinate
     *
     * @return Y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Get the parent Node
     *
     * @return Parent Node
     */
    public Node getParent() {
        return parent;
    }
}


/**
 * Implement constants
 */
final class Constants {
    public static final int START = 9;
    public static final int END = 3;
    public static final int OBSTACLE = -1;
    public static final int EMPTY = 0;
    public static final int DIM_CASE = 40;
    public static final int DEFAULT_MATRIX_ROW = 20;
    public static final int DEFAULT_MATRIX_COL = 20;
}
