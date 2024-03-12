package com.basics.datastructures.heap;//package com.basics.datastructures;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
convert an array of integers into a heap.
This is the crucial step of the sorting algorithm called HeapSort.
It has guaranteed worst-case running time of 𝑂(𝑛 log 𝑛)
as opposed to QuickSort’s average running time of 𝑂(𝑛 log 𝑛).
QuickSort is usually used in practice, because typically it is faster,
 but HeapSort is used for external sort when you need to sort huge files
 that don’t fit into memory of your computer.
 */
public class BuildHeap {
    private int[] data;
    private List<Swap> swaps;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve();
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; ++i) {
          data[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
          out.println(swap.index1 + " " + swap.index2);
        }
    }

    private void generateSwapsNaive() {
      swaps = new ArrayList<Swap>();
      // The following naive implementation just sorts 
      // the given sequence using selection sort algorithm
      // and saves the resulting sequence of swaps.
      // This turns the given array into a heap, 
      // but in the worst case gives a quadratic number of swaps.
      //
      // TODO: replace by a more efficient implementation
      for (int i = 0; i < data.length; ++i) {
        for (int j = i + 1; j < data.length; ++j) {
          if (data[i] > data[j]) {
            swaps.add(new Swap(i, j));
            int tmp = data[i];
            data[i] = data[j];
            data[j] = tmp;
          }
        }
      }
    }

    /**
     * Key Idea:
     * Build up heap from bottom to up.
     * Ignore leaves since already heapified.
     * From the first inner node, sink it.
     * During the sink step, record all swaps
     */
    private void generateSwaps() {
        swaps = new ArrayList<Swap>();
        // Note 0-based index
        for (int i = data.length / 2; i >= 0; i--) {
            sink(i);
        }
    }
    /**Sink element i to maintain min-heap property.
     * <p>
     * Choose the smaller of left/right child, if any.
     * Terminate if i is already less than children.
     * Swap with the smaller child, record the swap.
     * Trace i downwards to the smaller children.
     * Terminate if i has no children.
     *
     * @param i the index where sinking begins
     */
    private void sink(int i) {
        int n = data.length;
        // While i has at least one left child
        while (i * 2 + 1 < n) {
            int j = i * 2 + 1;  // left child
            // Decide if right child exists and the smaller child
            j = (j + 1 < n && data[j + 1] < data[j]) ? j + 1 : j;
            // BZ: no swap if i == smallest child?
            if (data[i] <= data[j]) return;
            swaps.add(new Swap(i, j));
            int tmp = data[i];
            data[i] = data[j];
            data[j] = tmp;
            i = j;  // Forward i to its smaller child
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        generateSwaps();
        writeResponse();
        out.close();
    }

    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
