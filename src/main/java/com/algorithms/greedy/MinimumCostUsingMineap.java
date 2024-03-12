package com.algorithms.greedy;

//Given “n” block chains of varying lengths, find the minimum cost of connecting them.
//The cost to connect two pipes is equal to the sum of their lengths.
// We need to connect the pipes with minimum cost.

//The time complexity of the algorithm is O(nlogn).

/*
We use a MinHeap to solve this problem (implementation is given in MinHeap.java file).

Start by creating a minHeap of a capacity equal to n and put all the pipes in it (line 6).

Then iterate through the minHeap until its size equals one (line 8).

While iterating, extract two minimum length pipes from the minHeap (lines 10-11).

Then, update the total cost (line 13).

Next, insert a new pipe in minHeap with a length equal to the sum of the two extracted minimum lengths.

Finally, return the total minimum cost for connecting all pipes
 */

class MinimumCostUsingMineap {
 static int minCost(int pipes[], int n) {
  
  int cost = 0;
  int minimum, secondMinimum;
  MinHeap minHeap = new MinHeap(pipes, n);

  while (!minHeap.SizeIsOne()) {

   minimum = minHeap.extractMin();
   secondMinimum = minHeap.extractMin();

   cost += (minimum + secondMinimum);


   minHeap.insertKey(minimum + secondMinimum);
  }
  return cost;
 }




    public static void main(String[] args){
        int pipes[] = { 4, 3, 2, 6 };
        int n = pipes.length;

        System.out.println("Total cost for connecting pipes is " + MinimumCostUsingMineap.minCost(pipes, n));

        int[] pipes1 = {1, 1, 2, 6};
        n = pipes1.length;
        System.out.println("Total cost for connecting pipes1 is " + MinimumCostUsingMineap.minCost(pipes1,n));
    }

}



class MinHeap {
    int[] heaparr;
    int hSize;
    int capacity;

    public MinHeap(int pipes[], int size) {
        hSize = size;
        capacity = size;
        heaparr = pipes;
        int i = (hSize - 1) / 2;
        while (i >= 0) {
            MinHeapify(i);
            i--;
        }
    }

    void MinHeapify(int i) {
        int l = left(i);
        int r = right(i);
        int smallest = i;
        if (l < hSize && heaparr[l] < heaparr[i])
            smallest = l;
        if (r < hSize && heaparr[r] < heaparr[smallest])
            smallest = r;
        if (smallest != i) {
            swap(i, smallest);
            MinHeapify(smallest);
        }
    }

    int parent(int i) {
        return (i - 1) / 2;
    }


    int left(int i) {
        return (2 * i + 1);
    }


    int right(int i) {
        return (2 * i + 2);
    }


    int extractMin() {
        if (hSize <= 0)
            return Integer.MAX_VALUE;
        if (hSize == 1) {
            hSize--;
            return heaparr[0];
        }


        int root = heaparr[0];
        heaparr[0] = heaparr[hSize - 1];
        hSize--;
        MinHeapify(0);

        return root;
    }


    void insertKey(int x) {
        if (hSize == capacity) {
            System.out.println("Could not insertKey");
            return;
        }


        hSize++;
        int i = hSize - 1;
        heaparr[i] = x;


        while (i != 0 && heaparr[parent(i)] > heaparr[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }


    boolean SizeIsOne() {
        return (hSize == 1);
    }


    void swap(int x, int y) {
        int temp = heaparr[x];
        heaparr[x] = heaparr[y];
        heaparr[y] = temp;
    }
}